package com.itdom.cloud.stock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/stock")
public class StockController {
    @GetMapping("/getStock")
    public Object getStock(){
    return new HashMap<String,Object>(){{put("stcok","this is stock content");}};
    }


}
