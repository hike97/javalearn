package com.aboutstring;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-05-08 19:05
 * @desc
 **/
@Data
@NoArgsConstructor
public class Teacher {

	private String name;
	private String code;
	private String lifeNo;

	public Teacher (String name, String code) {
		this.name = name;
		this.code = code;
	}

	@Data
	@NoArgsConstructor
	private static class Student{
		private String name;
		private String code;

		public Student (String name, String code) {
			this.name = name;
			this.code = code;
		}
	}

	public static void main (String[] args) {
		List<Student> studentList = Arrays.asList (new Student ("zhangsan", "1"), new Student ("zhangsan", "2"));
		List<Teacher> teachers = new ArrayList<> ();
//		Object[] student = studentList.toArray ();
//		Teacher[] teacher = new Teacher[student.length];
//		System.arraycopy (student,0,teacher,0,student.length);
//		System.out.println (teacher);
		studentList.forEach (student -> {

		});
	}
}
