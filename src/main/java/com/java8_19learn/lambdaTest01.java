package com.java8_19learn;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description java8 风格引入
 * @create 2019-04-24 14:41
 * @Modified By:
 **/
public class lambdaTest01 {
	public static void main (String[] args) {
		//筛选隐藏文件的老方法
//		File[] hiddenFiles = new File (".").listFiles (new FileFilter () {
//			@Override
//			public boolean accept (File file) {
//				return file.isHidden ();
//			}
//		});
//		//java8 风格
//		File[] utils = new File (".").listFiles (File::isHidden);
		List<String> list = new ArrayList<> ();
		list.add ("1");
		list.add ("2");
		list.add ("3");
		System.out.println (list);

	}
}
