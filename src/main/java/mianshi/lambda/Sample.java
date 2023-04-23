package mianshi.lambda;

import java.util.function.Function;

/**
 * @author: hike97
 * @createTime: 2023/04/22 16:38
 * @description: lambda 底层实现原理
 * 添加JVM参数：-Djdk.internal.lambda.dumpProxyClasses=.
 * Lambda表达式是基于JSR-292引入的动态语言调用包java.lang.invoke和Unsafe#defineAnonymousClass()定义的轻量级模板类实现的，
 * 主要用到了invokedynamic字节码指令，关联到方法句柄MethodHandle、调用点CallSite等相对复杂的知识点，这里不再详细展开。
 */
public class Sample {

    public static void main(String[] args) throws Exception {
        Runnable runnable = () -> System.out.println("Hello World!");
        runnable.run();
        String hello = "Hello ";
        Function<String, String> function = string -> hello + string;
        function.apply("Doge");
    }
}

