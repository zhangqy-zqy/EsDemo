package com.midu.es.util;

import com.google.common.base.Joiner;
import com.midu.es.constans.Constant;
import com.midu.es.constans.ConstantsChar;
import com.midu.es.constans.ConstantsNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author zhangqy
 * @version 1.0.0
 * @ClassName EsSearchIndexUtil.java
 * @Description es查询index处理类
 * @createTime 2022年09月07日 15:05:00
 */
public class EsSearchIndexUtil {

    private static final Logger logger = LoggerFactory.getLogger(EsSearchIndexUtil.class);

    /**
     * 默认使用系统时区
     */
    private static final ZoneId SYSTEM_DEFAULT = ZoneOffset.systemDefault();
    /**
     * 年索引
     */
    public static final String YEAR_INDEX_TIME_PATTEN = "yyyy";

    /**
     * 索引时间后缀-年月 yyyyMM
     */
    private static final DateTimeFormatter MOUTH_INDEX_FORMAT = DateTimeFormatter.ofPattern(Constant.INDEX_TIME_PATTEN, Locale.CHINA);

    /**
     * 索引时间后缀-年  YYYY
     */
    private static final DateTimeFormatter YEAR_INDEX_FORMAT = DateTimeFormatter.ofPattern(YEAR_INDEX_TIME_PATTEN, Locale.CHINA);


    /**
     * 根据索引前缀和查询时间获取索引
     *
     * @param prefix        索引前缀
     * @param startDateTime 开始查询时间
     * @param endDateTime   结束查询时间
     * @return 索引字符串 示例：prefix202206,prefix202207,prefix202208,prefix202209
     */
    public static String getIndexForDate(String prefix, Date startDateTime, Date endDateTime) {
        try{
            LocalDateTime s = LocalDateTime.ofInstant(Instant.ofEpochSecond(startDateTime.getTime() / ConstantsNumber.ONE_THOUSAND), SYSTEM_DEFAULT);
            final LocalDateTime e = LocalDateTime.ofInstant(Instant.ofEpochSecond(endDateTime.getTime() / ConstantsNumber.ONE_THOUSAND), SYSTEM_DEFAULT);
            final String formatEnd = MOUTH_INDEX_FORMAT.format(s);
            final String formatStart = MOUTH_INDEX_FORMAT.format(e);
            if (formatEnd.equals(formatStart)) {
                return String.format(prefix, formatStart);
            }
            List<String> list = new ArrayList<>();
            while (s.compareTo(e) < ConstantsNumber.ONE) {
                String format = MOUTH_INDEX_FORMAT.format(s);
                list.add(String.format(prefix, format));
                s = s.plus(Period.ofMonths(ConstantsNumber.ONE));
                s = s.withDayOfMonth(ConstantsNumber.ONE).withHour(ConstantsNumber.ZERO)
                        .withMinute(ConstantsNumber.ZERO).withSecond(ConstantsNumber.ZERO);
            }
            return indexTransform(list, prefix, startDateTime, endDateTime);
        }catch (Exception e){
            logger.error("描述：根据索引前缀和查询时间获取索引;方法：getIndexForDate();异常信息：{}", StackTraceCollectUtil.collectStackTrace(e));
        }
        return String.format(prefix, ConstantsChar.ANY_MATCH_CHAR);
    }


    /**
     * 当月索引太多转换为年索引  index_202201...index_202212  ->index_2022*
     *
     * @param indexList     索引集合
     * @param prefix        索引前缀
     * @param startDateTime 开始查询时间
     * @param endDateTime   结束查询时间
     * @return 月：index_202201,index_202211   或者 年:index_2022*
     */
    private static String indexTransform(List<String> indexList, String prefix, Date startDateTime, Date endDateTime) {
        if (indexList == null || indexList.isEmpty()) {
            return String.format(prefix, ConstantsChar.ANY_MATCH_CHAR);
        }
        if (indexList.size() < ConstantsNumber.TWELVE) {
            return Joiner.on(ConstantsChar.COMMA_CHAR).join(indexList);
        }
        LocalDateTime s = LocalDateTime.ofInstant(Instant.ofEpochSecond(startDateTime.getTime() / ConstantsNumber.ONE_THOUSAND), SYSTEM_DEFAULT);
        final LocalDateTime e = LocalDateTime.ofInstant(Instant.ofEpochSecond(endDateTime.getTime() / ConstantsNumber.ONE_THOUSAND), SYSTEM_DEFAULT);
        final String formatEnd = YEAR_INDEX_FORMAT.format(s);
        final String formatStart = YEAR_INDEX_FORMAT.format(e);
        if (formatEnd.equals(formatStart)) {
            return String.format(prefix, formatStart + ConstantsChar.ANY_MATCH_CHAR);
        }
        List<String> list = new ArrayList<>();
        while (s.compareTo(e) < ConstantsNumber.ONE) {
            String format = YEAR_INDEX_FORMAT.format(s);
            list.add(String.format(prefix, format + ConstantsChar.ANY_MATCH_CHAR));
            s = s.plus(Period.ofYears(ConstantsNumber.ONE));
            s = s.withDayOfYear(ConstantsNumber.ONE).withMonth(ConstantsNumber.ONE).withHour(ConstantsNumber.ZERO)
                    .withMinute(ConstantsNumber.ZERO).withSecond(ConstantsNumber.ZERO);
        }
        return Joiner.on(ConstantsChar.COMMA_CHAR).join(list);
    }
}
