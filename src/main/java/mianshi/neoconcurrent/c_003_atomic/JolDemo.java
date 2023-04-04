package mianshi.neoconcurrent.c_003_atomic;

import org.openjdk.jol.info.ClassLayout;

/**
 * @ClassName JolDemo
 * @Description 利用jol打印对象
 * @Author hike97
 * @Date 2023/3/23 19:53
 * @Version 1.0
 **/
public class JolDemo {
    public static void main(String[] args) {
        //Object o = new Object();
        //Integer o = 1;
        Integer o = 129;
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
