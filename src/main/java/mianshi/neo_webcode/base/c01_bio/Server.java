package mianshi.neo_webcode.base.c01_bio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName Server
 * @Description 服务端
 * @Author hike97
 * @Date 2023/3/30 22:22
 * @Version 1.0
 **/
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress("localhost",8888));
        boolean started = true;
        while (started) {
            Socket acceptSocket = ss.accept();
            //浪费资源：只适用于 已知客户端的情况
            new Thread(()->{
                try {
                    //server接收
                    BufferedReader reader = new BufferedReader(new InputStreamReader(acceptSocket.getInputStream()));
                    String str = reader.readLine();
                    System.out.println(str);

                    //server发送
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(acceptSocket.getOutputStream()));
                    bw.write("server respond ~~");
                    bw.newLine();
                    bw.flush();

                    reader.close();
                    acceptSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }



        ss.close();
    }
}
