package com.midu.es.constans;

import java.util.regex.Pattern;

/**
 * @author zhangqy
 * @description TODO
 * @createTime 2022年09月14日 13:16:00
 */
public class Constant {
    public static final String APP_NAME = "UMEDIA_SERVICE";
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final String SHORT_TIME_PATTEN = "yyyy-MM-dd";
    public static final String LONG_TIME_PATTEN = "yyyy-MM-dd HH:mm:ss";
    public static final String INDEX_TIME_PATTEN = "yyyyMM";
    public static final String ZONE_TIME_PATTEN = "EEE MMM dd HH:mm:ss zzz yyyy";
    public static final String FANS_AGE_GROUP_TYPE = "fans_age_group";
    public static final String FANS_LOCATION_TYPE = "fans_location";
    public static final String FANS_CLASSIFY_TYPE = "fans_classify";
    public static final String FANS_TAG_TYPE = "fans_tag";
    public static final String FANS_ASTRO_TYPE = "fans_astro";
    public static final String FANS_LEVEL_TYPE = "fans_level";
    public static final String FANS_SOURCE_TYPE = "fans_source";
    public static final String FANS_ONLINE_TIME_TYPE = "fans_online_time";
    public static final String FANS_INFLUENCE_TOP20_TYPE = "fans_influence_top20";
    public static final String TAG = "tag";
    public static final String ASTRO = "astro";
    public static final String FAN = "fan";
    public static final String USER_STATUSES_TYPE = "user_statuses";
    public static final String USER_REPOSTS_TYPE = "user_reposts";
    public static final String USER_COMMENTS_TYPE = "user_comments";
    public static final String USER_DYNAMIC_TYPE = "user_dynamic";
    public static final String USER_COUNT_TYPE = "user_count";
    public static final String USER_MESSAGE_TYPE = "user_message";
    public static final String USER_LIKE_TYPE = "user_like";
    public static final String MIDU_SALT = "midu-idba@2016";
    public static final Pattern WEIBO_SOURCE_PATTERN = Pattern.compile("<a[^>]*>([^<]*)</a>");
    public static final String TYPE = "sina";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final int DEFAULT_SCOPE = 0;
    public static final int NEARLY_THREE_SCOPE = 1;
    public static final int NEARLY_SENVE_SCOPE = 2;
    public static final int NEARLY_THIRTY_SCOPE = 3;
    public static final int CUSTOM_SCOPE = 3;
    public static final String REQUEST_HANDLE_SUCCESS = "0000";
    public static final String REQUEST_HANDLE_FAILURE = "9999";
    public static final String REQUEST_NECESSARY_USERID = "0201";
    public static final String REQUEST_NECESSARY_STARTTIME = "0210";
    public static final String REQUEST_STIME_THAN_ETIME = "0211";
    public static final String REQUEST_NECESSARY_ENDTIME = "0211";
    public static final String QUERY_LOGIC_AND = "AND";
    public static final String QUERY_LOGIC_OR = "OR";
    public static final String ACCESS_TOKEN = "2.00rGMcaF0iLr8E14f63cd04ds_BLyD";
    public static final String KAFKA_BROKERS = "192.168.20.232:9092,192.168.20.233:9092,192.168.20.234:9092,192.168.20.16:9092,192.168.20.17:9092,192.168.20.18:9092";
    public static final String JUDGE_URL = "http://www.weibo.com/1";
    public static final String EMPTY = "";
    public static final String YUYIN = "分享语音";
    public static final String SEND_DIRECT_PREFIX = "ym_direct_send_";
    public static final String RECEIVE_DIRECT_PREFIX = "ym_direct_receive_";
    public static final String REDIS_KEY_COMMENT = "ym_kafka_comment";
    public static final String REDIS_KEY_AT_ME_COMMENT = "ym_kafka_at_me_comment";
    public static final String REDIS_KEY_AT_ME_SUBSCRIBE = "ym_kafka_at_me_subscribe";
    public static final String REDIS_KEY_STATUSE_REPOST = "ym_kafka_statuse_repost";
    public static final String REDIS_KEY_LIKE = "ym_kafka_like";
    public static final int MESSAGE_THREAD_COUNT = 30;
    public static final int AT_ME_STATUSES_THREAD_COUNT = 40;
    public static final int COMMENT_THREAD_COUNT = 50;
    public static final String REDIS_KEY_CACHE_MESSAGE = "ym_kafka_message";
    public static final String REDIS_KEY_CACHE_COMMENT = "ym_kafka_cache_comment";
    public static final String REDIS_KEY_CACHE_AT_ME_COMMENT = "ym_kafka_cache_at_me_comment";
    public static final String REDIS_KEY_CACHE_AT_ME_SUBSCRIBE = "ym_kafka_cache_at_me_subscribe";
    public static final String REDIS_KEY_CACHE_STATUS_REPOST = "ym_kafka_cache_status_repost";
    public static final String REDIS_KEY_CACHE_LIKE = "ym_kafka_cache_like";
    public static final String REDIS_KEY_FRIEND_SHIP = "ym_kafka_friend_ship";
    public static final String REDIS_KEY_INIT = "ym_init";
    public static final String REDIS_KEY_INIT_REPOST_SMALL = "ym_init_repost_small";
    public static final String REDIS_KEY_INIT_REPOST_MEDIUM = "ym_init_repost_medium";
    public static final String REDIS_KEY_INIT_REPOST_BIG = "ym_init_repost_big";
    public static final String REDIS_KEY_INIT_COMMENT_SMALL = "ym_init_comment_small";
    public static final String REDIS_KEY_INIT_COMMENT_MEDIUM = "ym_init_comment_medium";
    public static final String REDIS_KEY_INIT_COMMENT_BIG = "ym_init_comment_big";
    public static final String REDIS_KEY_ACCOUNT_RECALL = "ym_account_recall_queue";
    public static final String REDIS_KEY_ACCOUNT_POLLING = "ym_account_polling_queue";
    public static final String REDIS_KEY_ACCOUNT_INDUSTRY_POLLING = "ym_account_industry_polling_queue";
    public static final String REDIS_KEY_ACCOUNT_CUSTOM_POLLING = "ym_account_polling_custom_queue";
    public static final String REDIS_KEY_ACCOUNT_RECALL_CACHE = "ym_account_recall_cache";
    public static final String REDIS_KEY_ACCOUNT_POLLING_CACHE = "ym_account_polling_cache";
    public static final String REDIS_KEY_ACCOUNT_INDUSTRY_POLLING_CACHE = "ym_account_industry_polling_cache";
    public static final String REDIS_KEY_ACCOUNT_POLLING_CUSTOM_CACHE = "ym_account_polling_custom_cache";
    public static final String REDIS_KEY_FANS_AGE_GROUP_POLLING = "ym_fans_age_group_polling_queue";
    public static final String REDIS_KEY_FANS_LOCATION_POLLING = "ym_fans_location_polling_queue";
    public static final String REDIS_KEY_FANS_CLASSIFY_POLLING = "ym_fans_classify_polling_queue";
    public static final String REDIS_KEY_FANS_TAGS_POLLING = "ym_fans_tags_polling_queue";
    public static final String REDIS_KEY_FANS_ASTRO_POLLING = "ym_fans_astro_polling_queue";
    public static final String REDIS_KEY_FANS_LEVEL_POLLING = "ym_fans_level_polling_queue";
    public static final String REDIS_KEY_FANS_SOURCE_POLLING = "ym_fans_source_polling_queue";
    public static final String REDIS_KEY_FANS_ONLINE_TIME_POLLING = "ym_fans_online_time_polling_queue";
    public static final String REDIS_KEY_FANS_INFLUENCE_POLLING = "ym_fans_influence_polling_queue";
    public static final String REDIS_KEY_SEND_DIRECT_MESSAGE = "ym_send_direct_message_queue";
    public static final String REDIS_KEY_RECEIVE_DIRECT_MESSAGE = "ym_receive_direct_message_queue";
    public static final String REDIS_KEY_INIT_CACHE = "ym_init_cache";
    public static final String REDIS_KEY_INIT_STATUS_CACHE = "ym_init_status_cache_small";
    public static final String REDIS_KEY_INIT_REPOST_CACHE_SMALL = "ym_init_repost_cache_small";
    public static final String REDIS_KEY_INIT_REPOST_CACHE_MEDIUM = "ym_init_repost_cache_medium";
    public static final String REDIS_KEY_INIT_REPOST_CACHE_BIG = "ym_init_repost_cache_big";
    public static final String REDIS_KEY_INIT_COMMENT_CACHE_SMALL = "ym_init_comment_cache_small";
    public static final String REDIS_KEY_INIT_COMMENT_CACHE_MEDIUM = "ym_init_comment_cache_medium";
    public static final String REDIS_KEY_INIT_COMMENT_CACHE_BIG = "ym_init_comment_cache_big";
    public static final String REDIS_KEY_INIT_REPOST_LIST = "ym_init_repost_list";
    public static final String REDIS_KEY_INIT_COMMENT_LIST = "ym_init_comment_list";
    public static final String REDIS_KEY_INIT_REPOST_END = "ym_init_repost_end";
    public static final String REDIS_KEY_INIT_COMMENT_END = "ym_init_comment_end";
    public static final String REDIS_KEY_SEND_COMMENT_SINCEID_CACHE = "ym_send_comment_sinceid_cache";
    public static final String REDIS_KEY_RECEIVE_COMMENT_SINCEID_CACHE = "ym_receive_comment_sinceid_cache";
    public static final String REDIS_KEY_NETWORK_GROUP = "ym_network_group";
    public static final String REDIS_KEY_NETWORK_GROUP_WEIXIN = "ym_network_group_weixin";
    public static final String REDIS_KEY_NETWORK_GROUP_FAIL = "ym_network_group_fail";
    public static final String REDIS_KEY_MESSAGE_NUM_RECORD = "ym_message_num_record";
    public static final String REDIS_KEY_MESSAGE_NUM_RECORD_MON = "ym_message_num_record_monday";
    public static final String REDIS_KEY_MESSAGE_NUM_RECORD_TUES = "ym_message_num_record_tuesday";
    public static final String REDIS_KEY_MESSAGE_NUM_RECORD_WED = "ym_message_num_record_wednesday";
    public static final String REDIS_KEY_MESSAGE_NUM_RECORD_THUR = "ym_message_num_record_thursday";
    public static final String REDIS_KEY_MESSAGE_NUM_RECORD_FRI = "ym_message_num_record_friday";
    public static final String REDIS_KEY_MESSAGE_NUM_RECORD_SAT = "ym_message_num_record_saturday";
    public static final String REDIS_KEY_MESSAGE_NUM_RECORD_SUN = "ym_message_num_record_sunday";
    public static final int MONDAY = 2;
    public static final int TUESDAY = 3;
    public static final int WEDNESDAY = 4;
    public static final int THURSDAY = 5;
    public static final int FRIDAY = 6;
    public static final int SATURDAY = 7;
    public static final int SUNDAY = 1;
    public static final String REDIS_KEY_INIT_END_FLAG = "-1";
    public static final int INIT_STATUSES_THREAD_COUNT = 20;
    public static final int INIT_THREAD_COUNT_SMALL = 20;
    public static final int INIT_THREAD_COUNT_MEDIUM = 20;
    public static final int INIT_THREAD_COUNT_BIG = 20;
    public static final int ACCOUNT_THREAD_NUM = 10;
    public static final int FANS_THREAD_NUM = 5;
    public static final int DIRECT_MESSAGE_THREAD_NUM = 50;
    public static final String REDIS_KEY_INIT_DAILY_RANKING_CACHE = "ym_init_ranking_cache";
    public static final String REDIS_KEY_INIT_DAILY_RANKING = "ym_init_ranking";
    public static final String REDIS_KEY_DAILY_RANKING_CACHE = "ym_daily_ranking_cache";
    public static final String REDIS_KEY_DAILY_RANKING = "ym_dalily_ranking";
    public static final String REDIS_KEY_INIT_WEEKLY_RANKING_CACHE = "ym_init_weekly_cache";
    public static final String REDIS_KEY_INIT_WEEKLY_RANKING = "ym_init_weekly_ranking";
    public static final String REDIS_KEY_WEEKLY_RANKING_CACHE = "ym_weekly_ranking_cache";
    public static final String REDIS_KEY_WEEKLY_RANKING = "ym_weekly_ranking";
    public static final String REDIS_KEY_INIT_MONTHLY_RANKING_CACHE = "ym_init_monthly_cache";
    public static final String REDIS_KEY_INIT_MONTHLY_RANKING = "ym_init_monthly_ranking";
    public static final String REDIS_KEY_MONTHLY_RANKING_CACHE = "ym_monthly_ranking_cache";
    public static final String REDIS_KEY_MONTHLY_RANKING = "ym_monthly_ranking";
    public static final String REDIS_KEY_INIT_WEIXIN_DAILY_RANKING_CACHE = "ym_init_weixin_cache";
    public static final String REDIS_KEY_INIT_WEIXIN_DAILY_RANKING = "ym_init_weixin_ranking";

    public Constant() {
    }

    public interface WbAccountInitType {
        int SMALL = 0;
        int MEDIUM = 1;
        int BIG = 2;
        long SMALL_COUNT = 100000L;
        long BIG_COUNT = 500000L;
    }

    public interface WxMessageType {
        int TEXT = 1;
        int PICTURE = 2;
        int VOICE = 3;
        int VIDEO = 4;
    }

    public interface WbMessageType {
        int AT_ME_WB = 1;
        int AT_ME_COMMNET = 2;
        int RECEVICE_COMMNET = 3;
        int RECEVICE_SIXIN = 4;
        int SEND_SIXIN = 5;
        int COMMENT_BY_ME = 6;
        int REPLAY_COMMENT = 7;
        int STATUSES = 8;
        int REPOSTS = 9;
        int STATUSES_REPOSTS = 10;
        int ES_RECEVICE_COMMNET = 11;
    }

    public interface Month {
        String JAN = "01";
        String FEB = "02";
        String MAR = "03";
        String APR = "04";
        String MAY = "05";
        String JUN = "06";
        String JUL = "07";
        String AUG = "08";
        String SEP = "09";
        String OCT = "10";
        String NOV = "11";
        String DEC = "12";
    }
}
