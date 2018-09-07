package com.atguigu.java8_stream;

import com.atguigu.entity.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hike97
 * @create 2018-09-06 19:29
 * @desc 终止操作
 **/
public class TestStreamAPI3 {

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
    /*
     *一.查找与匹配
     * allMatch--检查是否匹配所有元素
     * anyMatch--检查是否至少匹配一个元素
     * noneMatch--检查是否没有匹配所有元素
     * findFirst--返回第一个元素
     * findAny--返回流中元素任意元素
     * count--返回流中元素的总个数
     * max--返回流中最大值
     * min--返回流中最小值
     */
    @Test
    public void test_1() {

        boolean b = employees.stream().
                allMatch( employee -> employee.getStatus().equals( Employee.Status.BUSY ) );
        System.out.println( "是否都是BUSY:"+b );

        b=employees.stream().
                anyMatch( employee -> employee.getStatus().equals( Employee.Status.BUSY ));
        System.out.println( "是否至少一个元素是BUSY:" + b );
        //有匹配的元素
        b=employees.stream().
                noneMatch( employee -> employee.getStatus().equals( Employee.Status.BUSY ));
        System.out.println( "是否没有元素是BUSY:" + b );
    }

    @Test
    public void test_2() {
        //工资排序 返回第一个
        Employee employee = employees.stream().
                sorted( (e1, e2) -> -Double.compare( e1.getSalary(), e2.getSalary() ) )
                .findFirst().get();//返回optional get方法 返回对应对象
        System.out.println( employee );

        //只要空闲 就加入
        Optional<Employee> any = employees.stream().filter( e -> e.getStatus().equals( Employee.Status.FREE ) )
                .findAny();
        System.out.println( "随便找一个FREE的："+any.get() );

        Optional<Employee> any_parrell = employees.parallelStream().filter( e -> e.getStatus().equals( Employee.Status.FREE ) )
                .findAny();
        System.out.println( "并行Stream随便找一个FREE的："+any_parrell.get() );
    }

    @Test
    public void test_() {
        long count = employees.stream().count();
        System.out.println( "一共有多少人："+count );

        Optional<Employee> max = employees.stream().max( (e1, e2) -> Double.compare( e1.getSalary(), e2.getSalary() ) );
        System.out.println( "工资最大:"+max.get() );

        Optional<Double> min = employees.stream().map( Employee::getSalary ).min( Double::compare );
        System.out.println( "最低工资："+min.get() );

    }
    /*
        二.归约
        reduce（T identity,s）
        reduce(BiaryOperator)
        --可以将流中元素反复结合起来，得到一个值。
        map-reduce 方法 常用
     */
    @Test
    public void test_3() {
        List<Integer> list = Arrays.asList( 1,2,3,4,5,6,7,8,9,10 );
        Integer sum = list.stream().reduce( 0, (x, y) -> x + y );
        System.out.println( "1到10的和："+sum );
        System.out.println("----------------------------------------------");
        Optional<Double> reduce = employees.stream().map( Employee::getSalary ).reduce( Double::sum );
        System.out.println( "工资一共是：" + reduce );
    }
    /*
      收集
      collect --将流转换为其他形式。接受一个Collector接口的实现，用于给Stream元素做汇总的方法
     */
    @Test
    public void test_4() {
        List<String> collect = employees.stream().map( Employee::getName ).collect( Collectors.toList() );
        System.out.println( collect );
        System.out.println( "放入set中------" );
        Set<String> set = employees.stream()
                .map( Employee::getName ).collect( Collectors.toSet() );
        System.out.println( set );
        //定制返回容器
        HashSet<String> hs = employees.stream().map( Employee::getName )
                .collect( Collectors.toCollection( HashSet::new ) );
        hs.forEach( System.out::println );
    }

    @Test
    public void test_5() {
        //总数
        System.out.println( employees.stream().collect( Collectors.counting() ) );
        //平均值
        Double aDouble = employees.stream().
                collect( Collectors.averagingDouble( employee -> employee.getSalary() ) );
        System.out.println( "平均值是：" + aDouble );
        //总和
        employees.stream().collect( Collectors.summarizingDouble(Employee::getSalary ) );
        //工资最大值
        Optional<Employee> max_money = employees.stream().collect( Collectors.maxBy( (e1, e2) -> Double.compare( e1.getSalary(), e2.getSalary() )
        ) );
        System.out.println( "工资最大值:"+max_money.get() );

        //工资最小值
        Optional<Double> a_Double
                = employees.stream().map( Employee::getSalary )
                .collect( Collectors.minBy( Double::compare ) );
        System.out.println( a_Double.get() );
    }

    /**
     * 分组
     */
    @Test
    public void test_6() {
        Map<Employee.Status, List<Employee>> listMap
                = employees.stream().collect( Collectors.groupingBy( Employee::getStatus ) );
        System.out.println( listMap );
    }
    /**
     * 多级分组
     */
    @Test
    public void test_7() {
        Map<Employee.Status, Map<String, List<Employee>>> map = employees.stream()
                .collect( Collectors.groupingBy( Employee::getStatus
                        , Collectors.groupingBy( e -> {
                            if (e.getAge() <= 35) {
                                return "青年";
                            } else if (e.getAge() <= 50) {
                                return "中年";
                            } else {
                                return "老年";
                            }
                        } ) ) );
        System.out.println( map );
    }
    /**
     * 分区
     */
    @Test
    public void test_8() {
        Map<Boolean, List<Employee>> collect = employees.stream().collect( Collectors.partitioningBy( e -> e.getSalary() > 8000 ) );
        System.out.println( collect );
    }
    /**
     * 收集
     * collect --将刘转换为其他形式。接收一个Collector接口的实现，
     * 用于给Stream中元素做汇总的方法
     */
    @Test
    public void test_9() {
        DoubleSummaryStatistics dss = employees.stream().collect( Collectors.summarizingDouble( Employee::getSalary ) );
        System.out.println( "总数：" + dss.getSum() );
        System.out.println( "平均数：" + dss.getAverage() );
        System.out.println( "最大值：" + dss.getMax() );
    }

    /**
     * 连接字符串
     */
    @Test
    public void test_10() {
        String s = employees.stream().map( Employee::getName )
                .collect( Collectors.joining(",","<<<",">>>") );
        System.out.println( "把名字连接起来："+s );
    }
}
