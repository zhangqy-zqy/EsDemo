package com.midu.es.service;

import com.midu.es.util.StackTraceCollectUtil;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <h3>sjzt-common</h3>
 * <p></p>
 *
 * @author :
 * @date : 2020-05-11 16:29
 **/
@Component
public class HighLevelClient {

    private static final Logger logger = LoggerFactory.getLogger(HighLevelClient.class);

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private static RestHighLevelClient client;

    @PostConstruct
    public void Init() {
        client = this.restHighLevelClient;
    }

    /**
     * 将terms和term和match进行数据疯转
     *
     * @param boolQueryBuilder boolQueryBuilder
     * @param termsFile        termsFile
     * @param termFile         termFile
     * @param matchFile        matchFile
     * @return BoolQueryBuilder
     */
    private static BoolQueryBuilder handleTermsOrTermAndMatch(BoolQueryBuilder boolQueryBuilder,
                                                              Map<String, List<Object>> termsFile,
                                                              Map<String, Object> termFile,
                                                              Map<String, Object> matchFile) {
        if (termsFile != null && !termsFile.isEmpty()) {
            termsFile.keySet().forEach((key) -> {
                List<Object> values = termsFile.get(key);
                if (values!=null && !values.isEmpty()) {
                    boolQueryBuilder.must(QueryBuilders.termsQuery(key, values));
                }
            });
        }
        if (termFile != null && !termFile.isEmpty()) {
            termFile.keySet().forEach((key) -> {
                Object value = termFile.get(key);
                if (value != null) {
                    boolQueryBuilder.must(QueryBuilders.termQuery(key, value));
                }
            });
        }
        if (matchFile != null && !matchFile.isEmpty()) {
            matchFile.keySet().forEach((key) -> {
                Object value = matchFile.get(key);
                if (value != null) {
                    boolQueryBuilder.must(QueryBuilders.matchQuery(key, value));
                }
            });
        }
        return boolQueryBuilder;
    }

    /**
     * searchAfter 查询数据，暂时在测试阶段
     *
     * @param index     索引字符串  "index01,index02,index03"
     * @param type      类型
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param range     范围
     * @param size      数据量
     * @param fields    字段
     * @param sortFields 排序字段
     * @param terms     terms map集合
     * @param term      term map集合
     * @param match     match 集合
     * @param retry     重试次数
     * @return 数据集合
     */
    public static List<Object> searchAfter(String index, String type, long startTime, long endTime,
                                           String range, Integer size, String fields, List<String> sortFields,
                                           Map<String, List<Object>> terms, Map<String, Object> term,
                                           Map<String, Object> match, int... retry){
        List<Object> result = new ArrayList<>();
        try {
            SearchRequest searchRequest = new SearchRequest(index.split(","));
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            searchRequest.types(type.split(","));
            if (StringUtils.isNotEmpty(fields)) {
                sourceBuilder.fetchSource(fields.split(","), null);
            }
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (startTime > 0 && endTime > 0 && startTime < endTime) {
                RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(range);
                rangeQueryBuilder.format("epoch_millis").from(startTime, true).to(endTime, true);
                boolQueryBuilder.filter().add(rangeQueryBuilder);
            }
            boolQueryBuilder = handleTermsOrTermAndMatch(boolQueryBuilder, terms, term, match);
            if (sortFields!=null && !sortFields.isEmpty()) {
                for (String sortField : sortFields) {
                    sourceBuilder.sort(sortField, SortOrder.DESC);
                }
            }
            searchRequest.source(sourceBuilder.size(size).query(boolQueryBuilder));
            SearchResponse response = client.search(searchRequest);
            SearchHit[] searchHits = response.getHits().getHits();
            while (null != searchHits && searchHits.length > 0) {
                List<Object> sourceAsMaps= Arrays.stream(searchHits)
                        .map((Function<SearchHit, Object>) SearchHit::getSourceAsMap)
                        .collect(Collectors.toList());
                result.addAll(sourceAsMaps);
                SearchHit last = searchHits[searchHits.length - 1];
                sourceBuilder = sourceBuilder.searchAfter(last.getSortValues());
                response = client.search(searchRequest);
                searchHits = response.getHits().getHits();
            }
        } catch (IOException e) {
            logger.error("描述：searchAfter查询数据;方法:searchAfter();异常信息:{}", StackTraceCollectUtil.collectStackTrace(e));
            int r = retry.length > 0 ? retry[0] : 1;
            if (r > 3) {
                return null;
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e1) {
                e.printStackTrace();
            }
            result = searchAfter(index, type, startTime, endTime, range, size, fields, sortFields, terms, term, match, ++r);
        }
        return  result;
    }

}