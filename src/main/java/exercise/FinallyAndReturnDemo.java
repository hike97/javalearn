package exercise;

/**
 * @Author hike97
 * @Description finally 和return 执行顺序问题
 * @create 2020-10-26 15:39
 * @Modified By:
 **/
public class FinallyAndReturnDemo {
	public static int method () {
		try {
			int[] arr = new int[10];
			System.out.println (arr[10]);
			return 1;
		} catch (Exception e) {
			e.printStackTrace ();
			return 2;
		} finally {
			System.out.println ("我一定会执行的");
			return 3; //此处无论结果是什么 都会返回3
		}
	}

	public static void main (String[] args) {
		System.out.println (method ());
		/*
		  分析：
		  case 1 : 有异常
		  		java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 10
				at exercise.FinallyAndReturnDemo.method(FinallyAndReturnDemo.java:13)
				at exercise.FinallyAndReturnDemo.main(FinallyAndReturnDemo.java:25)
				我一定会执行的
				2
		  case 2 : 无异常
				我一定会执行的
				1
		  case 3 : finally 中 return 3
		  		无论是否有异常 都会返回 3
		 */
	}
}
