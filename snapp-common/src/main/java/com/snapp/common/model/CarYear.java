package com.snapp.common.model;

/**
 * <p>
 *     年款
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-21  19:55
 */
public class CarYear extends BaseDO{

    private String name;

    /**
     * 车系Id
     */
    private int carSeriesId;

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

    public int getCarSeriesId() {
        return carSeriesId;
    }

    public void setCarSeriesId(int carSeriesId) {
        this.carSeriesId = carSeriesId;
    }

    public int getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }
}
