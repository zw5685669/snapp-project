package com.snapp.common.model;

/**
 * <p>
 *     品牌
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-21  19:55
 */
public class Brand extends BaseDO{

    private String name;

    private String iconUrl;

    /**
     * 首字母
     */
    private String prefix;

    /**
     * 0：未同步 1：正在同步 2：同步完成
     */
    private int syncStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }
}
