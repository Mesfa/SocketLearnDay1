package test.src;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
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
            int i,cn = 1,iRes;
            InputStream is = socket.getInputStream();
            //读数据
            byte[] recvBuf = new byte[1024];
            do {
                //接收服务器发的信息
                iRes = is.read(recvBuf);
                if (iRes > 0) {
                    System.out.println("\nRecv" + iRes + "bytes");
                    String encoded = Base64.getEncoder().encodeToString(recvBuf);//base64encode
                    byte[] decode = Base64.getDecoder().decode(encoded);
                    System.out.println(new String(decode));
                } else {
                    if (iRes == -1) {
                        System.out.println("关闭连接");
                        break;
                    }
                }
            }
                while (iRes > 0);
                    OutputStream os1 = socket.getOutputStream();
                    os1.write(msg.getBytes());
                    os1.flush();
                    os1.close();
                    is.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}
