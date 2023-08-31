package dev.example.jpa.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    @Around(value = "@annotation(LogExecutionTime)") //어노테이션을 정의해주는것
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{ // 실행 전과 실행 후이기에 object를 사용
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();

        long execTime = System.currentTimeMillis() - startTime;

        log.trace("method ex in {}",execTime);
        return proceed;
    }

    @Before(value = "@annotation(LogParameters)") // 특정 시점 이전에만 적용할거라 void사용
    public void logParameters(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.trace("method description : [{}]",methodSignature.getMethod());
        log.trace("method name : [{}]",methodSignature.getName());
        log.trace("declaring class : [{}]",methodSignature.getDeclaringType());

        Object[] arguments = joinPoint.getArgs();
        if(arguments.length == 0){
            log.trace("no arguments");
        }
        for(Object argument : arguments){
            log.trace("arguments : [{}]",argument);
        }


    }

  //  @AfterReturning(value = "@annotation(LogReturn)", returning = "returnValue")
   // public void logResults(JoinPoint joinPoint, Object returnValue)


}
