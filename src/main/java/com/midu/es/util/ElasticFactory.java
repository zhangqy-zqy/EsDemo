package com.midu.es.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.Objects;

/**
 * @Description TODO
 * @Author pual
 * @Date 2020-04-29
 * @Version v1.0.0
 */
public class ElasticFactory {

    public static int               CONNECT_TIMEOUT_MILLIS = 10000;
    public static int               SOCKET_TIMEOUT_MILLIS = 180000;
    public static int               CONNECTION_REQUEST_TIMEOUT_MILLIS = 0;
    public static int               MAX_CONN_PER_ROUTE = 5;
    public static int               MAX_CONN_TOTAL = 20;

    private static HttpHost         HTTP_HOST;
    private RestClientBuilder       builder;
    private RestClient              restClient;
    private RestHighLevelClient     restHighLevelClient;

    private static ElasticFactory   factory = new ElasticFactory();

    private ElasticFactory(){}

    public static ElasticFactory build(HttpHost httpHost, Integer maxConnectNum, Integer maxConnectPerRoute) {
        HTTP_HOST           = httpHost;
        MAX_CONN_TOTAL      = maxConnectNum;
        MAX_CONN_PER_ROUTE  = maxConnectPerRoute;
        return factory;
    }

    public static ElasticFactory build(HttpHost httpHost,Integer connectTimeOut, Integer socketTimeOut, Integer connectionRequestTime,Integer maxConnectNum, Integer maxConnectPerRoute){
        HTTP_HOST                           = httpHost;
        CONNECT_TIMEOUT_MILLIS              = connectTimeOut;
        SOCKET_TIMEOUT_MILLIS               = socketTimeOut;
        CONNECTION_REQUEST_TIMEOUT_MILLIS   = connectionRequestTime;
        MAX_CONN_TOTAL                      = maxConnectNum;
        MAX_CONN_PER_ROUTE                  = maxConnectPerRoute;
        return factory;
    }


    public void init() {
        builder = RestClient.builder(HTTP_HOST);
        setConnectTimeOutConfig();
        setConnectConfig();
        restClient = builder.build();
        restHighLevelClient = new RestHighLevelClient(restClient);
    }


    private void setConnectTimeOutConfig() {
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(CONNECT_TIMEOUT_MILLIS);
            requestConfigBuilder.setSocketTimeout(SOCKET_TIMEOUT_MILLIS);
            requestConfigBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT_MILLIS);
            return requestConfigBuilder;
        }).setMaxRetryTimeoutMillis(5 * 60 * 1000);
    }

    private void setConnectConfig() {
        builder.setHttpClientConfigCallback(httpAsyncClientBuilder -> {
            httpAsyncClientBuilder.setMaxConnTotal(MAX_CONN_TOTAL);
            httpAsyncClientBuilder.setMaxConnPerRoute(MAX_CONN_PER_ROUTE);
            return httpAsyncClientBuilder;
        });
    }

    public RestClient restClient() {
        return restClient;
    }

    public RestHighLevelClient restHighLevelClient() {
        return restHighLevelClient;
    }

    public void close() {
        if (Objects.nonNull(restClient)) {
            try {
                restClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
