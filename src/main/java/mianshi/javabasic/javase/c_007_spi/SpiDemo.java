package mianshi.javabasic.javase.c_007_spi;

import com.spi.American;
import com.spi.People;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author: hike97
 * @createTime: 2023/04/18 14:26
 * @description: spi机制模拟
 */
public class SpiDemo {
    public static void main(String[] args) {
        ServiceLoader<People> peoples = ServiceLoader.load(People.class);
        Iterator<People> iterator = peoples.iterator();
        while (iterator.hasNext()) {
            People people = iterator.next();
            System.out.println(people.getClass().getClassLoader());
            System.out.println(people.getClass().getClassLoader().getParent());
            System.out.println(people.getClass().getClassLoader().getParent().getParent());
            System.out.println(people.speak());
        }
    }
}
