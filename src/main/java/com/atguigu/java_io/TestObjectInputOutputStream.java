package com.atguigu.java_io;

import lombok.Data;
import org.junit.Test;

import java.io.*;

/**
 * @author hike97
 * @create 2018-10-10 10:59
 * @desc 对象流
 **/
public class TestObjectInputOutputStream {
	@Test
	public void test_1 () throws Exception {
		ObjectInputStream ois
				= new ObjectInputStream (new FileInputStream (new File ("p.txt")));
		Person o1 = (Person) ois.readObject ();
		Person o2 = (Person) ois.readObject ();
		System.out.println (o1);
		System.out.println (o2);
		ois.close ();
	}
	@Test
	public void test_ () throws Exception {
		Person p1 = new Person ("小米",11);
		Person p2 = new Person ("红米",21);
		ObjectOutputStream os = new ObjectOutputStream (new FileOutputStream (new File ("p.txt")));
		os.writeObject (p1);
		os.flush ();
		os.writeObject (p2);
		os.flush ();
		os.close ();
	}
}
//person 要实现序列化的类：
//要求此类是可序列化的：
// 1.实现Serializable Externalizable
// 2.类的属性也必须实现serializable接口
//3.
@Data
class Person implements Serializable{
	private static final long serialVersionUID = -8587699841346505259L;
	static String name;
	transient Integer age;

	public Person (final String name, final Integer age) {
		this.name = name;
		this.age = age;
	}
}
