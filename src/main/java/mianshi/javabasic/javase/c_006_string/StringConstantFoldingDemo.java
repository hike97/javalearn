package mianshi.javabasic.javase.c_006_string;

/**
 * @author: hike97
 * @createTime: 2023/04/17 9:53
 * @description: 字符串常量折叠问题
 * 我可以帮你理解一下什么叫ConstantFolding。根据搜索结果，ConstantFolding是一种编译器优化技术，
 * 它的作用是在编译时识别和计算那些可以提前确定的常量表达式，而不是在运行时计算它们。
 * 常量表达式中的项通常是简单的字面量，例如整数字面量2，但也可能是编译时已知值的变量。
 */
public class StringConstantFoldingDemo {
    public static void main(String[] args) {
        String str1 = "str";
        String str2 = "ing";
        String str3 = "str" + "ing"; //相当于str5
        String str4 = str1 + str2; //String str4 = new StringBuilder().append(str1).append(str2).toString();
        String str5 = "string";


        System.out.println(str3 == str4);//false
        System.out.println(str3 == str5);//true
        System.out.println(str4 == str5);//false
        /*
         * 用final修饰相当于 "str"+"ing"
         */
        final  String str6 = "str";
        final  String str7 = "ing";
        String str8 = str6 + str7;
        System.out.println(str8 == str5);//true

        // 关键字 编译器确认值才能优化 运行时确认就不会被优化
        String str9 = getString();
        String str10 = str1 + getString();
        System.out.println(str3 == str10);//false

        /*
         * 下边这段代码 在jdk8中没有被优化，但是在jdk17 中被优化了
         */
        final int a = 10;
        final int b = 20;
        int c = a + b;
        int d = 2 * c;
        System.out.println(d);
    }

    private static String getString() {
        return "ing";
    }
}
