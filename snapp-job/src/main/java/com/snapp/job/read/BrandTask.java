package com.snapp.job.read;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.snapp.common.dao.BrandDao;
import com.snapp.common.model.Brand;
import com.snapp.common.utils.Common;
import com.snapp.job.constants.ReadConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BrandTask {

    private static final Logger LOGGER = LogManager.getLogger(BrandTask.class);

    int                         i      = 0;

    @Autowired
    BrandDao                    brandDao;

    //    @Scheduled(cron = "0 0 1 * * ?")
//    @Scheduled(cron = "0/5 * * * * ?")
    public void read() {

        try {
            if (i > 0) {
                return;
            }
            Gson gson = new Gson();
            for (int i = 'A'; i < 'A' + 26; i++) {
                String brandPrefix = String.valueOf((char) i);
                String result = CxfClient.getRes(ReadConstants.BRAND, Lists.newArrayList(brandPrefix));
                List<String> nameList = gson.fromJson(result, new TypeToken<List<String>>() {
                }.getType());

                List<Brand> brands = new ArrayList<>();
                for (String name : nameList) {
                    Brand brand = new Brand();
                    brand.setIconUrl("");
                    brand.setName(name);
                    brand.setPrefix(brandPrefix);
                    brand.setSyncStatus(0);
                    brands.add(brand);
                }
                if (!Common.isCollectionEmpty(brands)) {
                    brandDao.batchInsert(brands);
                }
            }
            i++;
        } catch (Exception e) {
            LOGGER.debug(e);
            e.printStackTrace();
        }

    }
}
