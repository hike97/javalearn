package com.atguigu.java8_lambda;

/**
 * @author hike97
 * @create 2018-09-06 9:04
 * @desc
 **/

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8 内置四大核心函数式接口
 * Cosumer<T>: 消费型接口
 *      void accept(T t);
 * Supplier<T>: 供给型接口
 *      T get();
 *  Function<T,R>: 函数型接口
 *      R apply(T t);
 *  Predicate<T>: 断言型接口
 *      booolean test(T t);
 */
public class TestLambda3 {
    //Predicate<T> 断言型接口：
    @Test
    public void test_4() {
        List<String> list = Arrays.asList( "Hello", "aguigu", "Lambda", "www", "ok" );
        List<String> filterStr = filterStr( list, s -> s.length() > 3 );
        filterStr.stream().forEach( System.out::println );

    }
    //需求：将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> pre){
        List<String> strList = new ArrayList<>();
        for (String s : list) {
            if (pre.test( s )){
                strList.add(s);
            }
        }
        return  strList;
    }
    //Function<T,R>函数型接口：
    @Test
    public void test_3() {
        String newStr = strHandaler( "\t\t\t贱人就是矫情", s -> s.trim());
        System.out.println( newStr );
        String newStr_ = strHandaler( "\t\t\t贱人就是矫情", s -> s.trim().substring( 0,2));
        System.out.println( newStr_ );
    }
    //需求 用于处理字符串
    public String strHandaler(String str, Function<String,String> fun){
        return fun.apply( str );
    }
    //供给型接口：Supplier<T>
    @Test
    public void test_2() {
        List<Integer> numList = getNumList( 10, () -> (int) (Math.random() * 100) );
        numList.stream().forEach( System.out::println );
    }
    //需求：产生制定个数的整数，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> sup){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }
    //消费型接口：有去无回 没有返回值
    @Test
    public void test_1() {
            happy( 10000,m-> System.out.println("你们刚哥喜欢大保健，每次消费"+m+"元") );
    }

    public void happy(double money, Consumer<Double> con){
        con.accept( money );
    }
}
