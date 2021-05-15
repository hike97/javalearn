package com.interview.java1.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * 动态代理的举例
 *
 * @author shkstart
 * @create 2019 上午 10:18
 */

interface Human{

    String getBelief ();

    void eat (String food);

}
//被代理类
class SuperMan implements Human{


    @Override
    public String getBelief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }
}

class HumanUtil{

    public static void method1(){
        System.out.println("====================通用方法一====================");

    }

    public static void method2(){
        System.out.println("====================通用方法二====================");
    }

}

/*
要想实现动态代理，需要解决的问题？
问题一：如何根据加载到内存中的被代理类，动态的创建一个代理类及其对象。
问题二：当通过代理类的对象调用方法a时，如何动态的去调用被代理类中的同名方法a。


 */

class ProxyFactory{
    /**
     * 返回一个代理类对象
     * @param obj 被代理对象
     * @return
     */
    public static Object getProxyInstance(Object obj){
        MyInvocationHandler handler = new MyInvocationHandler ();
        handler.bind(obj);
        return Proxy.newProxyInstance (obj.getClass ().getClassLoader (),obj.getClass ().getInterfaces (),handler);
    }


}

class ProxyFactoryLambda{
    public static Object getProxyInstance(Object obj){
        return Proxy.newProxyInstance (obj.getClass ().getClassLoader (), obj.getClass ().getInterfaces (),
                (proxy, method, args) ->{
                    HumanUtil.method1 ();
                    Object returnVal = method.invoke (obj, args);
                    HumanUtil.method2 ();
                    return returnVal;
                });
    }
}

class MyInvocationHandler implements  InvocationHandler {

    private Object obj;

    public void bind(Object obj){
        this.obj = obj;
    }
    //bind 被代理对象

    @Override
    public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke (obj,args);
    }
}


public class ProxyTest {

    public static void main(String[] args) {
        SuperMan superMan = new SuperMan ();
        //代理类的对象
        Human proxyInstance = (Human) ProxyFactoryLambda.getProxyInstance (superMan);
//        SuperMan proxyInstance = (SuperMan) ProxyFactoryLambda.getProxyInstance (superMan);
        System.out.println (proxyInstance.getBelief ());
        proxyInstance.eat ("四川麻辣烫");

    }
}
