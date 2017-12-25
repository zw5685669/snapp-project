package com.snapp.common.dao;


import com.snapp.common.model.CarSeries;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *     车系
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-21  19:55
 */
@Mapper
public interface CarSeriesDao {

    /**
     * 插入记录
     * @param record
     * @return
     */
    int insert(CarSeries record);

    /**
     * 批量插入记录
     * @param records
     * @return
     */
    int batchInsert(List<CarSeries> records);

    /**
     * 查询一个未同步的
     * @return
     */
    CarSeries getNoSyncOne();

    /**
     * 修改同步状态
     * @param record
     * @return
     */
    int updateSyncStatus(CarSeries record);
}
