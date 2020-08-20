package com.weijunkang.aopdome.aspectj;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.weijunkang.aopdome.domain.CodeLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author weijunkang
 * @version 1.0
 * @description: 接口日志切面
 * @date 2020/8/19 11:20
 */
@Slf4j
@Aspect
@Component
public class ApiLogAspect {

    @Autowired
    private HttpServletRequest request;

    @Pointcut("@annotation( com.weijunkang.aopdome.annotation.ApiLog)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();

        CodeLog codeLog = new CodeLog();
        createCodeLog(joinPoint, startTime, codeLog);
//        SpringUtil.getBean().insertLog(codeLog);
        return result;
    }

    private void createCodeLog(ProceedingJoinPoint joinPoint, long startTime, CodeLog codeLog) {
        long endTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        JSONObject jsonObject = JSONUtil.parseObj(args[0]);
        codeLog.setChannelCode("XXX");
        codeLog.setIp(ServletUtil.getClientIP(request));
        codeLog.setParam(Convert.toStr(jsonObject));
        codeLog.setSpendTime((int) (endTime - startTime));
        codeLog.setStartTime(startTime);
        codeLog.setUrl(request.getRequestURL().toString());
    }


}
