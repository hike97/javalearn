package mianshi.javabasic.javase.c_005_memory_allocate;

/**
 * @author: hike97
 * @createTime: 2023/04/16 20:14
 * @description: 内存逃逸分析
 */
public class EscapeAnalysis {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000_0000; i++) {
            allocate();
        }
        long end = System.currentTimeMillis();
        System.out.println("cost: " + (end - start) + " ms");
    }

    private static void allocate() {
        User user = new User();
    }

    /*
     * 这里的 User 对象在 allocate 方法中创建，但是没有被返回或者赋值给其他变量，所以它的作用域仅限于 allocate 方法内部。
     * 这种情况下，如果进行逃逸分析，就可以判断出 User 对象不会逃逸出方法，因此可以在栈上分配内存1。这样就避免了在堆上分配内存和触发垃圾回收的开销。
     * 如果我们运行这段代码，并且开启逃逸分析的选项 -XX:+DoEscapeAnalysis，可以看到输出如下：6s
     * -XX:-DoEscapeAnalysis 40s 内存逃逸分析默认开启（since 1.7）
     */
    static class User {

    }
}
