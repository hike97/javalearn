package com.atguigu.java8_stream;

import com.atguigu.entity.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author hike97
 * @create 2018-09-07 10:04
 * @desc stream练习
 **/
public class TestStreamAPI_ex {
    List<Employee> employees = Arrays.asList(
            new Employee("张三",18,'男',9999.99,Employee.Status.FREE),
            new Employee("李四",58,'男',5555.99,Employee.Status.BUSY),
            new Employee("王五",26,'男',3333.99,Employee.Status.VOCATION),
            new Employee("赵六",36,'男',6666.99,Employee.Status.FREE),
            new Employee("阿八",36,'男',6666.99,Employee.Status.FREE),
            new Employee("九喇嘛",36,'男',6666.99,Employee.Status.FREE),
            new Employee("重吾",36,'男',6666.99,Employee.Status.FREE),
            new Employee("sasuke",36,'男',6666.99,Employee.Status.FREE),
            new Employee("田七",12,'男',8888.99,Employee.Status.VOCATION)
    );
    /**
     * 1.给定一个数字列表，如何返回一个又没个数平方构成的列表
     */
    @Test
    public void test_1() {
        Integer[] nums = {1, 2, 3, 4, 5};
        Arrays.stream( nums ).map( x->x*x )
                .forEach( System.out::println );
    }
    /**
     * map和reduce 数一数 流中有多少Employee
     */
    @Test
    public void test_2() {
        Optional<Integer> reduce = employees.stream()
                .map( e -> 1 ).reduce( Integer::sum );
        System.out.println( "一共有人数：" + reduce.get() );
    }
}
