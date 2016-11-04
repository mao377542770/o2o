package com.ugfind.dao.cache;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.ugfind.model.Schoolvisits;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao{
	
	private static final Logger logger = LoggerFactory.getLogger("redisDao");
	//jedis 数据连接池
	private final JedisPool jedisPool;
	
	//指定要序列化的类
	private RuntimeSchema<Schoolvisits> schema = RuntimeSchema.createFrom(Schoolvisits.class);
	
	//缓存时间
	private final int timeout = 60*30;  //缓存半个小时
	
	
	//初始化
	public RedisDao(String ip,int port){
		jedisPool  = new JedisPool(ip, port);
	}
	
	public Schoolvisits getVisitBySchoolId(Integer schoolId){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String key = "visit"+schoolId;
			//要序列化
			//get -> byte[]  二进制数据   -> 反序列化 ->object()  
			//为了效率,我们不使用jdk  自带 实现 Serializable  ,因为效率非常低
			//protostuff : 序列化类必须是 :pojo 标准
			byte[] bytes = jedis.get(key.getBytes());
			if(bytes != null){
				//先new 一个空对象
				Schoolvisits schoolvisits = schema.newMessage();
				//字节,需要复制的对象,对象模型
				ProtostuffIOUtil.mergeFrom(bytes, schoolvisits, schema);
				return schoolvisits;
			}
		} catch (Exception e) {
			logger.error("redis 线程池未启动");
		}finally{
			if(jedis!=null){
				jedis.close(); //关闭连接
			}
		}
		return null;
	}
	
	
	public String setSchoolVisit(Schoolvisits schoolvisits){
		//序列化  存 redis
		Jedis jedis = null;
		try {
			String key = "visit" + schoolvisits.getSchoolId();
			jedis = jedisPool.getResource();
			//序列化对象  , scheme模板 ,默认大小(缓冲的作用)
			byte[] bytes = ProtostuffIOUtil.toByteArray(schoolvisits,schema,LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
			//设置超时缓存
			String result =  jedis.setex(key.getBytes(),timeout, bytes);
			return result;
		} catch (Exception e) {
			logger.error("redis 线程池未启动");
		}finally{
			if(jedis!=null){
				jedis.close();
			}
		}
		return null;
	}
}
