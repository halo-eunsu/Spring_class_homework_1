package com.nhnacademy.edu.springframework.project.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

    private static final Log log = LogFactory.getLog(LoggingAspect.class);
    @Pointcut("execution(* com.nhnacademy.edu.springframework.project.service.*.*(..))")
    public void serviceLayer() {}

    @Around("serviceLayer()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        Object result;

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();


        try {
            result = joinPoint.proceed();
        } finally {
            stopWatch.stop();
            log.info(String.format("%s.%s %dms", joinPoint.getTarget().getClass().getSimpleName(), methodName, stopWatch.getTotalTimeMillis()));

        }
        return result;
    }
}
