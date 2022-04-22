package com.itdom.cloud;

import ch.qos.logback.core.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ConfigApplication {
    private static final Logger logger = LoggerFactory.getLogger(ConfigApplication.class);
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(ConfigApplication.class, args);
        while (true) {
            String username = context.getEnvironment().getProperty("user.name");
            String age = context.getEnvironment().getProperty("user.age");
            String gender = context.getEnvironment().getProperty("user.gender");
            String email = context.getEnvironment().getProperty("user.email");
            logger.debug("username:{},age:{},gender:{},email:{}", username, age, gender,email);
           TimeUnit.SECONDS.sleep(1);
        }
    }
}
