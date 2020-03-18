package com.weitao.nio.chat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Scanner;

/**
 * @title: ServerHandler
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/1510:32
 */
public class ServerHandler
{

    /**
     * ByteBuffer 转换 String
     * @param buffer
     * @return
     */
    public static String getString(ByteBuffer buffer)
    {
        Charset charset = null;
        CharsetDecoder decoder = null;
        CharBuffer charBuffer = null;
        try
        {
            charset = Charset.forName("UTF-8");
            decoder = charset.newDecoder();
            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
            return charBuffer.toString();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return "";
        }
    }

    public void handleRead(SelectionKey key)
        throws IOException
    {

        //获得与客户端通信的信道
        SocketChannel socketChannel = (SocketChannel)key.channel();
        ByteBuffer byteBuffer = (ByteBuffer)key.attachment();
        //  System.out.println("ddd"+byteBuffer.capacity());
        byteBuffer.clear();
        if (socketChannel != null)
        {
            long byteRead = socketChannel.read(byteBuffer);
            if (byteRead == -1)
            {
                socketChannel.close();
            }
            else
            {
                byteBuffer.flip();
                String receiveMsg = new String(byteBuffer.array(), 0, byteBuffer.limit());
                System.out.println("接收来自" + socketChannel.socket().getRemoteSocketAddress() + "的消息：" + receiveMsg);

                // 返回回应给客户端
                handleWrite(key);
            }
        }

    }

    public void handleWrite(SelectionKey key)
        throws IOException
    {
        System.out.println("请服务器端输入数据...");
        //获得与客户端通信的信道
        SocketChannel socketChannel = (SocketChannel)key.channel();
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();

        //响应客户端
        ByteBuffer buffer = ByteBuffer.allocate(1024);//分配的容量一定要大于写入的容量
        buffer.clear();
        buffer.put(string.getBytes());
        buffer.flip();//在写入前一定要flip下，position置为 0 ，不然写不进去
        while (buffer.hasRemaining())
        {
            socketChannel.write(buffer);
        }
        buffer.clear();
    }

    public void handleConnectable(SelectionKey key)
    {
        System.out.println(" a connection was established with a remote server.");
    }

    public void handleAccept(SelectionKey key)
        throws IOException
    {
        System.out.println("accept。。。");
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
        // 获取客户端链接，并注册到Selector中
        SocketChannel clientChannel = serverSocketChannel.accept();
        clientChannel.configureBlocking(false);
        // 讲通道注册到Selector里头，然后设置为读操作，第三个参数是将你需要带的东西，可通过key获取
        clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(78));

    }
}
