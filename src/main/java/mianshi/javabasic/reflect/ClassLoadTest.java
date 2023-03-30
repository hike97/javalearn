package mianshi.javabasic.reflect;

/**
 * @Author hike97
 * @Description
 * @create 2020-11-20 16:22
 * @Modified By:
 **/
public class ClassLoadTest {
	public static void main (String[] args) {
		System.out.println (A.m);
	}

}
class A {
	static int m = 100;
	static {
		m = 300;
	}
}
/**
 * 类加载过程
 * 加载
 * 链接
 * 初始化
 * static <clinit>()V
 *    L0
 *     LINENUMBER 17 L0
 *     SIPUSH 300
 *     PUTSTATIC javaBasic/reflect/A.m : I
 *    L1
 *     LINENUMBER 19 L1
 *     BIPUSH 100
 *     PUTSTATIC javaBasic/reflect/A.m : I
 *     RETURN
 *     MAXSTACK = 1
 *     MAXLOCALS = 0
 */
