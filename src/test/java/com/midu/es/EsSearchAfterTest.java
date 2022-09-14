package com.midu.es;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.midu.es.service.HighLevelClient;
import com.midu.es.util.EsSearchIndexUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhangqy
 * @description TODO
 * @createTime 2022年09月14日 13:05:00
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class EsSearchAfterTest {

    @Before
    public void init() {
    }


    /**
     * 根据时间查询数据
     */
    @Test
    public void searchAfterOfTime() throws IOException  {
        // 索引
        String cuominIndex="umedia_correction_v2_dev_%s";
        //umedia_correction_v2_www_*
        //2022/1/1/00/00/00
        Date startDate = new Date(1640966400000L);
        //2022/1/31/23/59/59
        Date endDate = new Date(1643644799000L);
        String biz="MzUzMjY4ODYxMQ==";
        //String biz="11111,2222,3333";
        Map<String, Object> termMap = Maps.newHashMapWithExpectedSize(1);
        termMap.put("CoUid",biz);
        String index = EsSearchIndexUtil.getIndexForDate(cuominIndex,startDate,endDate);
        List<String>  sortFields= org.apache.commons.compress.utils.Lists.newArrayList();
        sortFields.add("CoPublishDate");
        sortFields.add("id");
        List<Object> dataList = HighLevelClient.searchAfter(index, "correct",
                startDate.getTime(), endDate.getTime(), "CoPublishDate", 2000, "", sortFields,
                null, termMap, null);
        if (dataList != null && dataList.size() > 0) {
            System.out.println("数据量：" + dataList.size() + ",具体数据为：");
            List<Object> ids = dataList.stream().map(data -> {
                JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(data));
                return "\""+jsonObject.get("id")+"\"";
            }).collect(Collectors.toList());
            saveFile("C:\\Users\\zhang\\Desktop\\export",ids);
        } else {
            System.out.println("没有数据！！！！！");
        }
    }

    /**
     * 不根据时间查询数据
     *
     * @throws IOException
     */
    @Test
    public void searchAfterNoTime() throws IOException {
        // 索引
        String index = "umedia_correction_v2_www_*";
        //umedia_correction_v2_www_*
        Map<String, Object> termMap = Maps.newHashMapWithExpectedSize(1);
        termMap.put("CoUmNetworkGroupId", "1649887");

        List<String> sortFields = Lists.newArrayList();
        sortFields.add("CoPublishDate");
        sortFields.add("id");
        List<Object> dataList = HighLevelClient.searchAfter(index, "correct", 0, 0,
                null, 2000, "id", sortFields, null, termMap, null);
        if (dataList != null && dataList.size() > 0) {
            System.out.println("数据量：" + dataList.size() + ",具体数据为：");
            List<Object> ids = dataList.stream().map(data -> {
                JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(data));
                return "\""+jsonObject.get("id")+"\"";
            }).collect(Collectors.toList());
            saveFileOFId("C:\\Users\\zhang\\Desktop\\export", ids);
        } else {
            System.out.println("没有数据！！！！！");
        }
    }


    /**
     * 存储数据 只存储id
     *
     * @param path 存储数据的位置
     * @param ids  id集合
     * @throws IOException
     */
    public static void saveFileOFId(String path, List<Object> ids) throws IOException {
        File f1 = new File(path + "\\ids.txt");
        if (!f1.exists()) {
            f1.createNewFile();
        }
        BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f1, true), "UTF-8"));
        String str = Joiner.on("\r\n").join(ids);
        /*一个字符一个字符写入文件中*/
        char[] cs = str.toCharArray();
        for (char c : cs) {
            w.write(c);
        }
        w.flush();      //清除缓冲区
        w.close();      //关闭流
        System.out.println("OK!");
    }

    /**
     * 存储数据
     *
     * @param path 存储数据的位置
     * @param ids  id集合
     * @throws IOException
     */
    public static void saveFile(String path, List<Object> ids) throws IOException {
        File f1 = new File(path + "\\ids.txt");
        if (!f1.exists()) {
            f1.createNewFile();
        }
        BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f1, true), "UTF-8"));
        StringBuilder str=new StringBuilder();

        Integer from=0;
        Integer size=3;
        while (from<ids.size()){
            Integer to=from+size;
            if(to > ids.size()){
                to=ids.size();
            }
            List<Object> newIds=ids.subList(from,to);
            str.append("update ym_web_word_error  set zstatus=0,update_date = NOW() where es_id in (" + Joiner.on(",").join(newIds) + ");\r\n");
            str.append("update ym_web_article_error set zstatus=0,update_date = NOW() where es_id in (" + Joiner.on(",").join(newIds) + ");\r\n");
            from=from+size;
        }
        /*一个字符一个字符写入文件中*/
        char[] cs = str.toString().toCharArray();
        for (char c : cs) {
            w.write(c);
        }
        w.flush();      //清除缓冲区
        w.close();      //关闭流
        System.out.println("OK!");
    }

}
