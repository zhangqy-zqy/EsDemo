package com.midu.es.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Description TODO
 * @Author pual
 * @Date 2020-04-29
 * @Version v1.0.0
 */
@Configuration
@ComponentScan(basePackageClasses = ElasticFactory.class)
public class ElasticConfig {

    @Value("${elastic.cluster-host}")
    private String host;

    @Value("${elastic.cluster-port}")
    private int port;

    @Value("${elastic.client.connect-num}")
    private Integer connectNum;

    @Value("${elastic.client.connect-per-route}")
    private Integer connectPerRoute;


    @Bean
    public HttpHost httpHost() {
        return new HttpHost(host, port, "http");
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public ElasticFactory elasticFactory() {
        return ElasticFactory.build(httpHost(), connectNum, connectPerRoute);
    }

    @Bean
    @Scope("singleton")
    public RestClient getRestClient() {
        return elasticFactory().restClient();
    }

    @Bean
    public RestHighLevelClient getRestHighLevelClient() {
        return elasticFactory().restHighLevelClient();
    }


}
