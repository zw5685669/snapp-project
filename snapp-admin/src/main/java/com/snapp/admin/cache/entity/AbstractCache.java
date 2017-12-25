package com.snapp.admin.cache.entity;//package com.shining.task.cache.template;//package com.shining.task.cache;
////
////import com.google.common.collect.Lists;
////import com.google.gson.Gson;
////import com.shining.task.enums.RedisEnum;
////import com.shining.task.param.BaseParam;
////import com.shining.task.utils.Common;
////
////import java.lang.reflect.ParameterizedType;
////import java.util.ArrayList;
////import java.util.HashMap;
////import java.util.List;
////import java.util.Map;
////
/////**
//// * <p>
//// *    缓存 抽象类(非数据库实体建议重写 getCacheName方法 + ver和 os)
//// * </p>
//// *
//// * @author zw(汤泽辰吃得多喝得多)
//// * @date 2017-07-11  17:24
//// */
////public abstract class AbstractCacheHelp<P extends BaseParam, R> {
////
////    protected String  cacheNamePrefix;
////
////    /**
////     * 缓存相关类
////     */
////    private RedisEnum cache;
////
////    /**
////     * redis操作template
////     */
////    private RedisClient redisClient;
////
////    /**
////     * 根据Id列表获取返回
////     * @param id
////     * @param param
////     * @return
////     * @throws Exception
////     */
////    public R getById(Integer id, P param) throws Exception {
////        return getResMap(Lists.newArrayList(id), param).get(id);
////    }
////
////    /**
////     * 根据Id列表获取返回
////     * @param idList
////     * @param param
////     * @return
////     * @throws Exception
////     */
////    public Map<Integer, R> getResMap(List<Integer> idList, P param) throws Exception {
////
////        //初始化
////        init();
////
////        //返回list
////        Map<Integer, R> resMap = new HashMap<>();
////        List<R> resList = new ArrayList<>();
////
////        if (Common.isCollectionEmpty(idList)) {
////            return resMap;
////        }
////
////        List<R> cacheList = getResFromRedis(idList, param);
////        for (R r : cacheList) {
////            resMap.put(getPrimaryKey(r), r);
////        }
////
////        List<Integer> idFromDb = new ArrayList<>();
////        for (Integer id : idList) {
////            if (resMap.get(id) != null) {
////                continue;
////            }
////            idFromDb.add(id);
////        }
////
////        if (!Common.isCollectionEmpty(idFromDb)) {
////            List<R> dbList = getResFromDb(idFromDb, param);
////
////            //写缓存
////            writeCache(dbList, param);
////
////            for (R r : dbList) {
////                resMap.put(getPrimaryKey(r), r);
////            }
////        }
////
////        return resMap;
////    }
////
////    /**
////     * 初始化
////     */
////    protected void init() {
////        cache = initEnum();
////        initCacheName();
////        redisClient = initRedisClient();
////    }
////
////    /**
////     * 需要重写，传入enum
////     * @return
////     */
////    protected abstract RedisEnum initEnum();
////
////    /**
////     * 初始化缓存名称
////     */
////    protected String initCacheName(){
////        return cache.getKey();
////    }
////
////    /**
////     * 初始化缓存名称
////     */
////    protected abstract RedisClient initRedisClient();
////
////
////    /**
////     * 从缓存中获取数据
////     * @param idList
////     * @param param
////     * @return
////     * @throws Exception
////     */
////    protected List<R> getResFromRedis(List<Integer> idList, P param) throws Exception {
////        List<R> listRes = new ArrayList<>();
////
////        String[] strCachekeys = new String[idList.size()];
////
////        for (int i = 0; i < idList.size(); i++) {
////            int id = idList.get(i);
////            strCachekeys[i] = getCacheName(id, param);
////        }
////
////        List<String> Result;
////        Result = redisClient.mget(strCachekeys);
////
////        //解析缓存
////        parseCache(Result, listRes);
////
////        return listRes;
////    }
////
////    /**
////     * 获取缓存名称
////     * @param primaryKey
////     * @param param
////     * @return
////     * @throws Exception
////     */
////    protected String getCacheName(int primaryKey, P param) throws Exception {
////        String key;
////        String prefix = getCacheNamePrefix();
////        key = prefix + "-" + primaryKey;
////        return key;
////    }
////
////    /**
////     * 缓存前缀
////     * @return
////     */
////    protected String getCacheNamePrefix() {
////        return cacheNamePrefix;
////    }
////
////
////
////    /**
////     * 获取主键
////     * @param r
////     * @return
////     */
////    protected abstract int getPrimaryKey(R r);
////
////    /**
////     * 写入缓存
////     * @param dbList
////     * @param param
////     * @throws Exception
////     */
////    protected void writeCache(List<R> dbList, P param) throws Exception {
////
////        //填充数据
////        Gson gson = new Gson();
////        String[] keysValues = new String[dbList.size() * 2];
////        for (int i = 0; i < dbList.size(); i++) {
////
////            R r = dbList.get(i);
////
////            //---------------- write cache -----------------
////            String value = gson.toJson(r);
////            keysValues[i * 2] = getCacheName(getPrimaryKey(r), param);
////            keysValues[i * 2 + 1] = value;
////        }
////
////        redisClient.set(keysValues, getCacheTime());
////    }
////
////    /**
////     * 获取缓存时间 如果需要更改缓存时间 需要重写该方法
////     */
////    protected int getCacheTime() {
////        return cache.getExpire();
////    }
////
////    /**
////     * 解析缓存数据
////     * @param cacheValue 缓存数据
////     * @param resList
////     * @return
////     * @throws Exception
////     */
////    protected List<R> parseCache(List<String> cacheValue, List<R> resList) throws Exception {
////
////        if (Common.isCollectionEmpty(cacheValue)) {
////            return resList;
////        }
////
////        Gson gson = new Gson();
////        for (String value : cacheValue) {
////            R res = gson.fromJson(value, getRClass());
////            if (res == null) {
////                continue;
////            }
////            resList.add(res);
////        }
////
////        return resList;
////    }
////
////    /**
////     * 单个 清除对应缓存
////     */
////    public void clearCache(Integer primaryId) {
////        redisClient.dels(CommonSub.geneCacheNameNoDate(getCacheNamePrefix() + "-" + primaryId));
////    }
////
////    /**
////     * 批量 清除对应缓存
////     */
////    public void clearCache(List<Integer> primaryIdList) {
////        if (Common.isCollectionEmpty(primaryIdList)) {
////            return;
////        }
////
////        for (Integer primaryId : primaryIdList) {
////            redisClient.dels(CommonSub.geneCacheNameNoDate(getCacheNamePrefix() + "-" + primaryId));
////        }
////    }
////
////    /**
////     * 全部 清除对应缓存
////     */
////    public void clearCache() {
////        redisClient.dels(getCacheNamePrefix());
////    }
////
////    @SuppressWarnings("unchecked")
////    private Class<R> getRClass() {
////        return (Class<R>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
////    }
////
////    /**
////     * 批量写缓存
////     *
////     * @param idList
////     * @param param
////     * @return
////     */
////    protected abstract List<R> getResFromDb(List<Integer> idList, P param) throws Exception;
////
////}
