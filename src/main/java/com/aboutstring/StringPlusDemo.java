package com.aboutstring;


/**
 * @Author hike97
 * @Description
 * @create 2020-10-28 14:50
 * @Modified By:
 **/
public class StringPlusDemo {
	public static void main (String[] args) {
		String s = new String ("1") + new String ("1");

		/*
		 0: new           #2                  // class java/lang/StringBuilder
		 3: dup
		 4: invokespecial #3                  // Method java/lang/StringBuilder."<init>":()V
		 7: new           #4                  // class java/lang/String
		10: dup
		11: ldc           #5                  // String 1
		13: invokespecial #6                  // Method java/lang/String."<init>":(Ljava/lang/String;)V
		16: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
		19: new           #4                  // class java/lang/String
		22: dup
		23: ldc           #5                  // String 1
		25: invokespecial #6                  // Method java/lang/String."<init>":(Ljava/lang/String;)V
		28: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
		31: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
		34: astore_1
		 */
		//		String intern = s.intern ();
		String s2 = "1" + "1";//两个字符串相加  会在编译期优化成“11”。
		//		System.out.println (intern == s2);
		/*
		    35: ldc           #9                  // String 11
      		37: astore_2
		 */
		String s3 = "1";
		String s4 = "1";
		String s5 = s3 + s4;
		/*
		  38: ldc           #5                  // String 1
		  40: astore_3
		  41: ldc           #5                  // String 1
		  43: astore        4
		  45: new           #2                  // class java/lang/StringBuilder
		  48: dup
		  49: invokespecial #3                  // Method java/lang/StringBuilder."<init>":()V
		  52: aload_3
		  53: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
		  56: aload         4
		  58: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
		  61: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
		  64: astore        5
		 */
		//		System.out.println (s == s5);
		//		System.out.println (s == s2);
	}
}
