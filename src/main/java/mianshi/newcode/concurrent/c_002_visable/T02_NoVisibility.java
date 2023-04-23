package mianshi.newcode.concurrent.c_002_visable;

/**
 * @ClassName T02_NoVisibility
 * @Description 可见性和顺序性demo
 * @Author hike97
 * @Date 2023/3/23 18:03
 * @Version 1.0
 **/
public class T02_NoVisibility {
    private static boolean ready = false;
    private static int number;

    private static class ReadThread extends Thread{
        @Override
        public void run() {
            while (!ready){
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ReadThread t = new ReadThread();
        t.start();
        //会产生指令重排
        number = 42;
        ready =true;
        t.join();

    }

}
