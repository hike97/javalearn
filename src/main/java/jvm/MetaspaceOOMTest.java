package jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author hike97
 * @Description 元空间异常
 * @create 2021-01-06 17:09
 * @Modified By:
 **/
public class MetaspaceOOMTest {
	static class OOMTest{}
	public static void main (String[] args) {
		int i = 0;
		try {
			while (true){
				i++;
				Enhancer enhancer = new Enhancer ();
				enhancer.setSuperclass (OOMTest.class);
				enhancer.setUseCache (false);
				enhancer.setCallback (new MethodInterceptor () {
					@Override
					public Object intercept (Object o, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
						return proxy.invokeSuper (o,args);
					}
				});
				enhancer.create ();
			}
		} catch (Throwable e) {
			System.out.println ("*************************多少次后发生了异常： "+i);
			e.printStackTrace ();
		}
	}
}
