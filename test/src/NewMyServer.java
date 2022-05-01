package test.src;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class NewMyServer {

    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
//        System.out.println(address);
        //服务点的ServerSocket
        ServerSocket server = new ServerSocket(9999,50,address);
        System.out.println("服务端IP"+ address);
//        服务端IP/10.1.2.190
        System.out.println("服务端IP"+"   "+server.getInetAddress().getHostAddress());
//        服务端IP  10.1.2.190
        //服务端监听,使用循环
        while (true){
            //等待客户端连接请求
            System.out.println("等待客户端连接请求-------");
            //服务器accep的为客户端，此时作为新的socket对象
            Socket socket = server.accept();
            //打印客户端socket的属性
            System.out.println("客户端IP为:"+socket.getInetAddress().getHostAddress());
            //接收客户端的信息
            InputStream is = socket.getInputStream();
            //服务端发送信息出去
            OutputStream os = socket.getOutputStream();
            for (int i = 0;i<10;i++){
                String format = String.format("NO.%d 欢迎登陆服务器，请问1+1等于几？（客户机IP :%s\n）",i+1,socket.getInetAddress().getHostAddress());
                os.write(format.getBytes(StandardCharsets.UTF_8));
                os.flush();
            }
            os.write("hello".getBytes(StandardCharsets.UTF_8));
            byte[] bytes = new byte[1024];
            int n = is.read(bytes);
            System.out.println("客户端消息"+new String(bytes,0,n));
            server.close();
            System.out.println("是否继续");
            Scanner scan = new Scanner(System.in);
            String msg = scan.nextLine();
            if (msg.equals("y"))
                break;

        }
//        server.close();

    }

}
