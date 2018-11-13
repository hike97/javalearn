package com.sxt.arithmetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author hike97
 * @create 2018-11-13 11:22
 * @desc CollectionsDemo
 **/
public class CollectionsTest {
	public static void main (String[] args) {
		List<Integer> list = new ArrayList<> ();
		list.add (1);
		list.add (2);
		list.add (3);
		list.add (4);
		System.out.println (list);
		Collections.reverse (list);
		System.out.println ("反转之后:" + list);
		//洗牌
		list.clear ();
		for (int i = 0; i <54 ; i++) {
			list.add (i);
		}
		Collections.shuffle (list);
		List<Integer> p1 = new ArrayList<> ();
		List<Integer> p2 = new ArrayList<> ();
		List<Integer> p3 = new ArrayList<> ();
		List<Integer> last = new ArrayList<> ();
		for (int i = 0; i <51 ; i+=3) {
			p1.add (list.get (i));
			p2.add (list.get (i+1));
			p3.add (list.get (i+2));
		}
		last.add (list.get (list.size ()-1));
		last.add (list.get (list.size ()-2));
		last.add (list.get (list.size ()-3));
		System.out.println (p1);
		System.out.println (p2);
		System.out.println (p3);
		System.out.println (last);
	}
}
