package com.atguigu.javaSE_singleton;

/**
 * @author hike97
 * @create 2018-09-20 11:06
 * @desc java 单例模式之饿汉式
 **/
public class Hungry {
    /**
     * 总结：共同点
     *      * 1,定义私有的构造方法，禁止外部直接创建实例
          *
         * 2,内部自己创建好实例，私有属性(不建议在外部直接调用我们的成员变量)
          *
           * 3,创建一个方法，使外部可以得到此实例
        区别：
     1.饿汉式是线程安全的,在类创建的同时就已经创建好一个静态的对象供系统使用,以后不在改变。懒汉式如果在创建实例对象时不加上synchronized则会导致对对象的访问不是线程安全的。
     2.从实现方式来讲他们最大的区别就是懒汉式是延时加载,
        他是在需要的时候才创建对象,而饿汉式在虚拟机启动的时候就会创建，
            饿汉式无需关注多线程问题、写法简单明了、能用则用。
             但是它是加载类时创建实例(上面有个朋友写错了)、所以如果是一个工厂模式、缓存了很多实例、
                那么就得考虑效率问题，因为这个类一加载则把所有实例不管用不用一块创建。
     */
    private Hungry(){

    }

    //类加载的时候进行初始化
    private static  Hungry hungry=new Hungry();

    public Hungry getInstance(){
        //返回一个对象实例
        return hungry;
    }
}
//懒汉式
class Lazy{
    private Lazy() {
    }

    //创建一个私有的对象，但是不进行初始化
    private static Lazy lazy = null;

    //得到自己的实例，判断是否为空，为空则创建
    public static Lazy getInstance() {
        if (lazy == null) {
            lazy = new Lazy();
        }
        return lazy;
    }
}
