package mianshi.javabasic.javase.c_006_string;

/**
 * @author: hike97
 * @createTime: 2023/04/17 20:47
 * @description: String.intern() 的用法
 * 当调用String.intern()方法时，如果常量池中已经存在该字符串对象的内容，那么就直接返回常量池中的引用；
 * 如果常量池中不存在该字符串对象的内容，那么就将该字符串对象添加到常量池中，并返回新添加的引用
 */
public class StringInternDemo {
    public static void main(String[] args) {
        String s1 = "Hello"; // 创建一个字符串对象，并将其引用赋给s1
        String s2 = new String("Hello"); // 创建另一个字符串对象，并将其引用赋给s2
        String s3 = s1.intern(); // 调用s1.intern()方法，返回常量池中"Hello"的引用，并赋给s3
        String s4 = s2.intern(); // 调用s2.intern()方法，返回常量池中"Hello"的引用，并赋给s4
        System.out.println(s1 == s2); // false，因为s1和s2指向不同的对象
        System.out.println(s1 == s3); // true，因为s1和s3指向同一个对象
        System.out.println(s2 == s4); // false，因为s2和s4指向不同的对象
        System.out.println(s3 == s4); // true，因为s3和s4指向同一个对象
    }
}
