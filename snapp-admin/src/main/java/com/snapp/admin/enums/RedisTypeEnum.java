package com.snapp.admin.enums;

/**
 * <p>
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-13  19:33
 */
public enum RedisTypeEnum {
    ENTITY(1, "entity"),
    LIST(2, "list"),
    OTHER(3, "other"),
    ;

    public static String getTypeName(int type) {
        for (RedisTypeEnum redisType : RedisTypeEnum.values()) {
            if (type != redisType.type) {
                continue;
            }
            return redisType.prifix;
        }
        return "";
    }

    RedisTypeEnum(int type, String prifix) {
        this.type = type;
        this.prifix = prifix;
    }

    /**
     * 1.实体 2.列表 3.其他
     */
    private int    type;

    private String prifix;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPrifix() {
        return prifix;
    }

    public void setPrifix(String prifix) {
        this.prifix = prifix;
    }
}
