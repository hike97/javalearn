package mianshi.javabasic.scanner;

import java.util.Scanner;

/**
 * @author: hike97
 * @createTime: 2023/06/21 20:35
 * @description: TODO
 */
public class ScannerDemo {
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);

        //1.从上可知：使用next()方法读取输入时是将空格作为两个字符串之间的间隔来处理。
        /*System.out.println("使用next()方法,输入为：");
        while(sc.hasNext()){
            String val=sc.next();
            System.out.println("输出为：");
            System.out.println(val);
        }*/

        //2.nextLine方法只处理
      /*  System.out.println("使用nextLine()方法,输入为：");
        String val = sc.nextLine();
        System.out.println("输出为：");
        System.out.println(val);*/

        //3.使用nextInt()方法时，处理空格的方式与next()类似，将空格作为两个输入数据之间的间隔，只是它的返回值是int类型。
        //  并且当使用nexInt()方法时，只能输入int类型的数据。
        /*System.out.println("使用nextInt()方法,输入为：");
        while(sc.hasNext()){
            int val = sc.nextInt();
            System.out.println("输出为：");
            System.out.println(val);
        }*/


        System.out.println("请输入一个整数：");
        int num = sc.nextInt();
//        sc.nextLine(); // 读取上一次输入留下的换行符
        System.out.println("您输入的整数是：" + num);


    }
}
