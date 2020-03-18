package com.weitao.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @title: Client
 * @projectName angel
 * @description: 客户端启动类
 * @author Administrator
 * @date 2020/3/1515:13
 */
public class Client
{
    //要连接的服务器的IP地址
    private String hostIp;

    //要连接的远程服务器在监听的端口
    private int hostListenningPort;

    //与服务器通信的信道
    SocketChannel socketChannel;

    public Client(String hostIp, int port, SocketChannel socketChannel)
    {
        this.hostIp = hostIp;
        this.hostListenningPort = port;
        this.socketChannel = socketChannel;
    }

    /**
     * 发送字符串到服务器
     */
    public void sendMsg(String message)
        throws IOException
    {
        ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes("UTF-8"));
        socketChannel.write(writeBuffer);
    }

    static boolean flag = true;

    public static void main(String[] args)
        throws IOException
    {

        //与服务器通信的信道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8678));
        //打开监听信道并设置为非阻塞模式
        socketChannel.configureBlocking(false);
        //打开并注册选择器到信道
        //信道选择器
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);//SelectionKey.OP_READ表示读就绪事件
        //启动一个线程去处理客户端的消息接收
        Thread thread = new Thread(new ClientThread(selector));
        //线程启动
        thread.start();
        System.out.println("请客户端输入。。。");
        //静态内部类方法
        new Thread(() -> {
            try
            {
                while (flag)
                {
                    Scanner scanner = new Scanner(System.in);
                    String string = scanner.next();

                    //创建一个客户端并绑定到IP地址和端口号
                    Client client = new Client("localhost", 8678, socketChannel);
                    client.sendMsg(string);
                }
            }
            catch (IOException e)
            {
                flag = false;
                e.printStackTrace();
            }
            finally
            {
                flag = false;
                //最后关闭资源
                try
                {
                    socketChannel.close();
                    selector.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }).start();
    }
}
