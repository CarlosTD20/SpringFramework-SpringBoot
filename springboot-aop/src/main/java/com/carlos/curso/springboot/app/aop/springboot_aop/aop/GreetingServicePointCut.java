package com.carlos.curso.springboot.app.aop.springboot_aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingServicePointCut {

    /*
        com.carlos.curso.springboot.app.aop.springboot_aop..*.*(..)
        springboot.app.aop.springboot_aop.service/controller/repository/etc.clase.metodos(argmuentos)
        springboot_aop..*. -> ringboot_aop.service/controller/repository/etc.clase
        al poner astericos los detecta todos
    */

    @Pointcut("execution(* com.carlos.curso.springboot.app.aop.springboot_aop..*.*(..))")
    public void greetingLoggerPointCut() {
    }

    @Pointcut("execution(* com.carlos.curso.springboot.app.aop.springboot_aop..*.*(..))")
    public void greetingFooLoggerJointCut() {
    }
}
