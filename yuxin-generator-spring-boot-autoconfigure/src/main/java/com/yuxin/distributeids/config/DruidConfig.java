//package com.yuxin.distributeids.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Description
// * @Author shenshixi
// * @Date 2021/12/24 21:26
// * @Version 1.0
// */
//@Configuration
//@Slf4j
//public class DruidConfig {
//
//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    @ConditionalOnMissingBean
//    public DruidDataSource getDataSource() {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        log.info("数据库连接创建成功！");
//        log.info(druidDataSource.toString());
//        return druidDataSource;
//    }
//
//
//}
