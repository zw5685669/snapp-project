package com.snapp.job.constants;

/**
 * <p>
 *     读取数据 常量类
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-19  20:46
 */
public class ReadConstants {

    /**
     * 请求url
     */
    public static final String WSDL_URL = "http://123.127.164.42:8080/GodNian/services/godnian?wsdl";

    /**
     * 用户名
     */
    public static final String WSDL_USERNAME = "admin";

    /**
     * 密码
     */
    public static final String WSDL_PASSWORD = "123456";

    //--------------------------- status ---------------------------------

    /**
     * 正在同步状态
     */
    public static final int SYNC_ING = 1;

    /**
     * 同步完成
     */
    public static final int SYNC_FINISH = 2;

    //--------------------------- method ---------------------------------


    /**
     * 根据首字母查品牌
     */
    public static final String BRAND = "getAutomakernameByInitial";

    /**
     * 根据品牌查车系
     */
    public static final String CAR_SERIES = "getVecModeByAutomakerName";

}
