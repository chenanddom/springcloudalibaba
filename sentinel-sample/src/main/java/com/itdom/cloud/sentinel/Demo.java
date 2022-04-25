package com.itdom.cloud.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static final String RESOURCE_NAME_1="DEMO1";
    public static void main(String[] args) throws InterruptedException {
        initFlowRules();
        while (true) {
            // 1.5.0 版本开始可以直接利用 try-with-resources 特性
            try (Entry entry = SphU.entry(RESOURCE_NAME_1)) {
                // 被保护的逻辑
                demo();
            } catch (BlockException ex) {
                // 处理被流控的逻辑
                System.out.println("blocked!");
            }
                TimeUnit.MILLISECONDS.sleep(100L);
        }

    }
    public static void initFlowRules(){
        List<FlowRule> flowRules = new ArrayList<FlowRule>();

        FlowRule rule = new FlowRule();
        rule.setResource(RESOURCE_NAME_1);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(8);
        flowRules.add(rule);
        FlowRuleManager.loadRules(flowRules);
    }
//    @SentinelResource("DEMO1")
    public static void demo() {
        // 资源中的逻辑
        System.out.println("DEMO1...");
    }
}
