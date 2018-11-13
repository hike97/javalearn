package com.atguigu.java_collection;


/**
 * @author hike97
 * @create 2018-11-02 20:35
 * @desc 手写arraylist
 **/
public class SxtArrayList /*implements List*/{

	private Object[] elementData;

	private int size;

	public int size () {
		return size;
	}
	public boolean isEmpty(){
		return size==0;
	}
	public SxtArrayList () {
		this(10);
	}

	public SxtArrayList (int initialCapacity) {
		if (initialCapacity<0){
			try {
				throw new Exception ();
			} catch (Exception e) {
				e.printStackTrace ();
			}
		}
		elementData = new Object[initialCapacity];
	}
	/**常用的封装方法--START--*/
	private void rangeCheck(int index){
		if (index>=size){
			try {
				throw new Exception ();
			} catch (Exception e) {
				e.printStackTrace ();
			}
		}
	}
	private void ensureCapacity(){
		//数组扩容和数据的拷贝
		if (elementData.length==size){
			Object[] newArray = new Object[size*2+1];
			System.arraycopy (elementData,0,newArray,0,elementData.length);
			//			for (int i = 0; i <elementData.length ; i++) {
			//				newArray[i] = elementData[i];
			//			}
			elementData = newArray;
		}
	}
	/**常用的封装方法--END--*/
	public  void add(Object obj){
		ensureCapacity();
		elementData[size++] = obj;
	}

	public Object get(int index){
		rangeCheck(index);
		return elementData[index];
	}

	public void remove(int index){
		rangeCheck(index);
		int numMoved = this.size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(this.elementData, index + 1, this.elementData, index, numMoved);
		}

		this.elementData[--this.size] = null;
	}
	public void remove(Object obj){
		for (int i = 0; i <size ; i++) {
			if (get (i).equals (obj)){//注意：底层调用的equals方法而不是==
					remove (i);
			}
		}
	}

	public Object set(int index,Object obj){
		rangeCheck (index);

		Object oldValue = elementData[index];
		elementData[index] = obj;
		return  oldValue;
	}

	public void  add(int index,Object obj){
		rangeCheck (index);
		ensureCapacity();
		System.arraycopy (elementData,index,elementData,index+1,size-index);
		elementData[index] = obj;
		size++;
	}
	public static void main (String[] args) {
		SxtArrayList list = new SxtArrayList (3);
		list.add ("123");
		list.add ("1231");
		list.add ("1232");
		list.add ("1233");
		list.add ("1234");
		System.out.println (list.size ());
		System.out.println (list.get (4));
		System.out.println (list.isEmpty ());
	}
}
