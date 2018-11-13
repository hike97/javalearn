package com.atguigu.java_collection;

import lombok.Data;

/**
 * @author hike97
 * @create 2018-11-12 10:06
 * @desc 重写hashcode和equals, equals相等hashcode必然相等
 * >>右移1位相当于除以2 <<左移一位相当于乘以2
 **/
@Data
public class Student {
	private Integer id;
	private String name;

	@Override
	public boolean equals (final Object object) {
		if (this == object) return true;
		if (object == null || getClass () != object.getClass ()) return false;
		if (!super.equals (object)) return false;

		final Student student = (Student) object;

		return id != null ? id.equals (student.id) : student.id == null;
	}

	@Override
	public int hashCode () {
		int result = super.hashCode ();
		result = 31 * result + (id != null ? id.hashCode () : 0);
		return result;
	}
}
