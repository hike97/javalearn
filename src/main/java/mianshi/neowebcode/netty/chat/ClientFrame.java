package mianshi.neowebcode.netty.chat;

import lombok.Data;
import mianshi.neowebcode.netty.NettyClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @ClassName ClientFrame
 * @Description TODO
 * @Author hike97
 * @Date 2023/4/1 15:44
 * @Version 1.0
 **/
@Data
public class ClientFrame extends Frame {

    private TextArea ta = new TextArea();

    private TextField tf = new TextField();

    private ChatNettyClient client = null;

    public static final ClientFrame INSTANCE = new ClientFrame();

    private ClientFrame(){
        this.setSize(300,700);
        this.setLocation(400,20);
        this.add(ta,BorderLayout.CENTER);
        this.add(tf,BorderLayout.SOUTH);
        this.setTitle("聊天窗口");
        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //send to server
                client.send(tf.getText());
                //ta.setText(ta.getText()+tf.getText()+"\r\n");
                tf.setText("");
            }
        });
        /*
          * 添加图形关闭事件
         */
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                 client.closeConnection();
                 System.exit(0);
            }
        });
    }

    public void connectToServer(){
        client =  new ChatNettyClient();
        client.connect();
    }

    public static void main(String[] args) {
        ClientFrame frame =ClientFrame.INSTANCE;
        frame.setVisible(true);
        frame.connectToServer();
    }

    public void updateText(String str) {
        ta.setText(ta.getText() + str +"\r\n");
    }
}
