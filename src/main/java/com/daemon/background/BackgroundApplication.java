package com.daemon.background;

import com.daemon.background.dynamic.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author dell
 */
@SpringBootApplication

@EnableAutoConfiguration
@Import(DynamicDataSourceRegister.class)
@MapperScan("com.daemon.background.dao")
@EnableTransactionManagement
@EnableScheduling
public class BackgroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackgroundApplication.class, args);
    }

}
