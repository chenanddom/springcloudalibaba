package com.itdom.cloud.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    @SentinelResource(RESOURCE_NAME_1)
    @GetMapping("/getRules")
    public Object getRules(){
        return new HashMap<String,String>(){{
            put("key1","value1");
            put("key2","value2");
            put("key3","value3");
            put("key4","value4");
        }};
    }



}

