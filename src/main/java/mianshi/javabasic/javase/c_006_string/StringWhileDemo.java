package mianshi.javabasic.javase.c_006_string;

/**
 * @author: hike97
 * @createTime: 2023/04/17 10:19
 * @description: TODO
 */
public class StringWhileDemo {
    public static void main(String[] args) {
        String[] arr = {"he", "llo", "world"};
        String s = "";
        for (int i = 0; i < arr.length; i++) {
            s += arr[i];
        }
        System.out.println(s);
    }
}
