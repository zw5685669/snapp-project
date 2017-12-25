package com.snapp.admin.cache.list;//package com.shining.task.cache.template;//package com.shining.task.Service.cache;
////
////import com.google.gson.Gson;
////import com.shining.task.param.BaseParam;
////
////
////import java.lang.reflect.ParameterizedType;
////import java.util.Date;
////
/////**
//// * <p>
//// *     列表 缓存 抽象类
//// *     ps:如需添加泛型。添加至Result后
//// * </p>
//// *
//// * @author zw(汤泽辰吃得多喝得多)
//// * @date 2017-08-29  17:45
//// */
////public abstract class AbstractListCacheHelp<Param extends BaseParam, Result extends BaseListCacheRes> {
////
////    private static final String LIST_CACHE_PREFIX  = "list_";
////
////    /**
////     * 默认缓存时间 6小时
////     */
////    private static final int    DEFAULT_CACHE_TIME = 6 * 60 * 60;
////
////    /**
////     * 缓存名称 前缀
////     */
////    private String cacheNamePrefix;
////
////    /**
////     * 获取数据
////     * @param param
////     * @return
////     * @throws Exception
////     */
////    public Result getResult(Param param) throws Exception {
////
////        String key = getCacheName(param);
////        String value = RedisClient.get(key);
////
////        Result result = null;
////        if (StringUtils.isNotBlank(value)) {
////
////            result = getResFromCache(value);
////
////            //过期时间检查
////            if (!expireDate(result)) {
////                //其他业务供拓展
////                if (extraOk(result)) {
////                    return extraBiz(result);
////                }
////            }
////        }
////
////        result = getResFromDb(param);
////
////        setExpireTime(param, result);
////
////        writeCache(param, result);
////
////        return result;
////    }
////
////    /**
////     * 默认与os 版本 read 相关 如需要与其他相关 需重写该方法
////     * @param param
////     * @return
////     * @throws Exception
////     */
////    protected String getCacheName(Param param) throws Exception {
////        String key = "";
////        String prefix = CommonSub.geneCacheNameNoDate(getCacheNamePrefix());
////        key = prefix + "_" + param.getOs() + "_" + param.getVer() + "_" + Common.GetIsTestPre(param.getIstest());
////        return key;
////    }
////
////    protected String getCacheNamePrefix() {
////        if (StringUtils.isBlank(cacheNamePrefix)) {
////            return cacheNamePrefix = LIST_CACHE_PREFIX + initCacheName();
////        }
////        return cacheNamePrefix;
////    }
////
////    /**
////     * 初始化缓存名称
////     */
////    protected abstract String initCacheName();
////
////    /**
////     * 全部 清除对应缓存
////     */
////    public void clearCache() {
////        RedisClient.dels(CommonSub.geneCacheNameNoDate(getCacheNamePrefix()));
////    }
////
////    /**
////     * 从redis读
////     *
////     * @param value
////     * @return
////     */
////    protected Result getResFromCache(String value) throws Exception {
////        return parseCache(value);
////    }
////
////    /**
////     * 解析缓存数据
////     * @param value 缓存数据
////     * @return
////     * @throws Exception
////     */
////    protected Result parseCache(String value) throws Exception {
////        Gson gson = new Gson();
////        return gson.fromJson(value, getRClass());
////    }
////
////    @SuppressWarnings("unchecked")
////    private Class<Result> getRClass() {
////        return (Class<Result>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
////    }
////
////    /**
////     * 获取数据相关业务  + write chache
////     *
////     * @param param
////     * @return
////     */
////    protected abstract Result getResFromDb(Param param) throws Exception;
////
////    /**
////     * 可设置缓存过期时间 不重写则表示无过期时间
////     *
////     * @param result
////     * @return
////     */
////    protected abstract void setExpireTime(Param param, Result result) throws Exception;
////
////    /**
////     * 将结果写入缓存 可设置时间
////     *
////     * @param param
////     * @param result
////     * @return
////     */
////    protected void writeCache(Param param, Result result) throws Exception {
////        Gson gson = new Gson();
////        String value = gson.toJson(result);
////        RedisClient.set(getCacheName(param), getCacheTime(), value);
////    }
////
////    /**
////     * 获取缓存时间 如果需要更改缓存时间 需要重写该方法
////     */
////    protected int getCacheTime() {
////        return DEFAULT_CACHE_TIME;
////    }
////
////    /**
////     * 获取缓存数据后其他业务
////     * @return
////     */
////    protected Result extraBiz(Result result) {
////        return result;
////    }
////
////    /**
////     * 获取缓存数据后其他业务 默认返回true
////     * @return
////     */
////    protected boolean extraOk(Result result) {
////        return true;
////    }
////
////    /**
////     * 检查返回列表过期时间
////     * end = 0 表示无过期时间
////     * @param result
////     * @return
////     */
////    protected boolean expireDate(Result result) {
////        boolean isExpire = false;
////        Date now = DatetimeUtils.getUTCDate();
////        if (result.getEnd() != 0 && result.getEnd() < now.getTime()) {
////            isExpire = true;
////        }
////        return isExpire;
////    }
////}
