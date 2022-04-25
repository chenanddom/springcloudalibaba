package com.itdom.cloud.sentinel.component;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {

        String msg = null;

        if (e instanceof FlowException) {
            msg = "访问频繁，请稍候再试";

        } else if (e instanceof DegradeException) {
            msg = "系统降级";

        } else if (e instanceof SystemBlockException) {
            msg = "系统规则限流或降级";

        } else if (e instanceof AuthorityException) {
            msg = "授权规则不通过";

        } else {
            msg = "未知限流降级";
        }
        // http状态码
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");

        httpServletResponse.getWriter().write(msg);
    }
}
