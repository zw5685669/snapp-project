package com.snapp.job.read;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.snapp.common.dao.BrandDao;
import com.snapp.common.dao.CarSeriesDao;
import com.snapp.common.model.Brand;
import com.snapp.common.model.CarSeries;
import com.snapp.common.utils.Common;
import com.snapp.job.constants.ReadConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     读取品牌信息
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-20  20:28
 */
@Component
public class CarSeriesTask {

    private static final Logger LOGGER = LogManager.getLogger(CarSeriesTask.class);

    @Autowired
    BrandDao                    brandDao;

    @Autowired
    CarSeriesDao                carSeriesDao;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void read() {
        try {
            Brand brand = brandDao.getNoSyncOne();
            if (brand == null) {
                return;
            }
            //修改为正在同步状态
            brand.setSyncStatus(ReadConstants.SYNC_ING);
            brandDao.updateSyncStatus(brand);

            Gson gson = new Gson();
            String result = CxfClient.getRes(ReadConstants.CAR_SERIES, Lists.newArrayList(brand.getName()));
            List<String> nameList = gson.fromJson(result, new TypeToken<List<String>>() {
            }.getType());

            List<CarSeries> carSeries = new ArrayList<>();
            for (String name : nameList) {
                CarSeries series = new CarSeries();
                series.setBrandId(brand.getId());
                series.setIconUrl("");
                series.setName(name);
                series.setSyncStatus(0);
                carSeries.add(series);
            }
            if (!Common.isCollectionEmpty(carSeries)) {
                carSeriesDao.batchInsert(carSeries);
            }

            //修改为完成状态
            brand.setSyncStatus(ReadConstants.SYNC_FINISH);
            brandDao.updateSyncStatus(brand);
        } catch (Exception e) {
            LOGGER.debug(e);
            e.printStackTrace();
        }

    }
}
