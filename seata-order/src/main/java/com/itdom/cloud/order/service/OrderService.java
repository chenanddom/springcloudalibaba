package com.itdom.cloud.order.service;

import com.itdom.cloud.order.feignclient.StockRemoteService;
import com.itdom.cloud.order.mapper.TOrderMapper;
import com.itdom.cloud.order.model.TOrder;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private TOrderMapper orderMapper;
    @Autowired
    private StockRemoteService stockRemoteService;
    @Transactional
    @GlobalTransactional
    public Object add(){
        TOrder tOrder = new TOrder();
        tOrder.setUserId("1");
        tOrder.setAmount(10.00);
        tOrder.setOrderNo(UUID.randomUUID().toString().replace("-",""));
        tOrder.setCommodityCode("1001");
        tOrder.setCount(1);
        orderMapper.createOrder(tOrder);
        Boolean aBoolean = stockRemoteService.decreaseStock(tOrder.getCommodityCode(), tOrder.getCount());
        double a = 1/0;
        return "成功";
    }
}
