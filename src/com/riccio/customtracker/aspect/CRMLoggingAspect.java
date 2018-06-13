package com.riccio.customtracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    //setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    //setup pointcut declarations
    @Pointcut("execution(* com.riccio.customtracker.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.riccio.customtracker.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("execution(* com.riccio.customtracker.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
    private void forAppFlow(){}


    //add @Before
    @Before("forAppFlow()")
    public void  before(JoinPoint joinPoint){

        // display method we are calling
        String methodName = joinPoint.getSignature().toShortString();
        myLogger.info("----> in @Before : calling method :" +methodName);

        // display the arguments to the method
        Object[] args = joinPoint.getArgs();

        for (Object tempArg : args){
            myLogger.info("----> arguments : "+tempArg);
        }


    }

    //add @AfterReturning
    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){

        //display method we are returning from
        String methodName = joinPoint.getSignature().toShortString();
        myLogger.info("----> in @AfterReturning : calling method :" +methodName);

        // display the arguments to the method
        Object[] args = joinPoint.getArgs();

        for (Object tempArg : args){
            myLogger.info("----> @AfterReturning arguments : "+tempArg);
        }

        //display data returned




    }
}
