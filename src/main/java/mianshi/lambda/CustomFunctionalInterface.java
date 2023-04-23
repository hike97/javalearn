package mianshi.lambda;

/**
 * @author: hike97
 * @createTime: 2023/04/22 15:02
 * @description: lambda底层学习
 * https://www.throwx.cn/2020/02/09/java-understand-and-use-lambda/
 */
@FunctionalInterface
public interface CustomFunctionalInterface {

    public abstract void process();

    default void defaultVoidMethod() {

    }

    default String sayHello(String name) {
        return String.format("%s say hello!", name);
    }
}

