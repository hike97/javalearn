package com.jvmlearn;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author hike97
 * @Description
 * @create 2020-09-02 15:00
 * @Modified By:
 **/
public class HelloJOL {
	public static void main (String[] args) {
		Object o = new Object ();
		System.out.println (ClassLayout.parseInstance (o).toPrintable ());
		//将o上完锁之后 markword 会有变化
		synchronized (o){
			System.out.println (ClassLayout.parseInstance (o).toPrintable ());
		}
	}
}
