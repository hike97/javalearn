package mianshi.newwebcode.base.c01_bio;

import java.io.*;
import java.net.Socket;

/**
 * @ClassName Client
 * @Description 客户端
 * @Author hike97
 * @Date 2023/3/30 22:22
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) throws IOException {

        Socket s = new Socket("localhost", 8888);

        //客户端发消息
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        bw.write("Hello Netty Server !!!");
        bw.newLine();
        bw.flush();

        //客户端接收服务端消息
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str = bufferedReader.readLine();
        System.out.println(str);
        bufferedReader.close();
    }
}
