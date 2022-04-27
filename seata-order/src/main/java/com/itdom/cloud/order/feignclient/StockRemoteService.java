package com.itdom.cloud.order.feignclient;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/stock")
public interface StockRemoteService {

    @PutMapping("/dec_stock")
    public Boolean decreaseStock(@RequestParam("commodityCode")String commodityCode, @RequestParam("count")Integer count);

}
