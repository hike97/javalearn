package mianshi.newcode.concurrent.c_009_threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: hike97
 * @createTime: 2023/04/20 21:12
 * @description: 线程池重命名
 */
public class NamingThreadPoolFactory implements ThreadFactory {
    private final AtomicInteger threadNum = new AtomicInteger();
    private final ThreadFactory delegate;
    private final String name;

    public NamingThreadPoolFactory(ThreadFactory delegate, String name) {
        this.delegate = delegate;
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = delegate.newThread(r);
        t.setName(name + " [#" + threadNum.incrementAndGet() + "]");
        System.out.println(t.getName());
        return t;
    }
}
