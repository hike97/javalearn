package com.atguigu.java_collection;

import lombok.Data;

/**
 * @author hike97
 * @create 2018-11-05 15:15
 * @desc 手写linkedList
 **/
public class SxLinkedList {
	private Node first;
	private Node last;
	private int size;

	public void add (Object obj) {
		Node node = new Node ();
		if (first==null){
			node.setPrevious (null);
			node.setObj (obj);
			node.setNext (null);
			first = node;
			last = node;
		}else {
			//直接往last节点后增加新的节点
			node.setPrevious (last);
			node.setObj (obj);
			node.setNext (null);

			last.setNext (node);
			last = node;
		}
		size++;
	}

	public Object get(int index){
		rangeCheck(index);
		//0 1 2 3 4
		Node temp = node (index);
		return  temp.obj;
	}

	public int size(){
		return size;
	}

	private void rangeCheck(int index){
		if (index<0||index>=size){
			try {
				throw new Exception ();
			} catch (Exception e) {
				e.printStackTrace ();
			}
		}
	}

	public Node node(int index){
		Node temp = null;
		if (first!=null){
			if (index<(size>>1)){
				temp = first;
				//右移一位除以2
				//size:50; 2 48
				//index 跟size/2 比较 如果大于中间值 倒序循环 如果小于中间值顺序循环
				for (int i = 0; i <index; i++) {
					temp = temp.next;
				}
			}else {
				temp = last;
				for (int i = size-1; i >index ; i--) {
					temp = temp.previous;
				}
			}
		}
		return temp;
	}
	public void remove(int index){
		Node temp = node (index);
		if (temp!=null){
			Node up = temp.previous;
			Node down = temp.next;
			up.next = down;
			down.previous = up;
		}
		size--;
	}

	public void add(int index,Object obj){
		rangeCheck (index);
		Node temp = node (index);
		Node up = temp.previous;
		Node n = new Node ();
		n.setObj (obj);

		up.next = n;
		n.previous = up;

		n.next = temp;
		temp.previous = n;
		size++;

	}
	public static void main (String[] args) {
		SxLinkedList list = new SxLinkedList ();
		list.add("AAA");
		list.add ("BBB");
		list.add ("CCC");
		System.out.println (list.size ());
		list.remove (1);
		list.add (1,"BBBB");
		System.out.println (list.get (1));
	}


}
//用来表示一个节点
@Data
class Node{
	Node previous;//上一个节点
	Object obj;
	Node next;//下一个节点
}