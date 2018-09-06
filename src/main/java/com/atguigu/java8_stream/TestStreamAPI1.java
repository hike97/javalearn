package com.atguigu.java8_stream;

        import com.atguigu.entity.Employee;
        import org.junit.Test;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.stream.Stream;

/**
 * @author hike97
 * @create 2018-09-06 14:54
 * @desc
 *  1.创建Stream
 *
 *  2.中间操作
 *
 *  3.终止操作
 **/
public class TestStreamAPI1 {
    //创建Stream
    @Test
    public void test_1() {
        //1.通过Collection 系列集合提供的stream 或parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        //2.通过Arrays 中的静态方法stream获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream( emps );
        //3.通过Stream类中的静态方法of()
        Stream<String> stream2 = Stream.of( "aa", "bb", "cc" );
        //4.创建无限流
        //迭代
        Stream<Integer> stream3 = Stream.iterate( 0, (x) -> x + 2 );
        stream3.limit( 10 ).forEach( System.out::println );
        //生成
        Stream.generate( ()->Math.random() ).limit( 5 ).forEach( System.out::println );
    }
}
