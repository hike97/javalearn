package mianshi.spring.cycle;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * @author: hike97
 * @createTime: 2023/05/06 22:14
 * @description: Spring循环依赖测试
 */
public class CycleTest {
    private final static Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
    private final static Map<String, Supplier<?>> singletonFactories = new HashMap<>(16);
    private final static Map<String, Class<?>> beanClasses = new HashMap<> (); // 新增一个Map

    static {
        // 初始化Map，将beanName和类对象对应起来
        beanClasses.put("a", A.class);
        beanClasses.put("b", B.class);
    }
    public static void main (String [] args) throws Exception {
        System.out.println (getBean (B.class).getA ());
        System.out.println (getBean (A.class).getB ());
    }
    private static <T> T getBean (Class<T> beanClass) throws Exception {
        String beanName = beanClass.getSimpleName ().toLowerCase ();
        // 先从一级缓存中获取
        if (singletonObjects.containsKey (beanName)) {
            return (T) singletonObjects.get (beanName);
        }
        // 实例化对象
        Object obj = beanClass.newInstance ();
        // 添加到三级缓存中
        singletonFactories.put(beanName, () -> getEarlyBeanReference(beanName, obj));
        // 属性填充
        Field[] fields = obj.getClass ().getDeclaredFields ();
        for (Field field : fields) {
            field.setAccessible (true);
            Class<?> fieldClass = field.getType ();
            String fieldBeanName = fieldClass.getSimpleName ().toLowerCase ();
            // 从一级缓存或者三级缓存中获取依赖对象
            field.set (obj, singletonObjects.containsKey (fieldBeanName) ? singletonObjects.get (fieldBeanName) : getBeanFromFactory(fieldBeanName));
            field.setAccessible (false);
        }
        // 添加到一级缓存中
        singletonObjects.put(beanName, obj);
        // 从三级缓存中移除
        singletonFactories.remove(beanName);
        return (T) obj;
    }

    private static Object getEarlyBeanReference(String beanName, Object bean) {
        // 这里可以进行AOP增强，返回代理对象
        return bean;
    }

    private static Object getBeanFromFactory(String beanName) throws Exception {
        Supplier<?> factory = singletonFactories.get(beanName);
        if (factory != null) {
            return factory.get();
        } else {
            // 从Map中获取类对象，而不是使用Class.forName
            return getBean(beanClasses.get(beanName));
        }
    }
}

class A {
    private B b;
    // ...get/set

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}

class B {
    private A a;
    // ...get/set

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
