package com.tsmoreland.spring.jdbcconsoledemo.config;

import jakarta.annotation.Resource;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.tsmoreland.spring.jdbcconsoledemo"})
public class DbConfig {

    @Resource
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        // storing in temp variables for easier inspection
        String url = environment.getRequiredProperty("spring.datasource.url");
        String driverClassName = environment.getRequiredProperty("spring.datasource.driver-class-name");
        String username = environment.getRequiredProperty("spring.datasource.username");
        String password = environment.getRequiredProperty("spring.datasource.password");

        String passwordFromEnv = System.getenv(password);
        if (!Strings.isEmpty(passwordFromEnv)) {
            password = passwordFromEnv;
        }

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}
