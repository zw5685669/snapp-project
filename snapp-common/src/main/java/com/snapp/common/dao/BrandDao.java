package com.snapp.common.dao;


import com.snapp.common.model.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *     品牌
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-21  19:55
 */
@Mapper
public interface BrandDao {

    /**
     * 插入记录
     * @param record
     * @return
     */
    int insert(Brand record);

    /**
     * 批量插入记录
     * @param records
     * @return
     */
    int batchInsert(List<Brand> records);

    /**
     * 查询一个未同步的
     * @return
     */
    Brand getNoSyncOne();

    /**
     * 修改同步状态
     * @param record
     * @return
     */
    int updateSyncStatus(Brand record);
}
