package com.atguigu.java_enum;

/**
 * @author hike97
 * @create 2018-09-30 10:01
 * @desc jdk1.5之后枚举类
 **/

/**
 * 1.如何自定义枚举类
 * 2.如何使用enum关键字定义枚举类
 *  >常用的方法：values() valueOf(String name)
 *  >如何让枚举类实现接口：
 *      可以让不同的枚举类的对象调用被重写的抽象方法，
 *      执行的效果不同。
 */
public class TestSeanson_ {
    public static void main(String[] args) {
        //1. values 常用的方法 返回一个数组
        Season_[] season_s = Season_.values();
        for (int i = 0; i < season_s.length; i++) {
            System.out.println( season_s[i] );
        }
        //2. valueOf 返回enum 要求传入的形参是枚举类对象的名字
        //否则报异常
        Season_ spring = Season_.valueOf( "SPRING" );
        System.out.println( spring );
        Thread.State[] values = Thread.State.values();
        for (Thread.State value : values) {
            System.out.println( value );
        }

        spring.show();
        Season_.WINTER.show();

    }
}
interface  Info {
    void show();
}
enum Season_ implements Info{

    SPRING ( "spring","春暖花开" ){
        @Override
        public void show() {
            System.out.println( "川田在哪里？" );
        }
    },
    SUMMER ( "summer","夏日炎炎" ){
        @Override
        public void show() {
            System.out.println( "夏碧碧" );
        }
    },
    AUTUMN ( "autumn","秋高气爽" ){
        @Override
        public void show() {
            System.out.println( "秋天不回来" );
        }
    },
    WINTER ( "winter","白雪皑皑" ){
        @Override
        public void show() {
            System.out.println( "冬天里的一把火" );
        }
    };

    private final String seasonName;
    private final String seasonDesc;

    private Season_(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }


    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }

//    public void show(){
//        System.out.println( "这是一个季节" );
//    }
}
