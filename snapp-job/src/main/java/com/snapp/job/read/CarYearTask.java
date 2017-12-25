package com.snapp.job.read;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.snapp.common.dao.CarSeriesDao;
import com.snapp.common.dao.CarYearDao;
import com.snapp.common.model.CarSeries;
import com.snapp.common.model.CarYear;
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
 *     读取年款信息
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-20  20:28
 */
@Component
public class CarYearTask {

    private static final Logger LOGGER = LogManager.getLogger(CarYearTask.class);

    @Autowired
    CarSeriesDao                carSeriesDao;

    @Autowired
    CarYearDao                  carYearDao;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void read() {
        try {
            CarSeries carSeries = carSeriesDao.getNoSyncOne();
            if (carSeries == null) {
                return;
            }
            //修改为正在同步状态
            carSeries.setSyncStatus(ReadConstants.SYNC_ING);
            carSeriesDao.updateSyncStatus(carSeries);

            Gson gson = new Gson();
            String result = CxfClient.getRes(ReadConstants.CAR_SERIES, Lists.newArrayList(carSeries.getName()));
            List<String> nameList = gson.fromJson(result, new TypeToken<List<String>>() {
            }.getType());

            List<CarYear> carYears = new ArrayList<>();
            for (String name : nameList) {
                CarYear year = new CarYear();
                year.setCarSeriesId(carSeries.getId());
                year.setName(name);
                year.setSyncStatus(0);
                carYears.add(year);
            }
            if (!Common.isCollectionEmpty(carYears)) {
                carYearDao.batchInsert(carYears);
            }

            //修改为完成状态
            carSeries.setSyncStatus(ReadConstants.SYNC_FINISH);
            carSeriesDao.updateSyncStatus(carSeries);
        } catch (Exception e) {
            LOGGER.debug(e);
            e.printStackTrace();
        }

    }
}
