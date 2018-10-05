package com.atguigu.java_reflection;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author hike97
 * @create 2018-09-30 15:27
 * @desc 反射机制
 **/
public class TestReflection {
    //如何获取Class的实例（3种）
    @Test
    public void test_() throws ClassNotFoundException {
        //1.调用运行时类本身的.class属性
        Class<Person> clazz = Person.class;
        System.out.println( clazz.getName() );
        Class<String> clazz2 = String.class;
        System.out.println( clazz2 );
        //2.通过运行时类的对象获取
        Person p = new Person();
        Class<? extends Person> clazz3 = p.getClass();
        System.out.println( clazz3 );
        //3.通过Class的静态方法获取
        Class<?> aClass = Class.forName( "com.atguigu.java_reflection.Person" );
        System.out.println( aClass );
        //4.（了解）通过类加载器
        ClassLoader classLoader = this.getClass().getClassLoader();
        String className = "com.atguigu.java_reflection.Person";
        Class<?> loadClass = classLoader.loadClass( className );
        System.out.println( loadClass );


    }
    /*
     * java.lang.Class : 是反射的源头。
     * 我们创建了一个类，通过编译（javac.exe）,生成对应的.class 文件。
     * 之后我们使用java.exe(JVM类加载器完成的)加载此.class文件，
     * 加载到内存以后，就是一个运行时类，存放在缓存区。
     * 那么这个运行时类本身就是一个Class的实例。
     *  1.每一个运行时类只加载一次
     *  2.有了Class的实例以后 我们才可以进行如下的操作：
     *      1）创建对应运行时类的对象
     *      2）可以获取对应运行时类的完整结构
     *      （属性、方法、构造器、内部类、父类、所在的包、异常、注解、····）
     *      3）调用对应的运行时类的指定的结构（属性，方法，构造器）
     *      4）反射的应用：动态代理
     */
    @Test
    public void test_3() {
        Person p = new Person();
        //通过运行时类的对象，调用其getClass() 返回其运行时类
        Class<? extends Person> clazz = p.getClass();
        System.out.println( clazz );
    }
    //有了反射，可以通过反射创建一个类的对象，并调用其中的结构
    @Test
    public void test_2() throws IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class<Person> clazz = Person.class;
        Class<String> stringClass = String.class;
        //1.创建clazz对应的运行时类Person类的对象
        Person p = clazz.newInstance();
        System.out.println( p );
        //2.通过反射调用运行时类的指定的属性
            //2.1
        Field f1 = clazz.getField( "name" );
        f1.set( p,"LiuDeHua" );
        System.out.println( p );
            //2.2
        Field f2 = clazz.getDeclaredField( "age" );
        f2.setAccessible( true );
        f2.set( p,20 );
        System.out.println( p );
        //3.通过反射调用运行累的指定的方法
        Method method = clazz.getMethod( "show" );
        method.invoke( p);
        method = clazz.getMethod( "display", String.class);
        method.invoke( p,"USA" );
    }
    //反射之前，如何创建一个类的对象，并调用其中的方法，属性
    @Test
    public void test_1() {
        Person p = new Person();
        p.setAge( 10 );
        p.setName( "TangWei" );
        p.show();
        p.display("HK");
    }
}
