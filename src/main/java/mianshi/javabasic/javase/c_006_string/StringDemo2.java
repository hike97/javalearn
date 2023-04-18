package mianshi.javabasic.javase.c_006_string;

/**
 * @author: hike97
 * @createTime: 2023/04/17 17:22
 * @description: String abc 会创建几个对象
 */
public class StringDemo2 {
    // 字符串常量池中已存在字符串对象“abc”的引⽤
    String s1 = "abc";
    // 下⾯这段代码只会在堆中创建 1 个字符串对象“abc”
    String s2 = new String("abc");

   /*
        0 aload_0：将第一个局部变量（即this指针）压入操作数栈
        1 invokespecial #1：调用父类的构造方法（#1表示常量池中的第一个常量，即java/lang/Object.<init> : ()V）
        4 aload_0：将第一个局部变量（即this指针）压入操作数栈
        5 ldc #2：将常量池中的第二个常量（即字符串"abc"）压入操作数栈
        7 putfield #3：将操作数栈顶的值（即"abc"）赋给当前对象的第三个字段（即s1）
        10 aload_0：将第一个局部变量（即this指针）压入操作数栈
        11 new #4：创建一个新对象（#4表示常量池中的第四个常量，即java/lang/String），并将其引用压入操作数栈
        14 dup：复制操作数栈顶的值，并压入操作数栈
        15 ldc #2：将常量池中的第二个常量（即字符串"abc"）压入操作数栈
        17 invokespecial #5：调用新对象的构造方法（#5表示常量池中的第五个常量，即java/lang/String.<init> : (Ljava/lang/String;)V），并将其从操作数栈弹出
        20 putfield #6：将操作数栈顶的值（即新对象的引用）赋给当前对象的第六个字段（即s2）
        23 return：从当前方法返回
    */
}
