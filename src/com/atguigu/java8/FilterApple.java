package com.atguigu.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterApple {

    public static List<Apple> findGreenApple(List<Apple> apples){
        ArrayList<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals( apple.getColor() )){
                list.add( apple );
            }
        }
        return list;
    }

    //方法拓展
    public static List<Apple> findGreenApple(List<Apple> apples,String color){
        ArrayList<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (color.equals( apple.getColor() )){
                list.add( apple );
            }
        }
        return list;
    }

    //创建一个applefilter
    @FunctionalInterface
    public  interface  AppleFilter{
        boolean filter(Apple apple);
    }

    public static List<Apple> findAppleByCondition(List<Apple> apples,AppleFilter appleFilter){
        ArrayList<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (appleFilter.filter( apple )){
                list.add( apple );
            }
        }
        return list;
    }

    public static class FindGreenAnd150Filter implements AppleFilter{
        @Override
        public boolean filter(Apple apple) {
            return (apple.getColor().equals( "green" )&&apple.getWeight()==150);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Apple> list = Arrays.asList(
                new Apple( "green", 150 ),
                new Apple( "blue", 170 ),
                new Apple( "green", 180 ),
                new Apple( "yellow", 180 ));
//        List<Apple> greenApples = findGreenApple( list );
//        //断言
//        assert greenApples.size() == 1;
//        System.out.println( greenApples );
        /**
         * 拓展可选颜色
         */
//        List<Apple> greenApple = findGreenApple( list, "green" );
//        System.out.println( greenApple );
//        List<Apple> redApple = findGreenApple( list, "red" );
//        System.out.println( redApple );
        /**
         * 拓展选择各种条件
         * 1.创建一个fiter
         * 2.创建filter实例
         * 3.将apple 和 filter  放入方法中
         */
        List<Apple> apple = findAppleByCondition( list, new FindGreenAnd150Filter() );
        System.out.println( apple );

        List<Apple> yellowResult = findAppleByCondition( list, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                return "yellow".equals( apple.getColor() );
            }
        });
        System.out.println( yellowResult );

//        List<Apple> lambdaResult = findAppleByCondition( list, (Apple apple_) -> {
//            return apple_.getColor().equals( "blue" );
//        });
        List<Apple> lambdaResult = findAppleByCondition( list, (Apple apple_) -> apple_.getColor().equals( "blue" )); /*也可以这样写*/

        System.out.println( "lambdaResult："+lambdaResult );

//        Thread thread = new Thread( new Runnable() {
//            @Override
//            public void run() {
//                System.out.println( Thread.currentThread().getName() );
//            }
//        });
//        thread.start();

        new Thread( new Runnable() {
            @Override
            public void run() {
                System.out.println( Thread.currentThread().getName() );
            }
        } ).start();

        new Thread( ()-> System.out.println(Thread.currentThread().getName())).start();
        Thread.currentThread().join();
    }
}
