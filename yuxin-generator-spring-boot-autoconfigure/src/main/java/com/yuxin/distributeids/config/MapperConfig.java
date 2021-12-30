package com.yuxin.distributeids.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author shenshixi
 * @Date 2021/12/29 11:04
 * @Version 1.0
 */
@Configuration
@MapperScan(basePackages = "com.yuxin.distributeids.mapper")
public class MapperConfig {

}
