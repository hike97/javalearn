package mianshi.javabasic.javase;

/**
 * @author: hike97
 * @createTime: 2023/05/18 14:04
 * @description: for循环 ++i 和 i++ 的区别
 */
public class ForDemo {
    // Java for loop i++ and ++i example
    /**
     * i++ 是先使用 i 的值，然后再让 i 加 1；而 ++i 是先让 i 加 1，然后再使用 i 的值。
     * 在 for 循环中，i++ 和 ++i 的结果是一样的，都要等代码块执行完毕才能执行更新表达式，但是性能是不同的。
     * 在大量数据的时候，++i 的性能要比 i++ 的性能好，因为 i++ 需要一个临时的变量来转存，而 ++i 则直接加 1，省去了对内存的操作的环节。
     */
    public static void main(String[] args) {
            int n = 10;
            // for loop with i++
            for (int i = 0; i < n; i++) {
                System.out.println("i++ = " + i);
            }
            // for loop with ++i
            for (int j = 0; j < n; ++j) {
                System.out.println("++j = " + j);
            }
        }
}
