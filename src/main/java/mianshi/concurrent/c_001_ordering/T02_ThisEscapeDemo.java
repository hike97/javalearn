package mianshi.concurrent.c_001_ordering;

/**
 * @ClassName T02_ThisEscapeDemo
 * @Description this 引用逃逸
 * @Author hike97
 * @Date 2023/3/23 18:57
 * @Version 1.0
 **/
public class T02_ThisEscapeDemo {
    final int value;

    public T02_ThisEscapeDemo() {
        SomeClass.register(this); // 构造函数中将this引用传递给其他方法
        this.value = 42;
    }

    public static T02_ThisEscapeDemo create() {
        T02_ThisEscapeDemo example = new T02_ThisEscapeDemo();
        SomeClass.register(example);
        return example;
    }

    public static void main(String[] args) {
        T02_ThisEscapeDemo escape = new T02_ThisEscapeDemo();
        System.out.println(escape.value);
    }
}

class SomeClass {
    public static void register(T02_ThisEscapeDemo example) {
        // 在这里访问了未构造完成的EscapeExample对象
        System.out.println(example.value);
    }
}
