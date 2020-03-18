package com.weitao.nio.chat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @title: TCPClientThread
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/1515:21
 */
public class ClientThread implements Runnable
{
    private Selector selector;

    public ClientThread(Selector selector)
    {
        this.selector = selector;
    }

    @Override public void run()
    {
        try
        {
            while (true)
            {
                if (selector.select() > 0)
                {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    //遍历每个有可用IO操作Channel对应的SelectionKey
                    while (iterator.hasNext())
                    {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable())
                        {
                            // a connection was accepted by a ServerSocketChannel.
                            System.out.println("a connection was accepted by a ServerSocketChannel.");
                        }
                        else if (key.isConnectable())
                        {
                            // a connection was established with a remote server.
                            System.out.println("a connection was established with a remote server.");
                        }
                        else if (key.isReadable())
                        {
                            // a channel is ready for reading
                            //使用NIO读取Channel中的数据
                            SocketChannel sc = (SocketChannel)key.channel();//获取通道信息
                            ByteBuffer buffer = ByteBuffer.allocate(1024);//分配缓冲区大小
                            buffer.clear();
                            try
                            {
                                if (sc.read(buffer) != -1) //读取通道里面的数据
                                {
                                    buffer.flip();//调用此方法为一系列通道写入或获取操作做好准备
                                    // 将字节转化为为UTF-8的字符串
                                    String receivedString =
                                        Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
                                    // 控制台打印出来
                                    System.out.println(
                                        "接收到来自服务器" + sc.socket().getRemoteSocketAddress() + "的信息:" + receivedString);
                                    System.out.println("请客户端输入。。。");
                                }
                                else
                                {
                                    sc.close();
                                }

                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }

                        }
                        else if (key.isWritable())
                        {
                            // a channel is ready for writing
                            System.out.println("a channel is ready for writing");
                        }

                        //删除正在处理的SelectionKey
                        iterator.remove();
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}