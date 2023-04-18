package mianshi.javabasic.javase.c_004_box;

/**
 * @author: hike97
 * @createTime: 2023/04/16 19:46
 * @description:
 * 基本数据类型的局部变量存放在 Java 虚拟机栈中的局部变量表中，
 * 基本数据类型的成员变量（未被 static 修饰 ）存放在 Java 虚拟机的堆中。
 * 包装类型属于对象类型，我们知道⼏乎所有对象实例都存在于堆中。
 */
public class IntegerBoxedDemo {

    public static void main(String[] args) {
        int a = 100; // 基本数据类型
        Integer b = 100; // 包装类型
        Integer c = new Integer(100); // 包装类型
        System.out.println(a == b); // true，包装类型自动拆箱，比较值
        System.out.println(b == c); // false，包装类型比较引用
        System.out.println(a == c); // true，包装类型自动拆箱，比较值
    }

}
