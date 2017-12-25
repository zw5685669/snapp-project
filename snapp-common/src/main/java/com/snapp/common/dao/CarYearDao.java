package com.snapp.common.dao;


import com.snapp.common.model.CarYear;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *     年款
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-21  19:55
 */
@Mapper
public interface CarYearDao {

    /**
     * 插入记录
     * @param record
     * @return
     */
    int insert(CarYear record);

    /**
     * 批量插入记录
     * @param records
     * @return
     */
    int batchInsert(List<CarYear> records);

    /**
     * 查询一个未同步的
     * @return
     */
    CarYear getNoSyncOne();

    /**
     * 修改同步状态
     * @param record
     * @return
     */
    int updateSyncStatus(CarYear record);
}
