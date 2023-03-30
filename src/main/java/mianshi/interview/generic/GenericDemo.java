package mianshi.interview.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hike97
 * @Description
 * @create 2020-11-13 15:12
 * @Modified By:
 **/
public class GenericDemo {

	/**
	 *
	 * @param e
	 * @param <E>
	 * @return
	 */
	public static  <E> List arrToList (E[] e) {
		List<E> list = new ArrayList<> ();
		for (E e1 : e) {
			list.add (e1);
		}
		return list;
	}
}
