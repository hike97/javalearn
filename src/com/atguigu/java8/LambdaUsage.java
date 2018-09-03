package com.atguigu.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.LongPredicate;

/**
 * 常用lambda表达式接口
 * Predicate boolean test(T t);
 * Consumer accept(T t);
 * Function(T,R) R apply(T t);
 * Supplier<T> T get();
 */
public class LambdaUsage {
    /**
     * 区分什么是funcitonalInterface
     * 只有一个方法（非deafault方法）接口
     * 可以用lambda表达式
     */
    @FunctionalInterface
    public  interface  Adder{
        int add(int a ,int b);
    }

    /**
     * 非functionalInterface
     * 继承了Adder 但拥有自己的方法 一共两个方法 所以非fuctional接口
     */
//    @FunctionalInterface
//    public interface SmartAdder extends  Adder{
//        int add(long a , long b);
//    }

    @FunctionalInterface
    public interface Nothing_这也是接口 extends Adder{

    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Predicate 用法 传Object
     * @param source
     * @param predicate
     * @return
     */
    private static List<Apple> filter(List<Apple> source, LambdaExpression.Predicate<Apple> predicate){
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple : source) {
            if (predicate.test( apple )){
                result.add( apple );
            }
        }
        return result;
    }

    /**
     * LongPredicate 用法 传Long类型
     * @param source
     * @param predicate
     * @return
     */
    private static List<Apple> filterByWeight(List<Apple> source, LongPredicate predicate){
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple : source) {
            if (predicate.test( apple.getWeight() )){
                result.add( apple );
            }
        }
        return result;
    }

    /**
     * BIPredicate
     * @param source
     * @param predicate
     * @return
     */
    private static List<Apple> filterByBiPredicate(List<Apple> source, BiPredicate<String,Integer> predicate){
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple : source) {
            if (predicate.test( apple.getColor(),apple.getWeight())){
                result.add( apple );
            }
        }
        return result;
    }
/////////////////////////////////////////////////////////////////////////////CONSUMER//////////////////////////////////////////////////////////
    /**
     * consumer 使用
     * @param source
     * @param consumer
     */
    private static void  simpleTestConsumer(List<Apple> source, Consumer<Apple> consumer) {
        for (Apple apple : source) {
           consumer.accept(apple);
        }
    }
    private static void  simpleTestBIConsumer(String c, List<Apple> source, BiConsumer<Apple, String> consumer) {
        for (Apple apple : source) {
           consumer.accept(apple,c);
        }
    }
////////////////////////////////////////////////////////////////////////////FUNCTION//////////////////////////////////////////////////////////
    private static String  testFunction(Apple apple , LambdaExpression.Function<Apple,String> fun) {
        return fun.apply( apple );
    }
    public static void main(String[] args) {
        /*Runnable r1 = () -> System.out.println("Hello");
        Runnable r2 = new Runnable() {

            @Override
            public void run() {
                System.out.println( "Hello" );
            }
        };
        process( r1 );
        process( r2 );
        process( ()-> System.out.println("Hello") );*/
        List<Apple> list = Arrays.asList(
                new Apple( "green", 150 ),
                new Apple( "blue", 170 ),
                new Apple( "green", 180 ),
                new Apple( "yellow", 180 ));

        List<Apple> green = filter( list, apple -> apple.getColor().equals( "green" ) );
        System.out.println( "predict查询_查询为绿色的apple:"+green );

        //longpredicte
        List<Apple> result = filterByWeight( list, w -> w > 100 );
        System.out.println( "predict查询_查询重量大于100的apple:"+result );

        result = filterByBiPredicate( list, (s,w) -> s.equals( "green" ) && w > 100 );
        System.out.println( "predict查询_查询绿色+重量大于100的apple:"+result );
        System.out.println( "==========" );
        simpleTestConsumer( list,apple -> System.out.println(apple) );
        simpleTestBIConsumer( "XXX",list,(a,s)->
                System.out.println(s + a.getColor() + ":weight=>"+a.getWeight()));

        String yellow = testFunction( new Apple( "yellow", 100 ), apple -> apple.toString() );
        System.out.println( "yellow:"+yellow );
    }
    public static void  process(Runnable r){
        r.run();
    }
}
