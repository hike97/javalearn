package mianshi.javabasic.iomodel.linux;

import static mianshi.javabasic.iomodel.linux.LinuxIoSelect.getFileByFd;
import static mianshi.javabasic.iomodel.linux.LinuxIoSelect.schedule_timeout;

/**
 * @author: hike97
 * @createTime: 2023/06/07 16:19
 * @description: TODO
 */
public class LinuxIoPoll {
    class Pollfd {
        int fd; // 需要监控的文件描述符
        int events; // 需要监控的事件类型（POLLIN, POLLOUT, POLLERR等）
        int revents; // 就绪的事件类型（POLLIN, POLLOUT, POLLERR等）
    }
     /*poll函数的工作流程：
     1.将需要监控的文件描述符数组从用户空间拷贝到内核空间
     2.遍历所有的文件描述符，调用相应的poll方法检查是否有就绪事件，并更新revents字段
     3.如果没有就绪事件，就调用schedule_timeout进入睡眠等待唤醒或超时
     4.如果有就绪事件或超时，就将文件描述符数组拷贝回用户空间*/

    public int poll(Pollfd[] fds, int nfds, int timeout) {
        // 将需要监控的文件描述符数组从用户空间拷贝到内核空间
        // 这里用一个数组来模拟内核空间的文件描述符数组，实际上是一个链表结构
        Pollfd[] kfds = new Pollfd[nfds];
        for (int i = 0; i < nfds; i++) {
            kfds[i] = new Pollfd();
            kfds[i].fd = fds[i].fd;
            kfds[i].events = fds[i].events;
            kfds[i].revents = 0;
        }

        // 遍历所有的文件描述符，调用相应的poll方法检查是否有就绪事件，并更新revents字段
        int count = 0; // 记录有多少个文件描述符有就绪事件
        for (int i = 0; i < nfds; i++) {
            if (kfds[i].fd == -1) continue; // 表示该文件描述符无效
            LinuxIoSelect.File file = getFileByFd(kfds[i].fd); // 根据文件描述符获取对应的文件对象
            int mask = file.poll(kfds[i].events); // 调用文件对象的poll方法，传入需要监控的事件类型，返回就绪的事件类型
            if (mask != 0) { // 表示该文件描述符有就绪事件
                count++; // 增加计数器
                kfds[i].revents = mask; // 更新文件描述符数组中的就绪事件类型
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

        // 如果有就绪事件或超时，就将文件描述符数组拷贝回用户空间，并更新revents字段
        for (int i = 0; i < nfds; i++) {
            fds[i].revents = kfds[i].revents;
        }

        return count; // 返回有多少个文件描述符有就绪事件
    }
}
