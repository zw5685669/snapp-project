package com.snapp.admin.controller;

import com.google.gson.Gson;
import com.snapp.common.dao.BrandDao;
import com.snapp.common.model.Brand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *     品牌
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-01  19:04
 */
@RestController
@RequestMapping("/brand/")
@Api(value = "品牌接口")
public class BrandController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    BrandDao                    brandDao;

    @ApiOperation(value = "获取未同步的品牌", notes = "测试测试测试啊啊")
    @RequestMapping(value = "list", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String getAccounts() {
        Brand brand = brandDao.getNoSyncOne();
        return new Gson().toJson(brand);
    }
}
