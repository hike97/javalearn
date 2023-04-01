package mianshi.neo_concurrent.base.democlazz;

import java.util.concurrent.Callable;

/**
 * @ClassName MyTask
 * @Description TODO
 * @Author hike97
 * @Date 2023/4/1 14:26
 * @Version 1.0
 **/
public class MyTask implements Callable<Long> {
    @Override
    public Long call() throws Exception {
        long r = 0L;
        for(long i=0L; i<10L; i++) {
            r += i;
            Thread.sleep(500);
            System.out.println(i + " added!");
        }
        return r;
    }
}
