package com.atguigu.java_annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hike97
 * @create 2018-09-30 11:04
 * @desc 注解
 **/
public class TestAnnotation {
    /*
     *1.JDK三个基本的annotation：
         *  @Override 该注释只能用于方法
         *  @Deprecated 用于表示某个程序元素已过时
         *  @SuppressWarnings 抑制编译器警告
     *2.如何自定义一个注解
     *3.元注解：修饰注解的注解
     *  @Documented
     *      生成文档会被识别
        @Retention
            指明注解可以保留多长时间--生命周期
            SOURCE,CLASS,RUNTIME
        @Target
            用于什么范围
        @Inherited
            注解具有继承性
     */
    public static void main(String[] args) {
        Person student = new Student();
        student.walk();
        student.eat();
        @SuppressWarnings( {"rawtypes","unused"})
        List list = new ArrayList<>();
    }
}
@MyAnnotation(value = "varcao")
class Student extends Person{
    @Override
    public void walk() {
        System.out.println("xuesheng zou lu");
    }

    @Override
    public void eat() {
        super.eat();
    }
}
@Deprecated
class Person{

    String name;
    int age;
    @MyAnnotation(value = "varcao")
    public Person() {
    }
    @MyAnnotation(value = "varcao")
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void walk(){
        System.out.println("走路");
    }
    @Deprecated
    public void eat(){
        System.out.println("走路");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
