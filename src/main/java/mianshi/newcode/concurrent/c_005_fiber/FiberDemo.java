//package mianshi.newcode.concurrent.c_005_fiber;
//
//import co.paralleluniverse.fibers.Fiber;
//import co.paralleluniverse.strands.SuspendableRunnable;
//
//import java.util.concurrent.ExecutionException;
//
///**
// * @author: hike97
// * @createTime: 2023/04/11 13:32
// * @description: 纤程demo
// */
//public class FiberDemo {
//    /*
//     * 用户态的线程，线程中的线程，切换和调度不需要经过OS
//     * 优势：1.占用资源少 线程1M 纤程4k 2.切换比较简单 3.启动很多个10w+
//     * 目前支持的语言：kotlin scala go python(lib) java14(需要类库)
//     */
//    static void m1() {
//        String m;
//        System.out.println("m1 begin");
//        m = m2();
//        m = m3();
//        System.out.println("m1 end");
//        System.out.println(m);
//    }
//    static String m2() {
//        return "m2";
//    }
//    static String m3() {
//        return "m3";
//    }
//    static public void main(String[] args) throws ExecutionException, InterruptedException {
//        new Fiber<Void>("Caller", (SuspendableRunnable) () -> m2()).start();
//        new Fiber<Void>("Caller", (SuspendableRunnable) () -> m1()).start();
//    }
//}
