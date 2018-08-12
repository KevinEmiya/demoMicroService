package com.ky.danmaku.messagecenter.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeanResult {

    String value() default "";
}
