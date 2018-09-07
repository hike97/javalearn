package com.atguigu.java8_stream;

import com.atguigu.entity.Employee;
import com.atguigu.entity.Godness;
import com.atguigu.entity.Man;
import com.atguigu.entity.NewMan;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * @author hike97
 * @create 2018-09-07 16:49
 * @desc optional
 **/
public class TestOptional {
    /*
     * 一、Optional 容器类：用于尽量避免空指针异常
     * 	Optional.of(T t) : 创建一个 Optional 实例
     * 	Optional.empty() : 创建一个空的 Optional 实例
     * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
     * 	isPresent() : 判断是否包含值
     * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
     * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
     * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
     * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
     */

    @Test
    public void test_5() {
//        Man man = new Man();
//        String godnessName = getGodnessName( man );
//        System.out.println( godnessName );
        //
        Optional<Godness> gn = Optional.ofNullable( new Godness( "波多老师" ));
        Optional<NewMan> op = Optional.ofNullable( new NewMan( gn ) );

        String godnessName1 = getGodnessName2( op );
        System.out.println( godnessName1 );
    }
    public String getGodnessName2(Optional<NewMan> man){
        return man.orElse( new NewMan())
                .getGodness().orElse( new Godness("苍老师") ).getName();
    }
    //需求：获取一个男人心中女神的名字
    public String getGodnessName(Man man){
        if (man != null){
            Godness godness = man.getGodness();
            if (godness!=null){
                return godness.getName();
            }
        }
        return "苍老师";
    }
    @Test
    public void test_4() {
        Optional<Employee> op = Optional.ofNullable(new Employee( "xiaowangba", 10, '男', 999, Employee.Status.FREE ));
        //将容器中的对象放入到map函数之中
        Optional<String> s = op.map( e -> e.getName() );
        System.out.println( s.get() );
        //返回的值必须是optional防止空指针异常
        Optional<String> s1 = op.flatMap( e -> Optional.of( e.getName()));
        System.out.println( s1 );
    }
    @Test
    public void test_3() {
        /*
          public static <T> Optional<T> ofNullable(T value) {
            return value == null ? empty() : of(value);
            }
         */
        Optional<Employee> op = Optional.ofNullable( null );
        if (op.isPresent()){
            System.out.println( op.get() );
        }else {
            Employee employee = op.orElse( new Employee( "dawangba", 10, '男', 999, Employee.Status.FREE ) );
            System.out.println( employee );
        }
        //可以写功能
        Employee employee = op.orElseGet( () -> new Employee() );
        System.out.println( employee );
    }
    @Test
    public void test_2() {
        Optional<Object> empty = Optional.empty();
        System.out.println( empty.get() );
    }
    @Test
    public void test_1() {
        //Optional.of()若报空指针 那么代码区间在括号内
        Optional<Employee> op = Optional.of( new Employee());
        Employee emp = op.get();
        System.out.println( emp );
    }
}
