package com.atguigu.java_collection;

import lombok.Data;

/**
 * @author hike97
 * @create 2018-11-06 19:56
 * @desc 自定义实现map的功能 暂不完美
 **/
public class SxtMap001 {

	SxtEntry[] arr = new SxtEntry[990];

	int size;

	public void put(Object key,Object value){
		SxtEntry e = new SxtEntry (key, value);
		//解决键值重复的处理
		for (int i = 0; i <size ; i++) {
			if (arr[i].key.equals (key)){
				arr[i].value = value;
				return;
			}
		}
		arr[size++] = e;
	}

	public Object get(Object key){
		for (int i = 0; i <size ; i++) {
			if (arr[i].key.equals (key)){
				return  arr[i].value;
			}
		}
		return null;
	}

	public boolean containsKey(Object key){
		for (int i = 0; i <size ; i++) {
			if (arr[i].key.equals (key)){
				return  true;
			}
		}
		return false;
	}

	public boolean containsValue (Object value){
		for (int i = 0; i <size ; i++) {
			if (arr[i].value.equals (value)){
				return  true;
			}
		}
		return false;
	}

	public static void main (String[] args) {
		SxtMap001 m = new SxtMap001 ();
		m.put ("高淇",new Wife ("杨幂"));
		System.out.println (m.size);
		Wife o = (Wife) m.get ("高淇");
		System.out.println (o);
	}
}
@Data
class SxtEntry{
	Object key;
	Object value;

	public SxtEntry (final Object key, final Object value) {
		this.key = key;
		this.value = value;
	}
}
