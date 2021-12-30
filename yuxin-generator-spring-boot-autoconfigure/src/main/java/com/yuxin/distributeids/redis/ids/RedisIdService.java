package com.yuxin.distributeids.redis.ids;

import com.yuxin.distributeids.util.IdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author shenshixi
 * @Date 2021/12/18 14:45
 * @Version 1.0
 */
@Service
public class RedisIdService {


    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取链接工厂
     */
    private RedisConnectionFactory getConnectionFactory() {
        return redisTemplate.getConnectionFactory();
    }

    /**
     * 自增数
     *
     * @param key
     * @return
     */
    public long increment(String key) {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, getConnectionFactory());
        long l = redisAtomicLong.get();
        if (l > IdConstant.UPPER_LIMIT_VAL) {
            redisAtomicLong.getAndSet(0);
        }
        return redisAtomicLong.incrementAndGet();
    }

    /**
     * 自增数（带过期时间）
     *
     * @param key
     * @param time
     * @param timeUnit
     * @param idStep
     * @return
     */
    public long increment(String key, long time, TimeUnit timeUnit, int idStep) {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, getConnectionFactory());
        //设置步长
        if (idStep > 1) {
            redisAtomicLong.addAndGet(idStep - 1);
        }
        redisAtomicLong.expire(time, timeUnit);
        return redisAtomicLong.incrementAndGet();
    }

    /**
     * 自增数（带过期时间）
     *
     * @param key
     * @param expireAt
     * @return
     */
    public long increment(String key, Instant expireAt) {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, getConnectionFactory());
        redisAtomicLong.expireAt(Date.from(expireAt));
        return redisAtomicLong.incrementAndGet();
    }

    /**
     * 自增数（带过期时间和步长）
     *
     * @param key
     * @param increment
     * @param time
     * @param timeUnit
     * @return
     */
    public long increment(String key, int increment, long time, TimeUnit timeUnit) {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, getConnectionFactory());
        redisAtomicLong.expire(time, timeUnit);
        return redisAtomicLong.incrementAndGet();
    }

}
