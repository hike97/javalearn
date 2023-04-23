package mianshi.newcode.concurrent.c_009_threadpool;

import static mianshi.tool.BinaryPrint.printBinary;

/**
 * @author: hike97
 * @createTime: 2023/04/21 12:04
 * @description: 演示一下线程池源码的位运算
 */
public class ThreadPoolCapacity {
    private static final int COUNT_BITS = Integer.SIZE-3;
    //11100000 00000000 00000000 00000000
    private static final int RUNNING = -1 << COUNT_BITS;
    //00000000 00000000 00000000 00000000
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    //00100000 00000000 00000000 00000000
    private static final int STOP = 1 << COUNT_BITS;
    //01000000 00000000 00000000 00000000
    private static final int TIDYING = 2 << COUNT_BITS;
    //01100000 00000000 00000000 00000000
    private static final int TERMINATED = 3 << COUNT_BITS;
    public static void main(String[] args) {
        int i = 1 << 29;
        //00100000 00000000 00000000 00000000
        printBinary(i);
        System.out.println("线程池大小capacity");
        int capacity = (1 << 29) - 1;
        //00011111 11111111 11111111 11111111
        printBinary(capacity);

        //11111111 11111111 11111111 11111111
        printBinary(-1);
        System.out.println("线程池状态");
        printBinary(RUNNING);
        printBinary(SHUTDOWN);
        printBinary(STOP);
        printBinary(TIDYING);
        printBinary(TERMINATED);
        System.out.println("线程池状态");
        int rs = -1<<29;
        int wc = 100;
        printBinary(rs|wc);
    }


}
