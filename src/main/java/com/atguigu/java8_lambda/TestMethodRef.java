package com.atguigu.java8_lambda;

import com.atguigu.entity.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * @author hike97
 * @create 2018-09-06 11:14
 * @desc
 * 一、方法引用
 * 若lambda 体中的内容有方法已经实现了，我们可以使用“方法引用”
 * （可以理解为方法引用是lambda表达式的另外一种表现形式）
 * 主要有三种语法格式
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名
 * 注意：
 *  ①lambda 体中调用方法的参数列表与返回值类型,
 *      要与函数式接口中抽象方法的函数列表和返回值类型保持一致
 *  ② 若 lambda 参数列表中的第一参数是实例方法的调用者，
 *      而第二个参数是实例方法的参数时，可以使用ClassName::method
 *  二、构造器引用
 *    格式：
 *    ClassName：：new
 *  注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致；
 *  三、数组引用：
 *  type::new
 **/
public class TestMethodRef {
    /**
     * 数组引用：
     */
    @Test
    public void test_6() {
        Function<Integer,String[]> fun = x -> new String[x];
        String[] apply = fun.apply( 10 );
        System.out.println( apply.length );

        fun = String[]::new;
        String[] app = fun.apply( 20 );
        System.out.println( app.length + "数组引用" );

    }
    /**
     * 构造器引用
     */
    @Test
    public void test_5() {
        Supplier<Employee> sup =()-> new Employee();
        sup = Employee :: new;
        System.out.println( sup.get() );
    }
    /**
     * 一个参数构造器引用
     */
    @Test
    public void test_() {
        Function<String,Employee> function = s->new Employee( s );
        function = Employee::new;
        Employee 小李 = function.apply( "小李" );
        System.out.println( 小李 );
        //两个参数构造器
        BiFunction<String,Integer,Employee> bifun = Employee::new;
    }
    /**
     * 类::实例方法名
     */
    @Test
    public void test_4() {
        BiPredicate<String,String> bp  = (x,y) -> x.equals( y );
        bp = String::equals;

    }
    /**
     * 类::静态方法名
     */
    @Test
    public void test_3() {
        Comparator<Integer> comparator = (x,y)->Integer.compare( x,y );

        comparator = Integer::compare;
        int i = comparator.compare( 2, 3 );
        System.out.println("类::静态方法名"+ i );
    }
    /**
     * 对象::实例方法名
     */
    @Test
    public void test_1() {
        Consumer<String> con = x-> System.out.println(x);
        /*
          注意：
                void accept(T t);
                public void println(String x);
                实现接口的参数列表和返回至类型与方法体中的方法保持一致
         */
        PrintStream printStream = System.out;
        Consumer<String> con1 = printStream :: println;

        Consumer<String> con2 = System.out::println;
        con2.accept( "实现接口的参数列表和返回至类型与方法体中的方法保持一致" );

    }

    @Test
    public void test_2() {
        Employee employee = new Employee();
        Supplier<String> supplier = ()->employee.getName();
        String s = supplier.get();
        System.out.println( s );

        supplier = employee::getName;
        System.out.println( "方法引用-》对象名：：实例名"+supplier.get() );
    }

}
