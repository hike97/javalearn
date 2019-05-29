package com.java8_19learn.optional_apply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.Properties;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description optional 的方法应用
 * @create 2019-05-27 15:11
 * @Modified By:
 **/
public class OptionalApply {

	public static void main (String[] args) {
		//1.声明一个空的Optional
		Optional<Object> empty = Optional.empty ();
		//2.一句一个非空值创建Optional
		Car car = new Car ();
		Optional<Car> optCar = Optional.of (car);
		//如果 car 是一个 null ，这段代码会立即抛出一个 NullPointerException ，而不是等到你
		//试图访问 car 的属性值时才返回一个错误。
		//3.创建可接受null的optional
		car = null;
		Optional<Car> optionalCar = Optional.ofNullable (car);
		System.out.println (optionalCar.get ());
		//optional map 方法
		Optional<String> name = optionalCar.map (Car::getName);
	}
	//返回一个人车险的名称
	public String getCarInsuranceName(Person person) {
		return person.getCar().getInsurance().getName();
	}

	@Test
	public void test_ () {
		//用optional重构
		//1.尝试用map
		Person person = new Person (new Car ("BENS",new Insurance ("DABAOJIAN")));
		Optional<Person> optPerson = Optional.of(person);
		Optional<String> s = optPerson.map (Person::getCar)
				.map (Car::getInsurance)
				.map (Insurance::getName);
		System.out.println (s);

	}
	//2.用flatmap 无法编译 原因未知
//	public String getCarInsuranceName(Optional<Person> person) {
//		return person.flatMap(Person::getCar)
//				.flatMap(Car::getInsurance)
//				.map(Insurance::getName)
//				.orElse("Unknown");
//	}
	//optional的filter用法
	//empty  返回一个空的 Optional 实例
	//filter
	//如果值存在并且满足提供的谓词，就返回包含该值的 Optional 对象；否则返回一个空的
	//Optional 对象
	//flatMap
	//如果值存在，就对该值执行提供的 mapping 函数调用，返回一个 Optional 类型的值，否则就返
	//回一个空的 Optional 对象
	//get  如果该值存在，将该值用 Optional 封装返回，否则抛出一个 NoSuchElementException 异常
	//ifPresent  如果值存在，就执行使用该值的方法调用，否则什么也不做
	//isPresent  如果值存在就返回 true ，否则返回 false
	//map  如果值存在，就对该值执行提供的 mapping函数调用
	//of
	//将指定值用 Optional 封装之后返回，如果该值为 null ，则抛出一个 NullPointerException
	//异常
	//ofNullable  将指定值用 Optional 封装之后返回，如果该值为 null ，则返回一个空的 Optional 对象
	//orElse  如果有值则将其返回，否则返回一个默认值
	//orElseGet  如果有值则将其返回，否则返回一个由指定的 Supplier 接口生成的值
	//orElseThrow  如果有值则将其返回，否则抛出一个由指定的 Supplier 接口生成的异常
	@Test
	public void test_01 () {
		Optional<Insurance> optInsurance = Optional.of (new Insurance ("CambridgeInsurance"));
		optInsurance.filter(insurance ->
				"CambridgeInsurance".equals(insurance.getName()))
				.ifPresent(x -> System.out.println("ok"));
	}
	//optional 和 异常 使用optional屏蔽异常
	public static Optional<Integer> stringToInt(String s) {
		try {
			return Optional.of(Integer.parseInt(s));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}
	//这里不用optionalInt 原因是基础类型的optional不支持map flatmap filter方法
	/**
	 * 需求：给定属性对应的值是一个代表正整数的字符串，就返回该整数值，任何其他的情况都返
	 * 回0。
	 */
	@Test
	public void test_optional_use () {
		Properties props = new Properties();
		props.setProperty("a", "5");
		props.setProperty("b", "true");
		props.setProperty("c", "-3");
		//junit断言
//		Assert.assertEquals(5,readDuration (props,"a"));
		Assert.assertEquals(5,readDurationByOptional (props,"a"));
	}

	public int readDuration(Properties props, String name) {
		String value = props.getProperty(name);
		if (value != null) {
			try {
				int i = Integer.parseInt(value);
				if (i > 0) {
					return i;
				}
			} catch (NumberFormatException nfe) { }
		}
		return 0;
	}
	//使用optional重构上边的方法
	public int readDurationByOptional(Properties props, String name){
		return Optional.ofNullable (props.getProperty(name))
				.flatMap (OptionalApply::stringToInt)
				.filter(i->i>0)
				.orElse (0);
	}
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Person{
	private Car car;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Car {
	private String name;
	private Insurance insurance;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Insurance {
	private String name;
}
