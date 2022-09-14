package com.midu.es.util;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;

//@Component
//@Order(Integer.MIN_VALUE)
public class ElasticSearchConfiguration implements FactoryBean<TransportClient>, InitializingBean, DisposableBean {

    @Value("${ym.elasticsearch.cluster-nodes}")
    private String clusterNodes;

    @Value("${ym.elasticsearch.cluster-name}")
    private String clusterName;

    private TransportClient client;

    @Override
    public TransportClient getObject() throws Exception {
        return client;
    }

    @Override
    public Class<?> getObjectType() {
        return TransportClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        buildTransportClient();
    }

    protected void buildTransportClient() {
    	
    	if(client != null){
    		return;
    	}

        try {
            PreBuiltTransportClient preBuiltTransportClient = new PreBuiltTransportClient(settings());
            if (!"".equals(clusterNodes)) {
                for (String nodes : clusterNodes.split(",")) {
                    String inetSocket[] = nodes.split(":");
                    String address = inetSocket[0];
                    Integer port = Integer.valueOf(inetSocket[1]);
                    preBuiltTransportClient.addTransportAddress(new InetSocketTransportAddress(
                            InetAddress.getByName(address), port
                    ));
                }
                client = preBuiltTransportClient;
            }
        } catch (Exception e) {

        }

    }

    private Settings settings() {

        Settings settings = Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", true)
                .build();

        return settings;
    }

    @Override
    public void destroy() throws Exception {
        try {
            if (null != client) {
                client.close();
            }
        } catch (Exception e) {

        }
    }
}
