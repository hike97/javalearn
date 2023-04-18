package mianshi.javabasic.javase.c_003_overload;

/**
 * @author: hike97
 * @createTime: 2023/04/16 19:35
 * @description:
 * 遇到⽅法重载的情况怎么办呢？会优先匹配固定参数还是可变参数的⽅法呢？
 * 答案是会优先匹配固定参数的⽅法，因为固定参数的⽅法匹配度更⾼。
 */
public class VariableLengthArgument {

    public static void printVariable(String... args) {
        for (String s : args) {
            System.out.println(s);
        }
    }
    public static void printVariable(String arg1, String arg2) {
        System.out.println(arg1 + arg2);
    }
    public static void main(String[] args) {
        printVariable("a", "b");
        printVariable("a", "b", "c", "d");
    }
}
