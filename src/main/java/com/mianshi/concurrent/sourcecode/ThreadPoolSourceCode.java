package com.mianshi.concurrent.sourcecode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ThreadPoolSourceCode
 * @Description 线程池相关源码的计算
 * @Author hike97
 * @Date 2023/3/22 17:34
 * @Version 1.0
 **/
public class ThreadPoolSourceCode {

    /*
    * workerCount，表示线程池中的有效线程数。
      runState，表示线程池的当前状态，比如是否在运行、正在关闭等等。
                为了将这两个字段压缩在一个整型变量中，我们将 workerCount 限制在 (2^29)-1（约 5 亿）个线程范围内，而不是 (2^31)-1（20 亿）个可表示的线程。
                如果将来需要支持更多线程，变量可以更改为 AtomicLong，并且下面的移位/掩码常量应该被调整。不过，在需要之前，使用整型变量的代码更快且更简单。
      workerCount 表示产生了启动但没有停止的线程数。该值在短暂时间内可能会与实际执行线程数不同，例如当请求创建线程的 ThreadFactory 失败时，或者退出线程在终止之前仍在执行一些操作时。
                  用户可见的线程池大小被报告为 worker 集合的当前大小。
      runState 提供主要的生命周期控制，其取值有：
      RUNNING：接受新的任务并处理排队的任务。
      SHUTDOWN：不接受新的任务，但处理已排队的任务。
      STOP：不接受新的任务，不处理已排队的任务，并中断正在处理的任务。
      TIDYING：所有任务已终止，workerCount 为 0，正在转换为 TIDYING 状态的线程将运行 terminated() 钩子方法。
      TERMINATED：terminated() 方法已完成。
      这些值之间的数字顺序很重要，以允许有序比较。runState 随时间单调增加，但不必达到每个状态。转换方式如下：

      RUNNING → SHUTDOWN：在调用 shutdown() 时（可能是在 finalize() 中隐式调用）。
      (RUNNING 或 SHUTDOWN) → STOP：在调用 shutdownNow() 时。
      SHUTDOWN → TIDYING：当队列和线程池都为空时。
      STOP → TIDYING：当线程池为空时。
      TIDYING → TERMINATED：当 terminated() 钩子方法完成时。
      等待在 awaitTermination() 中的线程将在状态达到 TERMINATED 时返回。
    * */

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    public static void main(String[] args) {
        printBinaryString(RUNNING);
        printBinaryString(SHUTDOWN);
        printBinaryString(STOP);
        printBinaryString(TIDYING);
        printBinaryString(TERMINATED);
    }

    public static void printBinaryString(int n) {
        String binaryString = Integer.toBinaryString(n);
        int len = binaryString.length();
        if (len < 32) {  // 前导 0 比较少，需要补全
            for (int i = 0, num = 32 - len; i < num; ++i) {
                binaryString = "0" + binaryString;
            }
        }

        for (int i = 0; i < binaryString.length(); ++i) {
            System.out.print(binaryString.charAt(i));
            if ((i + 1) % 8 == 0) {
                System.out.print(" "); // 分割符
            }
        }
        System.out.println();
    }


}
