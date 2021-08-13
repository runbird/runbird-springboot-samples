package com.scy.modules.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 类名： JedisUtil <br>
 * 描述：TODO <br>
 * 创建日期： 2020/4/13 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Component
public class JedisUtil {
    private static final Logger log = LoggerFactory.getLogger(JedisUtil.class);
    @Autowired
    private JedisPool jedisPool;


    private Jedis getJedis() {
        return this.jedisPool.getResource();
    }

    public String set(String key, String value) {
        Jedis jedis = null;

        Object var5;
        try {
            jedis = this.getJedis();
            String var4 = jedis.set(key, value);
            return var4;
        } catch (Exception var9) {
            log.error("set key:{} value:{} error", new Object[]{key, value, var9});
            var5 = null;
        } finally {
            this.close(jedis);
        }

        return (String) var5;
    }

    public String set(String key, String value, int expireTime) {
        Jedis jedis = null;

        Object var6;
        try {
            jedis = this.getJedis();
            String var5 = jedis.setex(key, expireTime, value);
            return var5;
        } catch (Exception var10) {
            log.error("set key:{} value:{} expireTime:{} error", new Object[]{key, value, expireTime, var10});
            var6 = null;
        } finally {
            this.close(jedis);
        }

        return (String) var6;
    }

    public String get(String key) {
        Jedis jedis = null;

        Object var4;
        try {
            jedis = this.getJedis();
            String var3 = jedis.get(key);
            return var3;
        } catch (Exception var8) {
            log.error("get key:{} error", key, var8);
            var4 = null;
        } finally {
            this.close(jedis);
        }

        return (String) var4;
    }

    public Long del(String key) {
        Jedis jedis = null;

        Object var4;
        try {
            jedis = this.getJedis();
            Long var3 = jedis.del(key.getBytes());
            return var3;
        } catch (Exception var8) {
            log.error("del key:{} error", key, var8);
            var4 = null;
        } finally {
            this.close(jedis);
        }

        return (Long) var4;
    }

    public Boolean exists(String key) {
        Jedis jedis = null;

        Object var4;
        try {
            jedis = this.getJedis();
            Boolean var3 = jedis.exists(key.getBytes());
            return var3;
        } catch (Exception var8) {
            log.error("exists key:{} error", key, var8);
            var4 = null;
        } finally {
            this.close(jedis);
        }

        return (Boolean) var4;
    }

    public Long expire(String key, int expireTime) {
        Jedis jedis = null;

        Object var5;
        try {
            jedis = this.getJedis();
            Long var4 = jedis.expire(key.getBytes(), expireTime);
            return var4;
        } catch (Exception var9) {
            log.error("expire key:{} error", key, var9);
            var5 = null;
        } finally {
            this.close(jedis);
        }

        return (Long) var5;
    }

    public Long ttl(String key) {
        Jedis jedis = null;

        Object var4;
        try {
            jedis = this.getJedis();
            Long var3 = jedis.ttl(key);
            return var3;
        } catch (Exception var8) {
            log.error("ttl key:{} error", key, var8);
            var4 = null;
        } finally {
            this.close(jedis);
        }

        return (Long) var4;
    }

    private void close(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }

    }
}
