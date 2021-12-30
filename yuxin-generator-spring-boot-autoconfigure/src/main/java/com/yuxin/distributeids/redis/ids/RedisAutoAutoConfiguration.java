package com.yuxin.distributeids.redis.ids;

import com.yuxin.distributeids.config.BaseRedisConfig;
import com.yuxin.distributeids.config.MapperConfig;
import com.yuxin.distributeids.redis.RedisService;
import com.yuxin.distributeids.service.IdStrategyService;
import com.yuxin.distributeids.service.impl.IdStrategyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Description
 * @Author shenshixi
 * @Date 2021/12/18 14:54
 * @Version 1.0
 */
@Configuration
@Import({BaseRedisConfig.class, MapperConfig.class})
@ComponentScan(basePackages = {"com.yuxin.distributeids"})
public class RedisAutoAutoConfiguration {

    @Bean
    public RedisIdGenerator redisIdGenerator() {
        return new RedisIdGenerator();
    }

    @Bean
    public IdStrategyService idStrategyService() {
        return new IdStrategyServiceImpl();
    }

    @Bean
    public RedisService redisService() {
        return new RedisServiceImpl();
    }

}
