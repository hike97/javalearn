package mianshi.newcode.concurrent.c_008_unsafe;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * @author: hike97
 * @createTime: 2023/04/19 17:58
 * @description: TODO
 */
@Slf4j
public class UnsafeDemo {

    private static final Unsafe unsafe = reflectGetUnsafe();

    private volatile int a;

    public static void main(String[] args) {
        //System.out.println(Unsafe.getUnsafe());
        //1、利用反射获得 Unsafe 类中已经实例化完成的单例对象 theUnsafe 。
        Unsafe unsafe = reflectGetUnsafe();
        System.out.println(unsafe);
    }
    /**
     * 1.内存拷贝
     */
    @Test
    public void unsafeMemoryTest() {
        int size = 4;
        //申请4byte
        long addr = unsafe.allocateMemory(size);
        //重新分配了一块8byte的空间
        long addr3 = unsafe.reallocateMemory(addr, size * 2);
        System.out.println("addr: " + addr);
        System.out.println("addr3: " + addr3);
        try {
            //
            unsafe.setMemory(null, addr, size, (byte) 1);
            for (int i = 0; i < 2; i++) {
                unsafe.copyMemory(null, addr, null, addr3 + size * i, 4);
            }
            System.out.println(unsafe.getInt(addr));
            System.out.println(unsafe.getLong(addr3));
        } finally {
            unsafe.freeMemory(addr);
            unsafe.freeMemory(addr3);
        }
    }
    /**
     * 2.内存屏障 应用StampedLock
     */
    @Test
    public void testMemoryFence() {
            ChangeThread changeThread = new ChangeThread();
            new Thread(changeThread).start();
            while (true) {
                boolean flag = changeThread.isFlag();
                //System.out.println("System 里面有synchronized 关键字导致flag被刷到主内存");
                unsafe.loadFence(); //加入读内存屏障
                if (flag){
                    System.out.println("detected flag changed");
                    break;
                }
            }
            System.out.println("main thread end");
    }

    /**
     * 6.Class操作
     * 如果一个类没有被实例化，那么它的静态属性也不会被初始化，最后获取的字段属性将是null
     */
    @Test
    public void testClassOperation() throws NoSuchFieldException {
        //User user=new User();
        //是否需要实例化
        System.out.println(unsafe.shouldBeInitialized(User.class));
        Field nameField = User.class.getDeclaredField("name");
        //获取静态属性偏移量
        long fieldOffset = unsafe.staticFieldOffset(nameField);
        //获取静态属性指针
        Object fieldBase = unsafe.staticFieldBase(nameField);
        //判断类是否需要实例化（用于获取类的静态属性前进行检测）
        Object object = unsafe.getObject(fieldBase, fieldOffset);
        System.out.println(object);
    }

    /**
     * 7.获取系统信息
     */
    @Test
    public void testSystemInfo() {
        //返回系统指针的大小。返回值为4（32位系统）或 8（64位系统）。
        System.out.println(unsafe.addressSize());
        //内存页的大小，此值为2的幂次方。
        System.out.println(unsafe.pageSize());

    }
    /**
     * 6.使用defineClass方法允许程序在运行时动态地创建一个类
     */
    @Test
    public void defineTest() {
        String fileName="E:\\idea_workspace_2021\\java8learn\\target\\classes\\mianshi\\newcode\\concurrent\\c_008_unsafe\\User.class";
        File file = new File(fileName);
        try(FileInputStream fis = new FileInputStream(file)) {
            byte[] content=new byte[(int)file.length()];
            fis.read(content);
            Class clazz = unsafe.defineClass(null, content, 0, content.length, null, null);
            Object o = clazz.newInstance();
            Object age = clazz.getMethod("getAge").invoke(o, null);
            System.out.println(age);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 5.线程调度
     */
    @Test
    public void threadScheduling() {
        Thread mainThread = Thread.currentThread();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("subThread try to unpark mainThread");
                unsafe.unpark(mainThread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("park main mainThread");
        unsafe.park(false,0L);
        System.out.println("unpark mainThread success");
    }
    /**
     * 4.Cas
     */
    @Test
    public void testCas() {
        UnsafeDemo unsafeDemo = new UnsafeDemo();
        new Thread(()->{
            for (int i = 1; i < 5; i++) {
                unsafeDemo.increment(i);
                System.out.print(unsafeDemo.a+" ");
            }
        }).start();
        new Thread(()->{
            for (int i = 5 ; i <10 ; i++) {
                unsafeDemo.increment(i);
                System.out.print(unsafeDemo.a+" ");
            }
        }).start();
    }
    /**
     * 3.对象操作
     */
    @Test
    public void objTest() throws Exception{
        A a1=new A();
        System.out.println(a1.getB());
        A a2 = A.class.newInstance();
        System.out.println(a2.getB());
        A a3= (A) unsafe.allocateInstance(A.class);
        System.out.println(a3.getB());
    }

    private void increment(int x){
        while (true){
            try {
                //获取a的偏移量
                long fieldOffset = unsafe.objectFieldOffset(UnsafeDemo.class.getDeclaredField("a"));
                if (unsafe.compareAndSwapInt(this,fieldOffset,x-1,x)) {
                    break;
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    @Getter
    private static class ChangeThread implements Runnable{
        /**volatile**/ boolean flag=false;
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.println("subThread change flag to:" + flag);
        }
    }

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

}
@Data
class A {
    private int b;
    public A(){
        this.b =1;
    }
}
