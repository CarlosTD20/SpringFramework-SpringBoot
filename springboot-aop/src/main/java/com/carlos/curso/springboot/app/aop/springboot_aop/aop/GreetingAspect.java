package com.carlos.curso.springboot.app.aop.springboot_aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(2)
@Aspect
@Component
public class GreetingAspect {

    private Logger logger = LoggerFactory.getLogger(/*GreetingAspect.class*/ this.getClass());

    @Before("GreetingServicePointCut.greetingLoggerPointCut()")
    public void loggerBefore(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Antes: " + method + " con los argumentos: " + args);
    }

    @After("GreetingServicePointCut.greetingLoggerPointCut()")
    public void loggerAfter(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Después: " + method + " con los argumentos: " + args);
    }

    @AfterReturning("GreetingServicePointCut.greetingLoggerPointCut()")
    public void loggerAfterReturning(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Después de devolver: " + method + " con los argumentos: " + args);
    }

    @AfterThrowing("GreetingServicePointCut.greetingLoggerPointCut()")
    public void loggerAfterThrowing(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Después de lanzar la excepción: " + method + " con los argumentos: " + args);
    }

    @Around("GreetingServicePointCut.greetingLoggerPointCut()")
    public Object loggerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().getName();
        String args = Arrays.toString(proceedingJoinPoint.getArgs());

        Object result = null;
        try {
            logger.info("El método: " + method + "() con los argumentos: " + args);
            result = proceedingJoinPoint.proceed();
            logger.info("El método: " + method + "() devuelve el resultado: " + result);
        } catch (Throwable e) {
            logger.error("Error en la llamada al método: " + method + "()");
        }

        return result;
    }
}

