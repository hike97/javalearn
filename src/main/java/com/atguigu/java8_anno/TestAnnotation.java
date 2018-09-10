package com.atguigu.java8_anno;

import lombok.NonNull;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author hike97
 * @create 2018-09-10 11:01
 * @desc 重复注解
 **/
public class TestAnnotation {
    //checker framework
    //private @NonNull Object object = null;
    @Test
    public void test_() throws NoSuchMethodException {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method method = clazz.getMethod( "show" );
        MyAnnotation[] byType = method.getAnnotationsByType( MyAnnotation.class );
        for (MyAnnotation myAnnotation : byType) {
            System.out.println( myAnnotation.value() );
        }
    }
    @MyAnnotation("Hello")
    @MyAnnotation("World")
    public  void show(/*@MyAnnotation("abc") String str*/){

    }
}
