package com.aliasgarli.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class DbConfig {

//    @Bean("hrb")
//    @ConfigurationProperties(prefix = "spring.datasource.hrb")
//    public DataSource hrbDb(){
//        DataSource hikariDataSource= DataSourceBuilder.create().build();
//        return hikariDataSource;
//    }
}
