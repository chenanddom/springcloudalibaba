package com.itdom.cloud.order.controller;

import com.itdom.cloud.order.feignclient.StockRemoteService;
import com.itdom.cloud.order.mapper.TOrderMapper;
import com.itdom.cloud.order.model.TOrder;
import com.itdom.cloud.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class TOrderController {

@Autowired
private OrderService orderService;
@PostMapping("/add")
 public Object add(){
    return orderService.add();
}
}
