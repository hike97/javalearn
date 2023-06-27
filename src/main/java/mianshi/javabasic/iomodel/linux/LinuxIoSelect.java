package mianshi.javabasic.iomodel.linux;

import java.util.Set;

/**
 * @author: hike97
 * @createTime: 2023/06/07 16:06
 * @description: linux 内核select poll epoll 伪代码
 */
public class LinuxIoSelect {



   /* select函数的工作流程：
        1.将需要监控的文件描述符集合从用户空间拷贝到内核空间
        2.遍历所有的文件描述符，调用相应的poll方法检查是否有就绪事件
        3.如果没有就绪事件，就调用schedule_timeout进入睡眠等待唤醒或超时
        4.如果有就绪事件或超时，就遍历所有的文件描述符，收集就绪事件并返回给用户空间
    */
    /**
     *
     * @param n 监控的文件描述符集里最大文件描述符加1
     * @param readfds 监控有读数据到达文件描述符集合，传入传出参数
     * @param writefds 监控写数据到达文件描述符集合，传入传出参数
     * @param exceptfds 监控异常发生达文件描述符集合, 传入传出参数
     * @param timeout 定时阻塞监控时间，3种情况   -1: 永远等下去 0: 检查描述字后立即返回，轮询  >0: 等待指定毫秒数
     * @return
     */
    public int select(int n, Set<Integer> readfds, Set<Integer> writefds, Set<Integer> exceptfds, long timeout) {
        // 将需要监控的文件描述符集合从用户空间拷贝到内核空间
        // 这里用一个数组来模拟内核空间的文件描述符集合
        int[] fds = new int[n];
        for (int fd : readfds) {
            fds[fd] = 1; // 表示该文件描述符需要监控读事件
        }
        for (int fd : writefds) {
            fds[fd] = 2; // 表示该文件描述符需要监控写事件
        }
        for (int fd : exceptfds) {
            fds[fd] = 3; // 表示该文件描述符需要监控异常事件
        }

        // 遍历所有的文件描述符，调用相应的poll方法检查是否有就绪事件
        int count = 0; // 记录有多少个文件描述符有就绪事件
        for (int i = 0; i < n; i++) {
            if (fds[i] == 0) continue; // 表示该文件描述符不需要监控任何事件
            File file = getFileByFd(i); // 根据文件描述符获取对应的文件对象
            int mask = file.poll(fds[i]); // 调用文件对象的poll方法，传入需要监控的事件类型，返回就绪的事件类型
            if (mask != 0) { // 表示该文件描述符有就绪事件
                count++; // 增加计数器
                fds[i] = mask; // 更新文件描述符集合中的事件类型为就绪的事件类型
            }
        }

        // 如果没有就绪事件，就调用schedule_timeout进入睡眠等待唤醒或超时
        if (count == 0) {
            if (timeout == -1) { // 永远等下去
                schedule_timeout(Long.MAX_VALUE); // 调用内核的方法进入睡眠，直到被唤醒
            } else if (timeout == 0) { // 检查描述字后立即返回，轮询
                return 0; // 直接返回0，表示没有就绪事件
            } else { // 等待指定毫秒数
                schedule_timeout(timeout); // 调用内核的方法进入睡眠，直到被唤醒或超时
            }
        }

        // 如果有就绪事件或超时，就遍历所有的文件描述符，收集就绪事件并返回给用户空间
        for (int i = 0; i < n; i++) {
            if (fds[i] == 0) continue; // 表示该文件描述符没有就绪事件
            if ((fds[i] & 1) != 0) { // 表示该文件描述符有读就绪事件
                readfds.add(i); // 将该文件描述符加入到读集合中
            }
            if ((fds[i] & 2) != 0) { // 表示该文件描述符有写就绪事件
                writefds.add(i); // 将该文件描述符加入到写集合中
            }
            if ((fds[i] & 3) != 0) { // 表示该文件描述符有异常就绪事件
                exceptfds.add(i); // 将该文件描述符加入到异常集合中
            }
        }

        return count; // 返回有多少个文件描述符有就绪事件
    }

    public static void schedule_timeout(long maxValue) {
    }

    public static File getFileByFd(int i) {
        return null;
    }
    public class File {
        public int poll(int fd) {
            return 1;
        }

        public void setCallback(CallBackInterface o) {

        }
    }
}

