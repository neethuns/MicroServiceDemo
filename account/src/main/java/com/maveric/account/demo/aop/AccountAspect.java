package com.maveric.account.demo.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AccountAspect {
    private final Logger log = LoggerFactory.getLogger(AccountAspect.class);

    @Before(value = "execution(* com.maveric.account.demo.controller.*.*(..))")
    public void logStatementBefore(JoinPoint joinPoint) {
        log.info("Executing controller {}", joinPoint);
    }

    @After(value = "execution(* com.maveric.account.demo.controller.*.*(..))")
    public void logStatementAfter(JoinPoint joinPoint) {
        log.info("Complete execution of controller {}", joinPoint);
    }

    @AfterReturning(value = "execution(* com.maveric.account.demo.controller.*.*(..))", returning = "result")
    public void logStatementAfterReturning(JoinPoint joinpoint, Object result) {
        log.info("Executing Controller {} Returned with value {}", joinpoint, result);
    }

    @Before(value = "execution(* com.maveric.account.demo.service.*.*(..))")
    public void logStatementBeforeService(JoinPoint joinpoint) {
        log.info("Executing Service {}", joinpoint);
    }

    @After(value = "execution(* com.maveric.account.demo.service.*.*(..))")
    public void logStatementAfterService(JoinPoint joinpoint) {
        log.info("Complete execution of  Service {}", joinpoint);
    }

    @Around(value = "execution(* com.maveric.account.demo.service.*.*(..))")
    public Object taskHandler(ProceedingJoinPoint joinPoint) throws Throwable {


        return joinPoint.proceed();

    }

    @Around(value = "execution(* com.maveric.account.demo.controller.*.*(..))")
    public Object timeTracker(ProceedingJoinPoint joinPoint) throws Throwable {

        long stratTime = System.currentTimeMillis();

        Object obj = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - stratTime;
        log.info("Time taken by {} is {} ms", joinPoint, timeTaken);
        return obj;

    }
}
