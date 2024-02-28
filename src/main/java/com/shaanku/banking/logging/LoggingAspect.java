package com.shaanku.banking.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.shaanku.banking.controller.*.*(..))")
    public void accountControllerAdavice(JoinPoint joinPoint) {
        log.info("Method Called: {}", joinPoint.getSignature().toShortString());
        log.info("Method Arguments: {}", joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* com.shaanku.banking.controller.*.*(..))", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        log.info("Method execution completed {}", joinPoint.getSignature().toShortString());
        log.info("Method Returned {}", result.toString());
    }
}
