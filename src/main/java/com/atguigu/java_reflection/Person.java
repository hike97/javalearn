package com.atguigu.java_reflection;



public class Person  {
	public String name;
	private int age;
	public Person() {
		super();
	}
	public Person(String name) {
		super();
		this.name = name;
	}
	private Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public void show(){

		System.out.println("我是一个人");
	}
	
	public void display(String nation) {
		System.out.println("我的国籍是：" + nation);
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
