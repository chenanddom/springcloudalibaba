package com.itdom.cloud.order.feignclient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(value = "seata-stock",fallbackFactory = StockFeignClient.StockFeignClientFallbackFactory.class)
public interface StockFeignClient extends StockRemoteService{
    @Component
    @Slf4j
    class StockFeignClientFallbackFactory implements FallbackFactory<StockFeignClient> {

        @Override
        public StockFeignClient create(Throwable throwable) {
            return new StockFeignClient() {
                @Override
                public Boolean decreaseStock(String commodityCode, Integer count) {
                    log.debug("服务压力山大熔断:{}",throwable.getCause());
                    return false;
                }
            };
        }
    }
}
