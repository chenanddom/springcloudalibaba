package com.itdom.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@MapperScan("com.itdom.cloud.stock.mapper")
public class SeataStockApplication {
    private static final Logger logger = LoggerFactory.getLogger(SeataStockApplication.class);
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(SeataStockApplication.class, args);
    }
}
