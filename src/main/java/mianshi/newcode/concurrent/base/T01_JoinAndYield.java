package mianshi.newcode.concurrent.base;

/**
 * @ClassName T01_JoinAndYeild
 * @Description Join 和 yield 的区别
 * @Author hike97
 * @Date 2023/3/23 18:21
 * @Version 1.0
 **/
public class T01_JoinAndYield {

    public static void main(String[] args) throws InterruptedException {

        // 使用yield示例
        Thread yieldThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Yield Thread: " + i);
                //交出线程使用权
                Thread.yield();
            }
        });
        yieldThread.start();

        // 使用join示例
        Thread joinThread = new Thread(() -> {
            System.out.println("Join Thread: Start");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Join Thread: End");
        });
        joinThread.start();
        //主线程需要等到join线程执行完毕  主线程 才能结束
        joinThread.join();

        System.out.println("Main Thread: End");
    }

}
