package mianshi.javabasic.javase.c_001_innerclass;

/**
 * @author: hike97
 * @createTime: 2023/04/12 0:10
 * @description: 外部类中的局部内部类
 */
public class OuterClassWithInnerClass {

    //定义一个私有属性
    private int num = 10;

    //定义一个成员内部类
    class Inner {
        //定义一个公共方法
        public void show() {
            //访问外部类的私有属性
            System.out.println(num);
        }
    }

    //定义一个公共方法
    public void method() {
        //创建成员内部类的对象
        Inner i = new Inner();
        //调用成员内部类的方法
        i.show();
    }

    public static void main(String[] args) {
        //创建外部类的对象
        OuterClassWithInnerClass o = new OuterClassWithInnerClass();
        //调用外部类的方法
        o.method();
        //创建内部类的对象
        OuterClassWithInnerClass.Inner i = o.new Inner();
        //调用内部类的方法
        i.show();
    }
}
