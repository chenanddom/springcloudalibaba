package com.itdom.cloud.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.itdom.cloud.sentinel.configuration.SentinelConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/sentinel")
public class SentinelController {
    private static final Logger logger = LoggerFactory.getLogger(SentinelController.class);
    public static final String RESOURCE_NAME_1 = "RULE";

    @PostConstruct
    public void initFlowRules() {
        List<FlowRule> flowRules = new ArrayList<FlowRule>();

        FlowRule rule = new FlowRule();
        rule.setResource(RESOURCE_NAME_1);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);
        flowRules.add(rule);
        FlowRuleManager.loadRules(flowRules);
    }

    /**
     * @return
     * @SentinelResource注解需要引入sentinel-annotation-aspectj value:就是资源的名称
     * blockHandler就是服务降级的处理方法，可以定义在同一个类里面，方法的修饰符是public
     * 可以定义在其他类里面使用blockHandlerClass即可,同时还需要配置blockHandler高速Sentinel使用的是哪个方法，默认是在当前类则不需要指定BlockHandlerClass
     * fallback :处理接口异常
     * 注意：如果同时指定了blockHandler和fallback，则blockHandler优先级高于fallback
     */
//    @SentinelResource(value = RESOURCE_NAME_1,blockHandler = "getRulesBlockHandler")
    @SentinelResource(value = RESOURCE_NAME_1, blockHandlerClass = {SentinelConfiguration.class}, blockHandler = "getRulesBlockHandler", fallback = "fallbackHandler")
    @GetMapping("/getRules")
    public Object getRules() {
        int i = 1 / 0;
        return new HashMap<String, String>() {{
            put("key1", "value1");
            put("key2", "value2");
            put("key3", "value3");
            put("key4", "value4");
        }};
    }
//    public Object getRulesBlockHandler(BlockException e){
//        e.printStackTrace();
//        return new HashMap<String,String>(){{
//            put("blockHandler","服务降级");}};
//    }

    public Object fallbackHandler(Throwable e) {
        e.printStackTrace();
        return new HashMap<String, String>() {{
            put("blockHandler", "服务异常");
        }};
    }

    @GetMapping("/add")
    public String add() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(10);

        return "添加成功";
    }

    @GetMapping("/threadControl")
    @SentinelResource(value = "threadControl",blockHandler = "threadControlHandler")
    public String threadControl() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return "线程控制";
    }
    public String threadControlHandler(BlockException e){
        e.printStackTrace();
        return "被控制了";

    }
    @GetMapping("/getContentById/{id}")
    @SentinelResource(value = "getContentById",blockHandler = "getContentByIdBlockHandler")
    public String getContentById(@PathVariable("id")Integer id){
        return "内容:"+id;
    }


    public String getContentByIdBlockHandler(@PathVariable("id")Integer id,BlockException e){
        e.printStackTrace();
        return "熔断了:"+id;
    }
    @GetMapping("/systemProtectRule")
    public String systemProtectRule(){
        return "系统保护规则测试";
    }
}

