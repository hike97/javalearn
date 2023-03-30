package workcode.exercise;

/**
 * @Author hike97
 * @Description
 * @create 2020-10-13 18:58
 * @Modified By:
 **/
public class IntegerDemo {
	public static void main (String[] args) {
		Integer i01 = 59;
		int i02 = 59;
		//表示-128到127的Integer对象已经建立好了，存在***数组中，调用valueOf后，直接把相应的引用返回。
		Integer i03 =Integer.valueOf(59);
		Integer i04 = new Integer(59);
		/**
		 * 包装类型间的相等判断应该用equals，而不是'==' less... (Ctrl+F1)
		 * Inspection info:
		 * 所有的包装类对象之间值的比较，全部使用equals方法比较。
		 * 说明：对于Integer var=?在-128至127之间的赋值，
		 * Integer对象是在IntegerCache.cache产生，会复用已有对象，
		 * 这个区间内的Integer值可以直接使用==进行判断，
		 * 但是这个区间之外的所有数据，都会在堆上产生，
		 * 并不会复用已有对象，
		 * 这是一个大坑，推荐使用equals方法进行判断。
		 *
		 *     Integer a = 235;
		 *     Integer b = 235;
		 *     if (a.equals(b)) {
		 *         // code
		 *     }
		 *
		 */
		System.out.println(i01 == i02);
		System.out.println(i01 == i03);
		System.out.println(i03 == i04);
		System.out.println(i02 == i04);
	}
}
