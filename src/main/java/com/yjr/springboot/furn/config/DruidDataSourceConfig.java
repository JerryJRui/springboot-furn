package com.yjr.springboot.furn.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class DruidDataSourceConfig {
    //配置druid数据源
    @Bean
    @ConfigurationProperties("spring.datasource") //读取application.properties中配置的属性
    public DataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        log.info("druidDataSource:{}",druidDataSource.getClass());
        return druidDataSource;
    }
}
