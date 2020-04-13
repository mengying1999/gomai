package com.gomai.order.delay;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.weaver.patterns.ThisOrTargetPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.glassfish.external.statistics.Statistic;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis缓存工具
 */
@Service("jedisUtil")
public class JedisUtil {
    private JedisPool pool;

    @Autowired
    private MyProperties properties;

    private static Log log = LogFactory.getLog(JedisUtil.class);

    private JedisUtil() {
    }

    @SuppressWarnings("unused")
    @PostConstruct  // 指定spring实例化对象之后调用的方法
    private void init() {
        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxActive(Integer.parseInt(properties.getJedisMaxActive()));
        config.setMaxIdle(properties.getJedisMaxIdle());
        config.setMaxTotal(properties.getMaxTotal());
//        config.setMaxWait(Long.parseLong(properties.getJedisMaxWait()));
        config.setTestOnBorrow(false);
        pool = new JedisPool(new JedisPoolConfig(), properties.getJedisHost(),
                properties.getJedisPort(),
                properties.getJedisTimeout());
    }

    public void set(String key, String value) {
        Jedis jedis = this.getResource();
        try {
            jedis.set(key, value);
        } finally {
            this.returnResource(jedis);
        }
    }

    public String get(String key) {
        Jedis jedis = this.getResource();
        try {
            return jedis.get(key);
        } finally {
            this.returnResource(jedis);
        }
    }

    public void setObject(String key, Object obj) {
        Jedis jedis = this.getResource();
        try {
            jedis.set(key.getBytes(), serialize(obj));
        } finally {
            this.returnResource(jedis);
        }
    }
    public Object getObject(String key) {

        Jedis jedis = this.getResource();
        try {
            if(jedis.get(key.getBytes()) == null) {
                return null;
            } else {
                return unserialize(jedis.get(key.getBytes()));
            }
        } finally {
            this.returnResource(jedis);
        }
    }
    /**
     * 删除key
     * @param
     */
    public void delkey(String...keys) {
        Jedis jedis = this.getResource();
        try {
            jedis.del(keys);
        } finally {
            this.returnResource(jedis);
        }
    }

    /**
     * 设置hash的值
     * @param key   hash中的key
     * @param field hash中的域
     * @param obj   值
     */
    public void setHash(String key,String field,Object obj) {
        Jedis jedis = this.getResource();
        try {
            jedis.hset(key.getBytes(), field.getBytes(), serialize(obj));
        } finally {
            this.returnResource(jedis);
        }
    }
    /**
     * 查找redis中hash的value值
     * @param key  hash中的key
     * @param field hash中的域
     * @return 返回对象
     */
    public Object getHash(String key,String field) {
        Jedis jedis = this.getResource();
        try {
            if (jedis.hget(key, field) == null) {
                return null;
            }
            return unserialize(jedis.hget(key.getBytes(), field.getBytes()));
        } finally {
            this.returnResource(jedis);
        }
    }

    /**
     * 删除hash中的指定域
     * @param key
     * @param fields
     * @return
     */
    public Long removeHash(String key,String fields) {
        Jedis jedis = this.getResource();
        try {

            return jedis.hdel(key.getBytes(),fields.getBytes());

        } finally {
            this.returnResource(jedis);
        }
    }

    /**
     * 返回hash中的所有域
     * @param key
     */
    public Set<String> hKeys(String key) {
        Jedis jedis = this.getResource();
        try {
            Set<String> hkeys = jedis.hkeys(key);
            return hkeys;
        } finally {
            this.returnResource(jedis);
        }
    }
    /**
     * 序列化
     * @param object
     * @return
     */
    private static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("jedis序列化异常.....");
        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    private static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("jedis反序列化异常.....");
        }
        return null;
    }

    /**
     * 获取jedis
     * @return
     */
    private Jedis getResource() {
        Jedis jedis = pool.getResource();
//        jedis.auth(properties.getJedisPassword());
        return jedis;
    }

    /**
     * 设置生命周期(过期时间)
     * @param key
     * @param
     */
    public void setExpireByKey(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = this.getResource();
            jedis.expire(key, seconds);
        } catch (Exception e) {
            log.error(e);
        } finally {
            this.returnResource(jedis);
        }
    }

    /**
     * 获取某个Key的余下存活时间（秒）。
     * @param key
     * @return 存活时间（秒）
     */
    public long getTimeToLive(String key) {
        Jedis jedis = null;
        long sec = -2;
        try {
            jedis = this.getResource();
            sec = jedis.ttl(key);
        } catch (Exception e) {
            log.error(e);
        } finally {
            this.returnResource(jedis);
        }
        return sec;
    }

    /**
     * jedis放回连接池
     * @param jedis
     */
    private void returnResource(Jedis jedis) {
        pool.returnResource(jedis);
    }

    /**
     * 释放Redis资源池。
     */
    public void destroy() {
        if(pool != null) {
            pool.destroy();
        }
        log.info("Redis池已销毁");
    }
}
