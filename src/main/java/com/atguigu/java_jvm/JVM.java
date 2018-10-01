package com.atguigu.java_jvm;

import org.junit.Test;

import java.util.Random;

/**
 * @author hike97
 * @create 2018-09-20 19:34
 * @desc JVM快速入门
 **/
public class JVM {
    /*
        1.虚拟机自带的加载器
            Bootstrap C++
            Extension Java
            应用程序加载器（App）Java 也叫系统类加载器，加载当前应用的classpath的所有类
        用户自定义加载器
            java.lang.ClassLoader的子类，用户可以定制类的加载方式
     */
    public static void main(String[] args) {
        //object 默认是bootstrap加载器
        Object o = new Object();
        ClassLoader classLoader = o.getClass().getClassLoader();
        System.out.println( "java自带obj类加载器："+classLoader );

        JVM jvm = new JVM();
        ClassLoader loader = jvm.getClass().getClassLoader();
        System.out.println( loader );
    }
    /*
        2.native Interface 本地接口
        java 语言本身不能对操作系统底层进行访问和操作，
        但可以通过 JNI 接口调用其它语言实现对底层的访问。
        专门处理C/C++程序
        Native Method Stack 本地方法栈里
     */

    /*
     3.PC寄存器
     用来存储指向下一条指令的地址，即将执行的指令代码
   */
    /*
     *4.对于栈来说 不存在垃圾回收问题
     * 基本类型的变量 实例方法 引用类型变量 都是在函数的栈内存中分配
     */
    /*
      堆： 伊甸区（Eden Space）
          幸存0区（Survivor 0 Space）
          幸存1区（Survivor 1 Space）
          养老区（Tenure Generation Space）
          永久存储区（Permanent Space）--又称为非堆内存
          逻辑上分为 新生 + 养老 + 永久
          1.7之前
          1.8取消永久区 变为元空间
          异常：OutOfMemoryError:Java heap space 异常
          堆内存不够
                ---堆参数调优：
          -Xms 堆内存 初始大小  默认物理内存的 "1/64".
          -Xmx 对大分配内存 默认物理内存的1/4.
          -XX：+printGCDetails 输出详细的GC处理日志
     */
    @Test
    public void test_1() {
        /**
         * MAX_MEMORY = 1029177344（字节）、981.5MB
         TOTAL_MEMORY = 1029177344（字节）、981.5MB
         Heap
         PSYoungGen      total 305664K, used 20971K [0x00000000eab00000, 0x0000000100000000, 0x0000000100000000)
             eden space 262144K, 8% used [0x00000000eab00000,0x00000000ebf7ae40,0x00000000fab00000)
             from space 43520K, 0% used [0x00000000fd580000,0x00000000fd580000,0x0000000100000000)
             to   space 43520K, 0% used [0x00000000fab00000,0x00000000fab00000,0x00000000fd580000)
         ParOldGen       total 699392K, used 0K [0x00000000c0000000, 0x00000000eab00000, 0x00000000eab00000)
            object space 699392K, 0% used [0x00000000c0000000,0x00000000c0000000,0x00000000eab00000)
         Metaspace       used 5136K, capacity 5268K, committed 5504K, reserved 1056768K
            class space    used 599K, capacity 627K, committed 640K, reserved 1048576K
         */
        //jVM参数 -Xms1024m -Xmx1024m -XX:+PrintGCDetails
        //summary 堆实际内存 只包括新生和养老 不包括 （永久（1.7）和元空间）
        //返回Java虚拟机试图使用的最大内存量
        //计算 PSYoungGen+ParOldGen = 305664K+699392K = 981.5MB
        long maxMemory = Runtime.getRuntime().maxMemory();
        //返回Java虚拟机中的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println( "MAX_MEMORY = " + maxMemory + "（字节）、" + (maxMemory / (double) 1024 / 1024) + "MB" );
        System.out.println( "TOTAL_MEMORY = " + totalMemory + "（字节）、" + (totalMemory / (double) 1024 / 1024) + "MB" );
    }

    /**
     * 堆内存溢出测试
     */
    /*
    [GC (Allocation Failure) [PSYoungGen: 1536K->488K(2048K)] 1536K->656K(7680K), 0.0012569 secs] [Times: user=0.00 sys=0.00, real=0.04 secs] [GC (Allocation Failure) [PSYoungGen: 2024K->488K(2048K)] 2192K->865K(7680K), 0.0012458 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    [GC (Allocation Failure) [PSYoungGen: 2024K->504K(2048K)] 2401K->1153K(7680K), 0.0007658 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] [GC (Allocation Failure) [PSYoungGen: 2039K->504K(2048K)] 2689K->1387K(7680K), 0.0042302 secs] [Times: user=0.03 sys=0.00, real=0.00 secs] [GC (Allocation Failure) [PSYoungGen: 1879K->488K(2048K)] 2762K->1643K(7680K), 0.0020009 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
    [GC (Allocation Failure) [PSYoungGen: 1916K->488K(2048K)] 3871K->2674K(7680K), 0.0010898 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    [Full GC (Ergonomics) [PSYoungGen: 1317K->0K(2048K)] [ParOldGen: 5384K->1891K(5632K)] 6701K->1891K(7680K), [Metaspace: 5112K->5112K(1056768K)], 0.0078235 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
    [GC (Allocation Failure) [PSYoungGen: 829K->0K(2048K)] 4319K->3489K(7680K), 0.0003876 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    [GC (Allocation Failure) [PSYoungGen: 0K->0K(2048K)] 3489K->3489K(7680K), 0.0002684 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    [Full GC (Allocation Failure) [PSYoungGen: 0K->0K(2048K)] [ParOldGen: 3489K->3368K(5632K)] 3489K->3368K(7680K), [Metaspace: 5114K->5114K(1056768K)], 0.0080546 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
    [GC (Allocation Failure) [PSYoungGen: 0K->0K(2048K)] 3368K->3368K(7680K), 0.0003413 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    [Full GC (Allocation Failure) [PSYoungGen: 0K->0K(2048K)] [ParOldGen: 3368K->3310K(5632K)] 3368K->3310K(7680K), [Metaspace: 5114K->5110K(1056768K)], 0.0090160 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
    java.lang.OutOfMemoryError: Java heap space
    Heap
     PSYoungGen      total 2048K, used 409K [0x00000000ffd80000, 0x0000000100000000, 0x0000000100000000)
      eden space 1536K, 26% used [0x00000000ffd80000,0x00000000ffde6568,0x00000000fff00000)
      from space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
      to   space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
     ParOldGen       total 5632K, used 3310K [0x00000000ff800000, 0x00000000ffd80000, 0x00000000ffd80000)
  object space 5632K, 58606K, capacity 626K, committed 640K, reserved 1048576K
     */
    //jVM参数 -Xms8m -Xmx8m -XX:+PrintGCDetails
    @Test
    public void test_heap() {
        java.lang.String str = "www.atguigu.com";
        while (true) {
            str+= str +new Random().nextInt(888888) + new Random().nextInt(9999);
        }
    }
}
