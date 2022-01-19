package com.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-05-09 11:01
 * @Modified By:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat {
	static {
		List<String> list = Arrays.asList ("1", "2", "3");
		System.out.println ("list init");
	}

	private String name;
	private String age;

	public static void main (String[] args) {
		Cat cat = new Cat ();
	}

}
