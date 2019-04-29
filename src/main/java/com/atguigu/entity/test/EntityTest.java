package com.atguigu.entity.test;

import com.atguigu.entity.Employee;

import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-04-22 11:18
 * @desc 比较测试
 **/
public class EntityTest {

	public String testField;

	public static void main (String[] args) {

		List<Employee> employees = Arrays.asList (new Employee ("南风", 13), new Employee ("西风", 13), new Employee ("东风", 13), new Employee ("北京", 13));
		employees.stream ().sorted ((e1,e2)->e2.compare (e1,e2)).forEach( System.out::println );
//		EntityTest test = new EntityTest ();
//		System.out.println (test.testField);
		employees.sort (Comparator.comparing (Employee::getName,Collator.getInstance (Locale.CHINA)));

//		employees.sort ((e1,e2)->
//			 Collator.getInstance(Locale.CHINA).compare (e1.getName (),e2.getName ())
//		);
		System.out.println ("排序结果："+employees);

	}
}
