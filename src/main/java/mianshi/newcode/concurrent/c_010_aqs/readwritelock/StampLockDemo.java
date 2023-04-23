package mianshi.newcode.concurrent.c_010_aqs.readwritelock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author: hike97
 * @createTime: 2023/04/19 19:45
 * @description:
 * StampedLock是Java 8引入的一种新的读写锁，它有三种模式：写模式、读模式和乐观读模式 。
 *
 * 写模式：只有一个线程能获取写锁，获取写锁后，其他线程不能获取读锁或写锁，直到写锁被释放。
 * 读模式：多个线程可以同时获取读锁，获取读锁后，其他线程不能获取写锁，但可以获取读锁，直到所有读锁被释放。
 * 乐观读模式：多个线程可以同时获取乐观读锁，获取乐观读锁后，其他线程可以获取写锁、读锁或乐观读锁。乐观读模式不会阻止写操作，所以需要在使用数据前后进行校验。
 * 使用StampedLock的一般步骤是：
 *
 * 创建一个StampedLock实例；
 * 在写操作前，调用writeLock()方法获取写锁，并返回一个stamp（类似于时间戳）；
 * 在写操作后，调用unlockWrite(stamp)方法释放写锁；
 * 在读操作前，调用readLock()方法获取读锁，并返回一个stamp；
 * 在读操作后，调用unlockRead(stamp)方法释放读锁；
 * 在乐观读操作前，调用tryOptimisticRead()方法获取乐观读锁，并返回一个stamp；
 * 在乐观读操作后，调用validate(stamp)方法校验数据是否被修改过，如果返回true，则表示数据有效，否则表示数据无效。
 */
public class StampLockDemo {
    private double x;
    private double y;
    private final StampedLock stampedLock = new StampedLock();

    // 写操作：移动坐标点
    public void move(double deltaX, double deltaY) {
        // 获取写锁
        long stamp = stampedLock.writeLock();
        try {
            // 修改x和y
            x += deltaX;
            y += deltaY;
        } finally {
            // 释放写锁
            stampedLock.unlockWrite(stamp);
        }
    }

    // 读操作：计算到原点的距离
    public double distanceFromOrigin() {
        // 获取读锁
        long stamp = stampedLock.readLock();
        try {
            // 读取x和y
            return Math.sqrt(x * x + y * y);
        } finally {
            // 释放读锁
            stampedLock.unlockRead(stamp);
        }
    }

    // 乐观读操作：如果坐标点没有移动，返回true，否则返回false
    public boolean isOrigin() {
        // 获取乐观读锁
        long stamp = stampedLock.tryOptimisticRead();
        // 读取x和y
        double currentX = x;
        double currentY = y;
        // 校验数据是否被修改过
        if (!stampedLock.validate(stamp)) {
            // 如果被修改过，获取读锁
            stamp = stampedLock.readLock();
            try {
                // 重新读取x和y
                currentX = x;
                currentY = y;
            } finally {
                // 释放读锁
                stampedLock.unlockRead(stamp);
            }
        }
        // 判断是否是原点
        return currentX == 0 && currentY == 0;
    }
}
