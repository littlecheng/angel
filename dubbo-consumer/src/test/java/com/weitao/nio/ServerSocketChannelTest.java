package com.weitao.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @title: ServerSocketChannelTest
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/1412:39
 */
public class ServerSocketChannelTest
{
    public static void main(String[] args)
    {
        ServerSocketChannel serverSocketChannel = null;
        try
        {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(9999));
            //  serverSocketChannel.configureBlocking(false);
            while (true)
            {
                try
                {
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    if (socketChannel != null)
                    {
                        //do something with socketChannel...
                        System.out.println("等待客户端接入。。。");

                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                System.out.println("do ...");

                //do something with socketChannel...
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                serverSocketChannel.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
}
