package com.itdom.cloud.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/getContent")
    public Object getContent(){
        if (logger.isDebugEnabled()){
            logger.debug("receive obtain request:{}",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
        }
//    return new HashMap<String,String>(){{put("test","test Content");}};
       return restTemplate.getForObject("http://localhost:8082/stock/getStock",Object.class);
    }

}
