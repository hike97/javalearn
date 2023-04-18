package mianshi.javabasic.javase.c_001_innerclass;

/**
 * @author: hike97
 * @createTime: 2023/04/11 23:23
 * @description: 方法中的局部内部类
 * 局部内部类可以访问方法中的局部变量，但不能修改它，因为：
 * 局部变量是存储在栈中的，而局部内部类是存储在堆中的。方法出栈后，局部变量会被清除，而局部内部类可能还存在。
 * 如果局部内部类可以修改局部变量，就会导致数据不一致的问题。
 * 局部内部类访问的其实是局部变量的副本，而不是原始变量。这符合Java的值传递原则。
 * 如果局部变量不是final的，那么它的值可能会改变，而副本的值不会跟着改变，也会导致数据不一致的问题。
 * final关键字可以保证局部变量的值在编译时就确定，不会被修改。这样就可以避免上述的数据不一致的问题，并且方便编译器优化。
 */
public class MethodWithInnerClass {
    //定义一个私有属性
    private int num = 10;
    //定义一个公共方法
    public void method() {
        //定义一个局部变量
        int j = 20;
        //定义一个局部内部类
        class Inner {
            //常量
            int number = 4;
            public void show() {
                //访问外部类的私有属性
                System.out.println(num);
                //访问方法的局部变量，需要加final修饰符
                System.out.println(number);
            }
        }
        //创建局部内部类的对象 必须在类下边new
        Inner i = new Inner();
        //调用局部内部类的方法
        i.show();
    }

    public static void main(String[] args) {
        MethodWithInnerClass outerClass = new MethodWithInnerClass();
        outerClass.method();
    }
}