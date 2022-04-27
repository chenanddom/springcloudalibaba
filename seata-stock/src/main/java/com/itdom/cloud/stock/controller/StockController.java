package com.itdom.cloud.stock.controller;

import com.itdom.cloud.stock.mapper.TStockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/stock")
public class StockController {


    @Autowired
    private TStockMapper tStockMapper;
    /**
     * 扣减库存
     */
    @PutMapping("/dec_stock")
    public Boolean decreaseStock(@RequestParam("commodityCode")String commodityCode,@RequestParam("count")Integer count) {
        log.info("请求库存微服务：{}", commodityCode);
        return tStockMapper.decreaseStock(commodityCode,count)>0?true:false;
    }

}
