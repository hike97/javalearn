package com.bean;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-05-09 11:04
 * @Modified By:
 **/
public class BeanUtilsTest {
	public static void main (String[] args) {
		Cat cat = new Cat ("楚歌", "12k");
		Dog dog = new Dog ();
		BeanUtils.copyProperties (cat,dog);
//		BeanUtils.copyPropertiesASM (cat,dog);
		System.out.println (dog);

	}
}
