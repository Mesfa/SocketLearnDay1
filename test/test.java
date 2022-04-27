package test;

import static java.net.Inet4Address.*;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class test {

    public static void main(String[] args) {
        try {
            InetAddress address = getLocalHost();
            System.out.println("主机名:"+address.getHostName());
            System.out.println("ip地址:"+address.getHostAddress());
            InetSocketAddress socketAddress = new InetSocketAddress("10.1.2.190",999);
            System.out.println("套接字地址的IP："+socketAddress.getAddress());
            System.out.println("套接字的端口："+socketAddress.getPort());
            InetAddress addr1 = socketAddress.getAddress();
            System.out.println("套接字方法生成的IP对象的IP："+addr1.getHostAddress());
            System.out.println("套接字方法生成的IP对象的主机名"+addr1.getHostName());
            /*
            这里发现其实InetSocketAddress是从Host文件读取的
             */
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
