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

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);
        int cn = 1, iRes;
        System.out.println("get data from server");
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[111];
        int leftlength = 111 * 50;
        int i = 1;
        while (leftlength > 0) {
            iRes = inputStream.read(bytes);
            if (iRes > 0) {
                System.out.println("\n NO." + i + "收到" + iRes + "bytes:");
                i++;
                String encode = Base64.getEncoder().encodeToString(bytes);
                byte[] decode = Base64.getDecoder().decode(encode);
                System.out.println(new String(decode));
                leftlength = leftlength - iRes;
                System.out.println(leftlength);
            } else if (iRes == -1) {
                System.out.println("连接关闭");
                break;
            }

        }
        System.out.println("发送数据");
        OutputStream outputStream = socket.getOutputStream();
        System.out.println("请输入发送数据");
        Scanner scan = new Scanner(System.in);
        String msg = scan.nextLine();
        outputStream.write(msg.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
        System.out.println("发送完毕");
        //关闭socket
        socket.close();
    }
}
