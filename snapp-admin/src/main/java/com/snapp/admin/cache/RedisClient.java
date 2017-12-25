package com.snapp.admin.cache;//package com.shining.task.cache;
//
//import redis.clients.jedis.*;
//import redis.clients.jedis.exceptions.JedisException;
//
//import java.net.URI;
//import java.util.*;
//
//public class RedisClient {
//	private static int isTest = -1;
//	private static String connectStringTest;
//	private static String connectStringProduct;
//	private static ConsistentHash<String> consistentHashRedis = null;
//	private static int numberOfReplicas = 100;
//
//	/**
//	 * 对象销毁时关闭连接
//	 * @throws Throwable
//	 */
//	public static synchronized void closeFinal(){
//		for (JedisPool jedisPool : pool.values()) {
//			jedisPool.close();
//		}
//		pool.clear();
//	}
//
//
//	public static int getIsTest() {
//		try {
//			if (isTest == -1) {
//				String IsTest = new ConfigurationManager().readConfig("IsTest");
//				if (string.IsNullOrWhiteSpace(IsTest))
//					return 1;
//				setIsTest(Integer.parseInt(IsTest));
//			}
//		} catch (Exception e) {
//			Log.writeComLog(String.format("getIsTest:%s", e.getMessage()), "error_RedisClient");
//		}
//		return isTest;
//	}
//
//	public static void setIsTest(int isTest) {
//		RedisClient.isTest = isTest;
//	}
//
//	public static String getConnectStringTest() {
//		try {
//			if (string.IsNullOrWhiteSpace(connectStringTest)) {
//				String ConnectStringTest = new ConfigurationManager().readConfig("RedisConnectStringTest");
//				if (string.IsNullOrWhiteSpace(ConnectStringTest))
//					return "";
//				setConnectStringTest(ConnectStringTest);
//			}
//		} catch (Exception e) {
//			Log.writeComLog(String.format("getConnectStringTest:%s", e.getMessage()), "error_RedisClient");
//		}
//		return connectStringTest;
//	}
//
//	public static void setConnectStringTest(String connectStringTest) {
//		RedisClient.connectStringTest = connectStringTest;
//	}
//
//	public static String getConnectStringProduct() {
//		try {
//			if (string.IsNullOrWhiteSpace(connectStringProduct)) {
//				String ConnectStringProduct = new ConfigurationManager().readConfig("RedisConnectStringProduct");
//				if (string.IsNullOrWhiteSpace(ConnectStringProduct))
//					return "";
//				setConnectStringProduct(ConnectStringProduct);
//			}
//		} catch (Exception e) {
//			Log.writeComLog(String.format("getConnectStringProduct:%s", e.getMessage()), "error_RedisClient");
//		}
//		return connectStringProduct;
//	}
//
//	public static void setConnectStringProduct(String connectStringProduct) {
//		RedisClient.connectStringProduct = connectStringProduct;
//	}
//
//	// private static JedisPool pool = null;
//	private static Map<String, JedisPool> pool = new HashMap<String, JedisPool>();
//
//	// private static Map<String,JedisPool> poolnew = null;
//
//	public synchronized static JedisPool getPool() {
//		if (pool.get(getServerJedisPoolKey("")) == null) {
//			if (getIsTest() == 1)
//				init_test();
//			else
//				init_product();
//		}
//		return pool.get(getServerJedisPoolKey(""));
//	}
//
//	// 通过keyword来获取连接池
//	public synchronized static JedisPool getPool(String keyword) {
//		if (pool.get(getServerJedisPoolKey(keyword)) == null) {
//			if (getIsTest() == 1)
//				init_test(keyword);
//			else
//				init_product(keyword);
//		}
//		return pool.get(getServerJedisPoolKey(keyword));
//	}
//
//	// 多台redis服务器处理,含有{{#redis#}}分隔符
//	private static String GetRandomServer(String urlString) {
//
//		String[] urlStringArray = urlString.split("\\{\\{##\\}\\}");
//
//		int randomNum = (int) (1 + Math.random() * (urlStringArray.length - 1 + 1));
//
//		if (randomNum > urlStringArray.length || randomNum == 0) {
//			randomNum = 1;
//		}
//		urlString = urlStringArray[randomNum - 1].toString();
//
//		return urlString;
//
//	}
//
//	// 通过key返回对应的服务器
//	private static String GetRedisServer(String keyword, String urlString) {
//
//		List<String> ServerList = new ArrayList<String>();
//		if (consistentHashRedis == null) {
//			String[] urlStringArray = getServerList(urlString); // urlString2.split("\\{\\{##\\}\\}");
//			for (String serverinfo : urlStringArray) {
//				ServerList.add(serverinfo);
//			}
//
//			consistentHashRedis = new ConsistentHash<String>(numberOfReplicas, ServerList);
//		}
//
//		String RedisServer = consistentHashRedis.get(keyword).toString();
//
//		return RedisServer;
//	}
//
//	// 通过关键字获取服务器
//	private static void init_test(String keyword) {
//		// JedisPool pool = null;
//		try {
//			String urlString = getConnectStringTest();
//			// 多台redis服务器处理,含有{{#redis#}}分隔符
//			if (urlString.contains("{{##}}")) {
//				urlString = GetRedisServer(keyword, urlString);
//			}
//
//			final URI uri = URI.create(urlString);
//			String host = uri.getHost();
//			int port = uri.getPort();
//
//			JedisPoolConfig config = getPoolConfig();
//			pool.put(host, new JedisPool(config, host, port, Protocol.DEFAULT_TIMEOUT));
//		} catch (Exception e) {
//			Log.writeComLog(String.format("init_test:%s", e.getMessage()), "error_RedisClient");
//		}
//
//	}
//
//	private static void init_test() {
//		try {
//			String urlString = getConnectStringTest();
//			// 多台redis服务器处理,含有{{#redis#}}分隔符
//			if (urlString.contains("{{##}}")) {
//				urlString = getFirstServer(urlString);
//			}
//
//			final URI uri = URI.create(urlString);
//			String host = uri.getHost();
//			int port = uri.getPort();
//
//			JedisPoolConfig config = getPoolConfig();
//			pool.put(host, new JedisPool(config, host, port, Protocol.DEFAULT_TIMEOUT));
//		} catch (Exception e) {
//			Log.writeComLog(String.format("init_test:%s", e.getMessage()), "error_RedisClient");
//		}
//	}
//
//	// 通过关键字获取服务器
//	private static void init_product(String keyword) {
//		// JedisPool pool = null;
//		try {
//			String urlString = getConnectStringProduct();
//			// 多台redis服务器处理,含有{{#redis#}}分隔符
//			if (urlString.contains("{{##}}")) {
//				urlString = GetRedisServer(keyword, urlString);
//			}
//
//			final URI uri = URI.create(urlString);
//			String host = uri.getHost();
//			int port = uri.getPort();
//			String password = uri.getUserInfo();
//
//			JedisPoolConfig config = getPoolConfig();
//			pool.put(host, new JedisPool(config, host, port, Protocol.DEFAULT_TIMEOUT, password));
//		} catch (Exception e) {
//			Log.writeComLog(String.format("init_product:%s", e.getMessage()), "error_RedisClient");
//		}
//
//	}
//
//	private static void init_product() {
//		try {
//			String urlString = getConnectStringProduct();
//			// 多台redis服务器处理,含有{{#redis#}}分隔符
//			if (urlString.contains("{{##}}")) {
//				urlString = getFirstServer(urlString);
//			}
//
//			final URI uri = URI.create(urlString);
//			String host = uri.getHost();
//			int port = uri.getPort();
//			String password = uri.getUserInfo();
//
//			JedisPoolConfig config = getPoolConfig();
//			pool.put(host, new JedisPool(config, host, port, Protocol.DEFAULT_TIMEOUT, password));
//		} catch (Exception e) {
//			Log.writeComLog(String.format("init_product:%s", e.getMessage()), "error_RedisClient");
//		}
//	}
//
//	private static JedisPoolConfig getPoolConfig() {
//		JedisPoolConfig config = new JedisPoolConfig();
//		config.setMaxIdle(50);
//		config.setMinIdle(5);
//		config.setMaxTotal(1000);
//		config.setMaxWaitMillis(3000);
//
//		config.setTestOnBorrow(true);
//		config.setTestOnReturn(true);
//		config.setTestWhileIdle(true);
//		config.setMinEvictableIdleTimeMillis(1000L * 60L * 1L);
//		config.setSoftMinEvictableIdleTimeMillis(1000L * 60L * 1L);
//		config.setTimeBetweenEvictionRunsMillis(60000); // 1m
//		config.setNumTestsPerEvictionRun(10);
//
//		return config;
//	}
//
//	/**
//	 * key 存储值 + 1
//	 * @param key
//	 * @return
//	 */
//	public static Long incr(final String key){
//		Jedis jedis = null;
//		Long res = 0L;
//		try {
//			jedis = getPool().getResource();
//
//			return jedis.incr(key);
//		} catch (JedisException e) {
//			if (jedis != null)
//				jedis.close();
//		} finally {
//			if (jedis != null)
//				jedis.close();
//		}
//		return res;
//	}
//
//	/**
//	 * key 存储值 + increment
//	 * @param key
//	 * @param increment 增量
//	 * @return
//	 */
//	public static Long incrBy(final String key, final Integer increment){
//		Jedis jedis = null;
//		Long res = 0L;
//		try {
//			jedis = getPool().getResource();
//
//			return jedis.incrBy(key, increment);
//		} catch (JedisException e) {
//			if (jedis != null)
//				jedis.close();
//		} finally {
//			if (jedis != null)
//				jedis.close();
//		}
//		return res;
//	}
//
//	/**
//	 * 验证指定key是否存在
//	 * @param key
//	 * @return
//     */
//	public static Boolean exists(final String key){
//		Jedis jedis = null;
//		boolean success = false;
//		try {
//			jedis = getPool().getResource();
//
//			return jedis.exists(key);
//		} catch (JedisException e) {
//			if (jedis != null)
//				jedis.close();
//		} finally {
//			if (jedis != null)
//				jedis.close();
//		}
//		return success;
//	}
//
//	public static String get(final String key) throws RedisXYException {
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool().getResource();
//
//			return jedis.get(key);
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//			throw new RedisXYException("redisexception");
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//		// return "";
//	}
//
//	// 通过keyword来获取信息
//	public static String get(final String keyword, final String key) throws RedisXYException {
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool(keyword).getResource();
//
//			return jedis.get(key);
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//			throw new RedisXYException("redisexception");
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//		// return "";
//	}
//
//	/**
//	 * 获取多个key值
//	 *
//	 * @param keys
//	 * @return
//	 * @throws RedisXYException
//	 */
//	public static List<String> mget(final String... keys) throws RedisXYException {
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool().getResource();
//
//			return jedis.mget(keys);
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//			throw new RedisXYException("redisexception");
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//		// return "";
//	}
//
//	/**
//	 * 获取set中随机member
//	 *
//	 * @param key
//	 * @param count
//	 * @return
//	 * @throws RedisXYException
//	 */
//	public static List<String> srandmember(final String key, final int count) throws RedisXYException {
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool().getResource();
//
//			return jedis.srandmember(key, count);
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//			throw new RedisXYException("redisexception");
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//		// return "";
//	}
//
//	public static String set(final String key, final int seconds, final String value) {
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool().getResource();
//			return jedis.setex(key, seconds, value);
//
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//		return "";
//	}
//
//	/**
//	 * 批量set缓存
//	 * @param keyvalues
//	 * @param seconds
//     * @return
//     */
//	public static String set(final String[] keyvalues, final int seconds) {
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool().getResource();
//			Pipeline pipeline = jedis.pipelined();
//			for (int i = 0; i < keyvalues.length; i += 2) {
//				pipeline.setex(keyvalues[i], seconds, keyvalues[i + 1]);
//			}
//			pipeline.sync();
//			return "";
//
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//		return "";
//	}
//
//	/**
//	 * hash表批量set
//	 * @param key
//	 * @param fieldValues
//	 * @return
//	 */
//	public static String hmset(final String key, Map<String, String> fieldValues) {
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool().getResource();
//			return jedis.hmset(key, fieldValues);
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//		return "";
//	}
//
//	/**
//	 * 同时设置多个值
//	 *
//	 * @param keysvalues
//	 * @return
//	 */
//	public static String mset(final String... keysvalues) {
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool().getResource();
//			return jedis.mset(keysvalues);
//
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//		return "";
//	}
//
//	/**
//	 * set add
//	 *
//	 * @param key
//	 * @param members
//	 * @return
//	 */
//	public static Long sadd(final String key, final String[] members) {
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool().getResource();
//			return jedis.sadd(key, members);
//
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//		return 0L;
//	}
//
//	/**
//	 * 设置zset
//	 *
//	 * @param key
//	 * @param scoreMembers
//	 * @return
//	 */
//	public static Long zadd(final String key, final Map<String, Double> scoreMembers) {
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool().getResource();
//			return jedis.zadd(key, scoreMembers);
//
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//		return 0L;
//	}
//
//
//	/**
//	 * zrevrange 从大到小
//	 *
//	 * @param key
//	 * @param start 起始
//	 * @param end 结束
//	 * @return
//	 */
//	public static Set<String> zrevrange(final String key, final long start, final long end) {
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool().getResource();
//			return jedis.zrevrange(key, start, end);
//
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//		return new LinkedHashSet<String>();
//	}
//
//	/**
//	 * zrevrange 从大到小
//	 *
//	 * @param key
//	 * @param start 起始
//	 * @param end 结束
//	 * @return
//	 */
//	public static Set<Tuple> zrevrangeWithScore(final String key, final long start, final long end) {
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool().getResource();
//			return jedis.zrevrangeWithScores(key, start, end);
//
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//		return new LinkedHashSet<Tuple>();
//	}
//
//	/**
//	 * zrevrange 从大到小
//	 *
//	 * @param key
//	 * @param member 起始
//	 * @return
//	 */
//	public static Long zrevrank(final String key,final String member) {
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool().getResource();
//			return jedis.zrevrank(key, member);
//
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//		return 0L;
//	}
//
//
//	/**
//	 * zrange 从小到大
//	 *
//	 * @param key
//	 * @param start 起始
//	 * @param end 结束
//	 * @return
//	 */
//	public static Set<String> zrange(final String key, final long start, final long end) {
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool().getResource();
//			return jedis.zrange(key, start, end);
//
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//		return new LinkedHashSet<String>();
//	}
//
//
//
//	// 通过keyword来存信息
//	public static String set(final String keyword, final String key, final int seconds, final String value) {
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool(keyword).getResource();
//			return jedis.setex(key, seconds, value);
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//		return "";
//	}
//
//	public static long del(String key) {
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool().getResource();
//			return jedis.del(key);
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//
//			// throw e;
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//		return -1;
//	}
//
//	public static void dels(String pattern) {
//		if (pattern == null || pattern.equals(""))
//			return;
//		if (!pattern.endsWith("*"))
//			pattern += "*";
//
//		Jedis jedis = null;
//		boolean success = true;
//		try {
//			jedis = getPool().getResource();
//			Set<String> keys = jedis.keys(pattern);
//
//			Iterator<String> it = keys.iterator();
//			while (it.hasNext()) {
//				String key = it.next();
//				jedis.del(key);
//			}
//		} catch (JedisException e) {
//			success = false;
//			if (jedis != null)
//				jedis.close();
//		} finally {
//			if (success && jedis != null)
//				jedis.close();
//		}
//	}
//
//	public static Long sadd(final String key, final String value) {
//		Jedis jedis = null;
//		try {
//			jedis = getPool().getResource();
//			return jedis.sadd(key, value);
//		} catch (JedisException e) {
//			return 0L;
//		} finally {
//			if (jedis != null)
//				jedis.close();
//		}
//	}
//
//	public static Long expire(final String key, final int seconds) {
//		Jedis jedis = null;
//		try {
//			jedis = getPool().getResource();
//			return jedis.expire(key, seconds);
//		} catch (JedisException e) {
//			return 0L;
//		} finally {
//			if (jedis != null)
//				jedis.close();
//		}
//	}
//
//	// 服务器通过分隔符，返回服务器数组
//	public static String[] getServerList(String urlString) {
//
//		String[] ServerList = urlString.split("\\{\\{##\\}\\}");
//		return ServerList;
//	}
//
//	// 服务器通过分隔符，返回第一个服务器，主要用于不用分服务器的情况
//	public static String getFirstServer(String urlString) {
//
//		String firstServerInfo = urlString.split("\\{\\{##\\}\\}")[0];
//		return firstServerInfo;
//	}
//
//	// 判断是否存对应服务器的在连接池
//	public static String getServerJedisPoolKey(String keyword) {
//		String urlString = "";
//		if (getIsTest() == 1) {
//			urlString = getConnectStringTest();
//		} else {
//			urlString = getConnectStringProduct();
//		}
//		// 多台redis服务器处理,含有{{#redis#}}分隔符
//		if (urlString.contains("{{##}}")) {
//			urlString = GetRedisServer(keyword, urlString);
//		}
//
//		final URI uri = URI.create(urlString);
//		String host = uri.getHost();
//
//		return host;
//	}
//}
