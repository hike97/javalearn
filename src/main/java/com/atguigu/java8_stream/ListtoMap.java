package com.atguigu.java8_stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName ListtoMap
 * @Description TODO
 * @Author hike97
 * @Date 2022/2/23 11:15
 * @Version 1.0
 **/
public class ListtoMap {

    public static void main(String[] args) {
        List<String> list = Arrays.asList ("1", "2", "3");
        String loanType = "1";
        Map<String, Object> map = list.stream().collect(Collectors.toMap(Function.identity(), x->loanType));
        map.forEach((x, y) -> System.out.println("Key: " + x +", value: "+ y));
        Optional<Map.Entry<String, Object>> first = map.entrySet ().stream ().filter (x -> "1".equals (x.getKey ()) && "111".equals (x.getValue ())).findFirst ();
        System.out.println (first.isPresent ());

        // 将Stream转换成容器或Map
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Map<String, Integer> newMap = stream.collect(Collectors.toMap(x->x, String::length));

    }
}
