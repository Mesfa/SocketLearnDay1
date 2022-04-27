package test.src;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Myserver {

    public static void main(String[] args) {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            //使用ServerSocket建立服务器套接字
            try (ServerSocket serverSocket = new ServerSocket(9999, 50, addr)) {
                //使用accept方法阻塞式等待建立请求,该方法返回的是客户机的socke对象
                Socket sc = serverSocket.accept();
                //打印客户机的名字
                System.out.println("客户机主机名为:"+(sc.getInetAddress()).getHostName());
                //接收客户机消息,使用的是匿名子类实例话
                InputStream is = sc.getInputStream();
                //打印客户机消息
                byte[] b = new byte[1024];
                int n = is.read();
                System.out.println("客户机消息:"+new String(b,0,n));
                //返回消息给客户端
                OutputStream os = sc.getOutputStream();
                System.out.println("请输入返回消息");
                Scanner scan = new Scanner(System.in);
                String msg = scan.nextLine();
                os.write(msg.getBytes());
                //关闭连接
                serverSocket.close();
                sc.close();
                scan.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

}
