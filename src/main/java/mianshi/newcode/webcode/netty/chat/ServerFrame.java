package mianshi.newwebcode.netty.chat;

import lombok.Data;

import java.awt.*;
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
public class ServerFrame extends Frame {
    private TextArea taServer = new TextArea();

    private TextArea taClient = new TextArea();

    public static final ServerFrame INSTANCE = new ServerFrame();

    private ChatNettyServer chatNettyServer;

    private ServerFrame() {
        this.setSize(800, 600);
        this.setLocation(300, 30);
        Panel panel = new Panel(new GridLayout(1, 2));
        panel.add(taServer);
        panel.add(taClient);
        this.add(panel);

        //字体设置
        taServer.setFont(new Font("Monospace", Font.PLAIN, 25));
        taClient.setFont(new Font("Monospace", Font.PLAIN, 25));
        this.updateServerMsg("server:");
        this.updateClientMsg("client:");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * 客户端数据更新
     *
     * @param str
     */
    void updateClientMsg(String str) {
        this.taClient.setText(taClient.getText() + str + System.getProperty("line.separator"));
    }

    /**
     * 服务器数据更新
     *
     * @param str
     */
    void updateServerMsg(String str) {
        this.taServer.setText(taServer.getText() + str + System.getProperty("line.separator"));
    }

    public static void main(String[] args) {
        ServerFrame.INSTANCE.setVisible(true);
        ServerFrame.INSTANCE.chatNettyServer.serverStart();
    }
}
