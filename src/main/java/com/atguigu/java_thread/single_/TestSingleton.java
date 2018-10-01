package com.atguigu.java_thread.single_;

/**
 * @author hike97
 * @create 2018-09-20 10:37
 * @desc 懒汉模式
 **/
//关于懒汉式的线程安全问题：使用同步机制
    //对于一般的方法内，使用同步代码块，可以考虑使用this.
    //对于静态方法而言，使用当前类本身充当锁
class Singelton{
    private Singelton(){

    }
    private static Singelton instance = null;
    public static Singelton getInstace(){
        if (instance == null){
            synchronized (Singelton.class){
                if (instance == null){
                    instance = new Singelton();
                }
            }
        }
        return instance;
    }
}
public class TestSingleton {

    public static void main(String[] args) {
        Singelton s1 = Singelton.getInstace();
        Singelton s2 = Singelton.getInstace();
        System.out.println( s1 == s2 );
        Class clazz = Singelton.class;
    }
}
