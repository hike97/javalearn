package com.atguigu.entity.test;

import com.atguigu.entity.Employee;

import java.util.Arrays;
import java.util.List;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-04-22 11:18
 * @desc 比较测试
 **/
public class EntityTest {
	public static void main (String[] args) {

		List<Employee> employees = Arrays.asList (new Employee ("南风", 13), new Employee ("西风", 13), new Employee ("东风", 13), new Employee ("北京", 13));
		employees.stream ().sorted ((e1,e2)->e2.compare (e1,e2)).forEach( System.out::println );
	}
}
