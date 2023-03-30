package workcode.exercise;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author hike97
 * @Description
 * @create 2020-10-15 16:43
 * @Modified By:
 **/
public class InterfaceImpl implements TestInterface {
	public static void main (String[] args) {
		//stackOverFlow
		//main (args);
		//Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//		Integer[] ints = new Integer[1024 * 1024 * 1024];
		String str = "AAA" +
				"BBB" +
				"CCC";
		System.out.println (JSONObject.parse (str));

	}
}
