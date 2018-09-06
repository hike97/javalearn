package com.atguigu.java8_stream;

import com.atguigu.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author hike97
 * @create 2018-09-06 15:16
 * @desc 中间操作
 **/
public class TestStreamAPI2 {

    List<Employee> emps = Arrays.asList(
            new Employee( "佟刚", 18, '男', 0 ),
            new Employee( "宋红康", 20, '女', 5000 ),
            new Employee( "菜菜", 18, '女', 8000 ),
            new Employee( "李立超", 22, '男', 9000 ),
            new Employee( "封捷", 30, '男', 11000 ),
            new Employee( "封捷", 30, '男', 11000 ),
            new Employee( "封捷", 30, '男', 11000 )
    );
    /*
      三.排序
      sorted（Comparable） --自然排序
      sorted(Comparator com) -- 定制排序
     */
    @Test
    public void test_7() {
        List<String> list = Arrays.asList( "fff","aaa", "bbb", "ccc", "ddd", "eee" );
        list.stream().sorted().forEach( System.out::println );
        System.out.println("-----------------------------------------");
        //定制排序
        emps.stream().sorted( (e1,e2)->{
            if (e1.getAge().equals( e2.getAge() )){
                    return e1.getName().compareTo( e2.getName());
            }else{
                return e1.getAge().compareTo( e2.getAge() );
            }
        }).forEach( System.out::println );
    }
    /*
      二.映射
      map--接收lambda,将元素转换成其他形式或提取信息。
      接受一个函数作为参数，该函数会被应用到每个元素上，
      并将其影射成一个新的元素。
      flatMap--接受一个函数作为参数，将流中的每个值都换成另一个流，
      然后把所有流连接成一个流
      add(Object o) addAll(Collection c)
     */
    //类似例子
    @Test
    public void test_6() {
        List<String> list = Arrays.asList( "aaa", "bbb", "ccc", "ddd", "eee" );
        List list2 = new ArrayList();
        list2.add( 11 );
        list2.add( 22 );
        list2.addAll( list );
        System.out.println( list2 + "[addAll]" );
        list2.add( list );
        System.out.println( list2 + "[add]" );
    }
    @Test
    public void test_5() {
        List<String> list = Arrays.asList( "aaa", "bbb", "ccc", "ddd", "eee" );
        list.stream().map( String::toUpperCase ).forEach( System.out::println );
        System.out.println( "提取名字--------------" );
        emps.stream().map( employee -> employee.getName() ).forEach( System.out::println );

        Stream<Stream<Character>> streamInStream
                = list.stream().map( TestStreamAPI2::filterCharacter );
        //{{a,a,a},{b,b,b}}
        streamInStream.forEach( (sm)->{
            sm.forEach( System.out::println );
        });

        Stream<Character> characterStream =
                list.stream().flatMap( TestStreamAPI2::filterCharacter );
        //{a,a,a,b,b,b}
        characterStream.forEach( System.out::println );
    }
    /**
     * 流中流
     */
    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add( c );
        }
        return list.stream();
    }

    /*
     *一、筛选与切片
     * filter--接收lambda,从流中排除某些元素
     * limit--截断流，是元素不超过给定数量。
     * skip--跳过元素，返回一个扔掉了前n个元素的流。
     * 若流中元素不足n个，则返回一个空流。与limit（n）互补
     * distinct 筛选，通过流所生成元素的hashCode()和equals去除重复元素
     */
    //内部迭代：迭代操作由 Stream API 完成
    //外部迭代：iterator接口
    /**
     * 去重
     */
    @Test
    public void test_4() {
        emps.stream().filter
                ( (e)->{
                    //System.out.println( "必须重写hashCode 和equals 方法" );
                    return e.getSalary()>5000; })
                .distinct().forEach(System.out::println);
    }
    /**
     * 跳过元素
     *
     */
    @Test
    public void test_3() {
        emps.stream().filter
                ( (e)->{
                    //找到满足条件以后不执行后续迭代--&&
                    System.out.println( "短路" );
                    return e.getSalary()>5000; }).skip( 2 ).forEach(System.out::println);
    }
    /**
     * 截断流
     */
    @Test
    public void test_2() {
        emps.stream().filter
                ( (e)->{
                    //找到满足条件以后不执行后续迭代--&&
                    System.out.println( "短路" );
                    return e.getSalary()>5000; }).limit( 2 ).forEach(System.out::println);
    }
    @Test
    public void test_1() {
        //中间操作
        Stream<Employee> s = emps.stream().filter( (e) ->{
            System.out.println( "Stream API 的中间操作" );
            return  e.getAge() > 18;
        });
        //终止操作
        /**
         *   多个 中间操作可以连接起来形成一个 流水线，除非流水
             线上触发终止操作，否则 中间操作不会执行任何的 处理！
             而在 终止操作时一次性全部 处理，称为“惰性求值”。
         */
        s.forEach( System.out::println );
    }
}
