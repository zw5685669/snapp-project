package com.snapp.admin.enums;

/**
 * <p>
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-13  19:33
 */
public enum RedisEnum {
    ;

    public static final String PREFIX = "tl";

    private String             key;

    /**
     * 过期时间
     */
    private int                expire;

    /**
     * 1.实体 2.列表 3.其他
     */
    private int                type;


    public String getKey() {
        return PREFIX + "-" + RedisTypeEnum.getTypeName(type) + "-" + key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
