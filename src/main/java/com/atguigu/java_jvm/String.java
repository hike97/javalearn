package com.atguigu.java_jvm;

/**
 * @author hike97
 * @create 2018-09-20 19:48
 * @desc 类加载器的：双亲委派机制+沙箱机制（防止恶意代码对java的破坏）
 **/
public class String {
    public static void main(String[] args) {
        /*
            首先将加载任务委托给父类加载器
            依次递归 ，如果福类加载器可以完成类加载任务，则成功返回；
            只有父类加载器无法完成此加载任务时，才自己去加载。
         */
    }
}
