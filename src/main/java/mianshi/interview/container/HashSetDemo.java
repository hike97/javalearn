package mianshi.interview.container;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

/**
 * @Author hike97
 * @Description
 * @create 2020-11-11 10:40
 * @Modified By:
 **/
public class HashSetDemo {
	/**
	 * HashSet add 方法
	 * 1.获取当前对象的hashCode值 通过某种算法获取到 数组的下标
	 * 		如果当前下标没有元素 则插入成功 SUCCESS case1
	 * 			如果有对比当前数组下链表的hashcode的值 如果不存在相同的hashcode值 则插入成功SUCCESS case2
	 * 				如果存在相同的hascode 调用equals方法 如果不同 插入成功SUCCESS case3
	 * 	 其它都算插入失败FAIL。
	 * 	 hashset 存储规则 七（jdk7 头节点永远是最新的值）上八（jdk8 头节点永远是最先存入的值）下
	 * 	 case1 存储到数组
	 * 	 case2 case3 存储到链表
	 *
	 * 	 linkedHashSet 也是存储时也是无序的 只是能够按存储的顺序打印出来
	 * 	 	底层是一个双向链表 两个引用记录前一个数据和后一个数据
	 * 	 	优点：频繁遍历效率高一些
	 */

	public static void main (String[] args) {
		HashSet set = new HashSet<> ();
		Person p1 = new Person (1001, "AA");
		Person p2 = new Person (1002, "BB");
		set.add (p1);
		set.add (p2);
		System.out.println (set);

		p1.name = "CC";
		set.remove (p1);
		//此时 p1 为 1001 CC set 的remove方法会去查询此对象hash值的位置
		//但是p1 当前位置 是根据 1001 AA hashcode
		    // 计算的位置 remove p1 实际上算的位置是 1001 cc的位置 =》什么都移除不了
		    //
		System.out.println ("修改p1.name为CC后 进行移除操作~~~ \n"+set);
		set.add (new Person(1001,"CC"));
		//此时存入 新的1001 CC 这样会根据当前属性的hashCode 计算出 上次移除位置为空的 index 插入成功
		System.out.println ("插入新的1001 CC Person() 操作~~~ \n"+set);
		set.add (new Person (1001,"AA"));
		//此时存入 新的 1001 AA 对象 会和 原来被修改的1001 CC
			// 	是同一个index 所以继续比较equals方法 两者不同加入该index下的链表之中
		System.out.println ("插入新的1001 AA Person() 操作~~~ \n"+set);
	}
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	private static class Person{
		int id;
		String name;

		@Override
		public boolean equals (Object o) {
			if (this == o) return true;
			if (o == null || getClass () != o.getClass ()) return false;

			Person person = (Person) o;

			if (id != person.id) return false;
			return name != null ? name.equals (person.name) : person.name == null;
		}

		@Override
		public int hashCode () {
			int result = id;
			result = 31 * result + (name != null ? name.hashCode () : 0);
			return result;
		}
	}
}
