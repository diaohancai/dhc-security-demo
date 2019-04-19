package com.hancai.demo.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * restful api 切面拦截器
 * @author diaohancai
 */
@Aspect
@Component
public class TimeAspect {

    private Logger log = LoggerFactory.getLogger(TimeAspect.class);

    @Around("execution(* com.hancai.core.module.user.rest.UserResource.*(..))")
    public Object timeAspect(ProceedingJoinPoint pjp) throws Throwable {
        log.info("time aspect start");
        long start = System.currentTimeMillis();

        Object[] args = pjp.getArgs();
        if(args != null) {
            for(int i=0; i<args.length; ++i) {
                log.info("time aspect arg" + i + ": " + args[i]);
            }
        }

        Object result = pjp.proceed(); // 执行真正的业务逻辑
        log.info("time aspect result: " + result);

        long end = System.currentTimeMillis();
        log.info("time aspect end, cost " + (end - start) + "ms");

        return result;
    }

}
