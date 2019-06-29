package com.example.vltask19.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class AOPClass {
//    private static final Logger logger = Logger.getLogger(AOPClass.class);

    //配置切点
    @Pointcut("execution(* com.example.vltask19.controller.LoginController.UserLogin(..))")
    public void log() {
    }

    /*配置切面*/
    @Around("log()")
    public Object aroundExcution(ProceedingJoinPoint joinPoint/*连接点*/) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        System.out.println("=====================Around=====================：");
        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        if ( result.toString().equals("登录成功"))
        {
            System.out.println("登录成功一次");
        }
        else
        {
            System.out.println("登录失败一次");
        }
        return result;
    }
}
