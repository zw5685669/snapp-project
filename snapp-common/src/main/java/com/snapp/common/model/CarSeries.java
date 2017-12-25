package com.snapp.common.model;

/**
 * <p>
 *     车系
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-21  19:55
 */
public class CarSeries extends BaseDO{

    private String name;

    /**
     * 品牌Id
     */
    private int brandId;

    private String iconUrl;

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

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }
}
