package com.atguigu.java8_;

import org.junit.Test;
import org.junit.internal.runners.statements.RunAfters;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author hike97
 * @create 2018-09-05 19:21
 * @desc 尚硅谷视频lambda
 *  lambda 表达式的基础语法：java8中引入一个新的操作符“->” 该操作符称为箭头操作符 或lambda操作符
 *      箭头操作符将lambda表达式拆分为 两部分
 *      左侧：lambda 表达式的参数列表
 *      右侧：lambda 表达式中所需执行的功能，即lambda 体
 * 口诀：
 *  上联：左右遇一括号省
 *  下联：左侧推断类型省
 *  横批：能省则省
 *
 *  二、lambda 表达式需要函数式接口的支持
 *  函数式接口：只有一个抽象方法的接口，成为函数式接口
 *  可以使用@FunctionalInterface
 *  测试是不是函数式接口
 **/
public class TestLambda {
    /*
        语法格式一：无参数 无返回值
        *      （）-> system.out.println("hello lambda")
     */
    @Test
    public void test_1() {
        final int num = 0;
        /*
            在局部内部类中 应用同级别的局部变量 该变量必须是final 1.7 之前
         */
        //匿名内部类
          Runnable r = new Runnable() {
              @Override
              public void run() {
                  System.out.println( "hello world" +num);
              }
          };
        System.out.println( "------------" );
        Runnable r1 = () -> System.out.println("hello Lambda!");

        r.run();
        r1.run();
    }
    /*
     *
     *   语法格式二：有一个参数 无返回值
     *      (x) -> System.out.println(x);
     *       /*
          语法格式三：若只有一个参数，小括号可以不写
     */

    @Test
    public void test_2() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept( "我打上硅谷无无" );
        con = x-> System.out.println(x);
        con.accept( "小括号可以不写" );
    }
    /*
     语法格式四：两个参数 有返回值 labmda体中有多条语句
     */
    /*
     语法格式五：如果只有一条语句 大括号和return 都可以省略不写
     */
    @Test
    public void test_3() {
        //多条语句必须写大括号
        Comparator<Integer> com = (x,y) ->{
            System.out.println( "函数式接口" );
            return Integer.compare( x,y );
        };
    }
    /*语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，
      数据类型，即“类型推断”。
     */
    @Test
    public void test_5() {
        //类型推断 in Java7
        String[] strs = {"aaa","bbb","ccc"};
        List<Object> arrayList = new ArrayList<>();
        //推断 in java8
        show( new HashMap<>() );
    }

    public void show(Map<String,Integer> map){

    }
    //需求：对一个数进行运算
    @Test
    public void test_6() {
        Integer result = operation( 100, (x) -> x * x );
        System.out.println( result );
        Integer operation = operation( 200, (y) -> y + 200 );
        System.out.println( "200+200="+operation );
    }

    public Integer operation(Integer num,MyFunction<Integer> myFunction){
        return myFunction.getValue( num );
    }
}
