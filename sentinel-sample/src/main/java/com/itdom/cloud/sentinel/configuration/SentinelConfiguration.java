package com.itdom.cloud.sentinel.configuration;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @SentinelResource注解需要使用该配置类
 */
@Configuration
public class SentinelConfiguration {
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    public static Object getRulesBlockHandler(BlockException e){
        e.printStackTrace();
        return new HashMap<String,String>(){{
            put("blockHandler","服务降级");}};
    }
}
