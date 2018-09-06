package com.atguigu.java8_;

import com.atguigu.entity.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author hike97
 * @create 2018-09-05 20:37
 * @desc lambda练习
 **/
public class LambdaExercise {
    List<Employee> emps = Arrays.asList(
            new Employee( "佟刚", 18, '男', 0 ),
            new Employee( "宋红康", 20, '女', 5000 ),
            new Employee( "菜菜", 18, '女', 8000 ),
            new Employee( "李立超", 22, '男', 9000 ),
            new Employee( "封捷", 30, '男', 11000 )
    );
    /*
    需求一：调用Collections.sort()方法，通过定制排序比较两个Employee(先按年龄比，年龄相同按姓名比)，
    使用lambda作为参数传递
     */
    @Test
    public void test_() {
        Collections.sort( emps,(e1,e2)->{
            if (e1.getAge() == e2.getAge()){
                return  e1.getName().compareTo( e2.getName() );
            }else{
                return -Integer.compare( e1.getAge(),e2.getAge() );
            }
        });

        emps.stream().forEach( System.out::println );
    }
    /*
    需求二：声明函数式接口，接口中声明抽象方法，public String getValue(String str)
            声明类TestLambda,类中 方法是用借口作为参数，将一个字符串转换成大写，
            并作为方法的返回值
     */
    public String strHandler(String str , Myfunction_ mf){
        return mf.getValue( str );
    }
    @Test
    public void test_1() {
        String s = strHandler( "\t\t\t 我大尚硅谷威武", str -> str.trim() );
        System.out.println( s );
        //大小写转换
        String adsdsdsda = strHandler( "adsdsdsda", str -> str.toUpperCase() );
        System.out.println( adsdsdsda );
            /*
            方法引用
             */
            Function<String,String> function = String::trim;
            String apply = function.apply( "\t\t\t 我大尚硅谷威武" );
            System.out.println( "方法引用改写：" + apply );

            function = String::toUpperCase;
        String abcdefg = function.apply( "abcdefg" );
        System.out.println( "方法引用——大写"+abcdefg );
    }
    /**
     * 两个long
     *
     */
    public  void  op(Long l1,Long l2,MyFunction2<Long,Long> mf){
        System.out.println( mf.getValue( l1, l2 ) );
    }

    @Test
    public void test_7() {
        op( 100l,200l,(x,y)->x+y );
        op( 100l,200l,(x,y)->x*y );

        BiFunction<Integer,Integer,Integer> bf = Integer::compare;
        Integer result = bf.apply( 200, 100 );
        System.out.println( result );
    }
}
