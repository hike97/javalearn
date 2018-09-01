package com.atguigu.java8;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaExpression {

    public static void main(String[] args) {
        Comparator<Apple> byColor = new Comparator<Apple>(){
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo( o2.getColor());
            }
        };
        List<Apple> list = Collections.emptyList();
        list.sort( byColor );

        //lambda表达式
        Comparator<Apple> byColor2 = ((o1, o2) -> o1.getColor().compareTo( o2.getColor() ));

        Function<String,Integer> flambda = s -> s.length();

        Function<Apple,Boolean> e = (apple-> apple.getColor().equals( "blue" ));
        //等同于
        Predicate<Apple> predicate = (o->o.getColor().equals( "yellow" ));

        Supplier<Apple> apple = Apple::new;
    }
    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);
    }

    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }
}
