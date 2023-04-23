package mianshi.javabasic.javase.c_006_string;

/**
 * @author: hike97
 * @createTime: 2023/04/23 9:58
 * @description: intern()方法是一个本地方法
 * 调用该方法时，如果字符串常量池中已经存在一个等于此String对象的字符串，就直接从常量池中返回这个字符串对象的引用
 * 如果调用该方法的这个字符串对象，目前字符串常量池中还并不存在这个字符串对象，也就是说这个字符串对象是首次出现的，
 * 那么就新建并添加这个字符串对象到字符串常量池中，并返回新建的字符串对象的引用
 */
public class StringInternDemo2 {
    public static void main(String[] args) {
        /*StringBuilder创建的字符串对象是在堆上，调用intern()方法会把这个字符串对象实例引用放到字符串常量池，
        并返回这个字符串对象的引用。 因此intern()返回的引用和由StringBuilder创建的那个字符串实例就是同一个对象*/
        String str1 = new StringBuilder("hello").append("world").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1 == str1.intern());

        System.out.println();
        /* 上述代码都是创建了一个字符串对象并调用intern()方法把这个字符串对象引用放到了字符串常量池中，
        但是“helloworld”字符串返回true，而“java”字符串却返回的是false，
        这说明“java”这个字符串之前已经创建过，并把引用存放在了字符串常量池中。*/
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2 == str2.intern());

        String a = new String("123") + new String("456");
        //String b = new String("123456");
        String intern = a.intern();
        System.out.println(intern == a);
    }
}
