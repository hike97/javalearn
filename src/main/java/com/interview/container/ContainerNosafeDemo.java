package com.interview.container;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author hike97
 * @Description 容器不安全
 * @create 2020-09-22 16:29
 * @Modified By:
 **/
public class ContainerNosafeDemo {
	public static void main (String[] args) {
		List<String> list = new ArrayList<> ();
		for (int i = 0; i < 30; i++) {
			new Thread (()->{
				list.add (UUID.randomUUID ().toString ().substring (0,4));
				System.out.println (list);
			},String.valueOf (i)).start ();
		}
		//java.util.ConcurrentModificationException
		new CopyOnWriteArrayList();
		new HashSet<> (); //底层 是 hashMap  add 方法 调用 map.put(e,常量)
		//public boolean add(E e) {
		//        return map.put(e, PRESENT)==null;
		//    }
		/*
		 public boolean add(E e) {
          synchronized (lock) {
            Object[] es = getArray();
            int len = es.length;
            //写时赋值，在一个容器中添加元素的时候，不直接添加，而是先复制一个新的容器
            es = Arrays.copyOf(es, len + 1);
            es[len] = e;
            //然后将元容器的引用指向新的容器
            setArray(es);
            return true;
        }
    }
		 * */

	}
}
