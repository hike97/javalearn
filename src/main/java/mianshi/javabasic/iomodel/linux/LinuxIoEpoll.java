package mianshi.javabasic.iomodel.linux;


import java.util.List;

import static mianshi.javabasic.iomodel.linux.LinuxIoSelect.getFileByFd;
import static mianshi.javabasic.iomodel.linux.LinuxIoSelect.schedule_timeout;

/**
 * @author: hike97
 * @createTime: 2023/06/07 16:21
 * @description: linux epoll 伪代码
 */
public class LinuxIoEpoll {
    private static final int EPOLL_CTL_ADD = 0;
    private static final int EPOLL_CTL_DEL = 1;
    private static final int EPOLL_CTL_MOD = 2;


    class EpollEvent {
        int fd; // 需要监控的文件描述符
        int events; // 需要监控的事件类型（EPOLLIN, EPOLLOUT, EPOLLET等）
        int data; // 用户自定义数据
    }


    /*
    epoll函数的工作流程：
        1.调用epoll_create创建一个epoll实例，返回一个文件描述符
        2.调用epoll_ctl向epoll实例中注册或修改或删除需要监控的文件描述符和事件，内核会为每个文件描述符维护一个就绪事件列表
        3.调用epoll_wait从epoll实例中获取就绪事件，内核会遍历所有的文件描述符，将有就绪事件的文件描述符复制到用户空间的数组中
    */

    /**
     * size: 创建epoll实例时指定的大小（已被忽略）
     * 返回一个epoll实例的文件描述符
     * @param size
     * @return
     */
    public int epoll_create(int size) {
        // 创建一个epoll实例，这里用一个对象来模拟
        Epoll epoll = new Epoll();
        // 返回一个文件描述符，这里用一个整数来模拟
        int epfd = getFdByEpoll(epoll);
        return epfd;
    }



    /**
     * @param epfd epoll实例的文件描述符
     * @param op    对epoll实例进行的操作（EPOLL_CTL_ADD, EPOLL_CTL_MOD, EPOLL_CTL_DEL）
     * @param fd   需要监控的文件描述符
     * @param event  需要监控的事件（EPOLLIN, EPOLLOUT, EPOLLET等）
     * @return
     */
    public int epoll_ctl(int epfd, int op, int fd, EpollEvent event) {
        // 根据文件描述符获取对应的epoll实例对象
        Epoll epoll = getEpollByFd(epfd);
        // 根据操作类型进行相应的处理
        switch (op) {
            case EPOLL_CTL_ADD: // 注册新的文件描述符和事件到epoll实例中
                if (epoll.contains(fd)) { // 如果已经存在该文件描述符，返回错误码
                    return -1;
                } else { // 否则，将该文件描述符和事件添加到epoll实例中，并设置回调函数
                    epoll.add(fd, event);
                    LinuxIoSelect.File file = getFileByFd(fd); // 根据文件描述符获取对应的文件对象
                    file.setCallback(() -> { // 设置回调函数，当该文件有就绪事件时，将其加入到epoll实例的就绪事件列表中
                        EpollEvent e = new EpollEvent();
                        e.fd = fd;
                        e.events = file.poll(event.events); // 调用文件对象的poll方法，传入需要监控的事件类型，返回就绪的事件类型
                        e.data = event.data; // 复制用户自定义数据
                        epoll.readyList.add(e); // 将就绪事件加入到epoll实例的就绪事件列表中
                    });
                    return 0;
                }
            case EPOLL_CTL_MOD: // 修改已经注册的文件描述符和事件
                if (epoll.contains(fd)) { // 如果存在该文件描述符，更新其对应的事件
                    epoll.update(fd, event);
                    return 0;
                } else { // 否则，返回错误码
                    return -1;
                }
            case EPOLL_CTL_DEL: // 删除已经注册的文件描述符和事件
                if (epoll.contains(fd)) { // 如果存在该文件描述符，删除其对应的事件，并取消回调函数
                    epoll.remove(fd);
                    LinuxIoSelect.File file = getFileByFd(fd); // 根据文件描述符获取对应的文件对象
                    file.setCallback(null); // 取消回调函数
                    return 0;
                } else { // 否则，返回错误码
                    return -1;
                }
            default: // 无效的操作类型，返回错误码
                return -1;
        }
    }



    /**
     * 返回就绪事件的个数，或者-1表示失败
     * @param epfd epoll实例的文件描述符
     * @param events 用于接收就绪事件的数组
     * @param maxevents 接收就绪事件的数组的最大容量
     * @param timeout 定时阻塞监控时间，3种情况 -1: 永远等下去 0: 检查描述字后立即返回，轮询 >0: 等待指定毫秒数
     * @return
     */
    public int epoll_wait(int epfd, EpollEvent[] events, int maxevents, int timeout) {
        // 根据文件描述符获取对应的epoll实例对象
        Epoll epoll = getEpollByFd(epfd);
        // 如果没有就绪事件，就调用schedule_timeout进入睡眠等待唤醒或超时
        if (epoll.readyList.isEmpty()) {
            if (timeout == -1) { // 永远等下去
                schedule_timeout(Long.MAX_VALUE); // 调用内核的方法进入睡眠，直到被唤醒
            } else if (timeout == 0) { // 检查描述字后立即返回，轮询
                return 0; // 直接返回0，表示没有就绪事件
            } else { // 等待指定毫秒数
                schedule_timeout(timeout); // 调用内核的方法进入睡眠，直到被唤醒或超时
            }
        }

        // 如果有就绪事件或超时，就将就绪事件列表中的文件描述符复制到用户空间的数组中，并清空就绪事件列表
        int count = 0; // 记录有多少个文件描述符有就绪事件
        for (EpollEvent e : epoll.readyList) {
            if (count < maxevents) { // 如果用户空间的数组还有空间，复制该就绪事件到数组中
                events[count] = e;
                count++;
            } else { // 否则，跳出循环
                break;
            }
        }
        epoll.readyList.clear(); // 清空就绪事件列表

        return count; // 返回有多少个文件描述符有就绪事件
    }

    class Epoll{

        public List<EpollEvent> readyList;

        public boolean contains(int fd) {
            return false;
        }

        public void add(int fd, EpollEvent event) {

        }

        public void update(int fd, EpollEvent event) {

        }

        public void remove(int fd) {

        }
    }

    private int getFdByEpoll(Epoll epoll) {
        return 0;
    }

    private Epoll getEpollByFd(int epfd) {
        return null;
    }

}
