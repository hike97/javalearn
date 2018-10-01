package com.atguigu.java_annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

//自定义注解
@Retention(RetentionPolicy.CLASS)//生命周期
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})//修饰范围
public @interface MyAnnotation {

    String value() default "hello";//成员变量
}
