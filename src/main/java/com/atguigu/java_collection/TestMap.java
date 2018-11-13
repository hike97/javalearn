package com.atguigu.java_collection;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hike97
 * @create 2018-11-05 17:21
 * @desc 测试Map
 **/
public class TestMap {
	public static void main (String[] args) {
		Map<Object, Object> map = new HashMap<> ();
		map.put ("111",new Wife ("小泽"));
		map.put ("222",new Wife ("桃谷绘里香"));
	}
}

@Data
class Wife{
	String name;

	public Wife (final String name) {
		this.name = name;
	}
}
