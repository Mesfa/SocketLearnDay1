package test.src;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/*
客户端socket
 */
public class Mycli {
    //建立连接
    public static void main(String[] args) {
    Socket socket;
    {
        try {
            socket = new Socket("localhost", 9999);
            //发送数据
            OutputStream os = socket.getOutputStream();
            //获取输入
            Scanner scan = new Scanner(System.in);
            String msg = scan.nextLine();
            //写数据
            os.write(msg.getBytes(StandardCharsets.UTF_8));
            //接受数据
            InputStream is = socket.getInputStream();
            //读数据
            byte[] bytes = new byte[1024];
            int n = is.read(bytes);
            System.out.println("服务器回："+new String(bytes,0,n));
            //关闭输出
            os.close();
            //关闭接受
            is.close();
            //关闭套接字
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}
