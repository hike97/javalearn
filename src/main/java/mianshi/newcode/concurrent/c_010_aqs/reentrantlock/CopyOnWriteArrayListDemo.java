package mianshi.newcode.concurrent.c_010_aqs.reentrantlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: hike97
 * @createTime: 2023/04/22 18:29
 * @description: 读写列表
 */
public class CopyOnWriteArrayListDemo {
   /*  好的，我尽量用简单的话来解释。假设你有一个列表，里面存了一些数字，比如1，2，3，4，5。
      你想让多个人（线程）同时看这个列表里有什么数字，或者往里面加新的数字。
      但是你又不想让他们互相打架（加锁），因为那样会很慢，也很麻烦。你该怎么办呢？
      你可以使用CopyOnWriteArrayList这个特殊的列表，它的名字就告诉了你它是怎么工作的。每当有人想往列表里加一个新的数字时，
      它就会把原来的列表复制一份，然后在新的列表上加上那个数字，最后再把新的列表给那个人。这样，其他人看到的还是原来的列表，
      不会被打扰，也不会看到错误的数据。只有当他们再次查看列表时，才会看到新的数据。
      比如说，小明想往列表里加一个6，他就会得到一个新的列表[1, 2, 3, 4, 5, 6]。
             小红想看看列表里有什么数字，她就会看到原来的列表[1, 2, 3, 4, 5]。
             小刚想往列表里加一个7，他就会得到另一个新的列表[1, 2, 3, 4, 5, 7]。
             小花想看看列表里有什么数字，她就会看到最新的列表[1, 2, 3, 4, 5, 7]。
    */


    public static void main(String[] args) {
        // 创建一个CopyOnWriteArrayList对象，里面存了一些数字
        //CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3, 4, 5});
         ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));

        // 创建一个线程，表示小明想往列表里加一个6
        Thread thread1 = new Thread(() -> {
            // 往列表里加一个6
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.add(6);
            // 打印出自己看到的列表
            System.out.println("小明看到的列表：" + list);
        });

        // 创建一个线程，表示小红想看看列表里有什么数字
        Thread thread2 = new Thread(() -> {
            // 打印出自己看到的列表
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("小红看到的列表：" + list);
        });

        // 创建一个线程，表示小刚想往列表里加一个7
        Thread thread3 = new Thread(() -> {
            // 往列表里加一个7
            list.add(7);
            // 打印出自己看到的列表
            System.out.println("小刚看到的列表：" + list);
        });

        // 创建一个线程，表示小花想看看列表里有什么数字
        Thread thread4 = new Thread(() -> {
            // 打印出自己看到的列表
            System.out.println("小花看到的列表：" + list);
        });

        // 启动四个线程
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

}
