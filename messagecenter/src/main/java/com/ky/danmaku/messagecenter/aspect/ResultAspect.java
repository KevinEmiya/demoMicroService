package com.ky.danmaku.messagecenter.aspect;

import com.ky.danmaku.messagecenter.common.ResultBean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class ResultAspect {

    @Around("@annotation(com.ky.danmaku.messagecenter.annotation.BeanResult)")
    public Object handledMemoize(ProceedingJoinPoint pjp) throws Throwable
    {
        return new ResultBean<>(pjp.proceed()).toString();
    }
}
