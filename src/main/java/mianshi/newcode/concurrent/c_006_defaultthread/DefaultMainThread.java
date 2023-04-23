package mianshi.newcode.concurrent.c_006_defaultthread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author: hike97
 * @createTime: 2023/04/19 11:14
 * @description: 主线程默认拥有线程数
 */
public class DefaultMainThread {
    /*
     * [6] Monitor Ctrl-Break //idea run时特有线程
     * [5] Attach Listener  //添加事件
     * [4] Signal Dispatcher // 分发处理给 JVM 信号的线程
     * [3] Finalizer  //调⽤对象 finalize ⽅法的线程
     * [2] Reference Handler  //清除 reference 线程
     * [1] main //main 线程,程序⼊⼝
     */
    public static void main(String[] args) {
        // 获取 Java 线程管理 MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程 ID 和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " +
                    threadInfo.getThreadName());
        }
        new Thread().start();
    }
}
