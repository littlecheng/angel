package com.weitao.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @title: ChatServer
 * @projectName angel
 * @description: 服务端启动类
 * @author Administrator
 * @date 2020/3/1412:39
 */
public class ChatServer
{
    public static void main(String[] args)
    {
        ServerSocketChannel serverSocketChannel = null;
        try
        {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(8678));
            // 打开一个Selector
            Selector selector = Selector.open();
            // 注册到Selector中，ACCEPT操作
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            // handler处理
            ServerHandler handler = new ServerHandler();
            while (true)
            {
                // 当准备好的通道大于0才有往下的操作
                if (selector.select() > 0)//调用select()会导致阻塞
                {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext())
                    {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable())
                        {  // 接收状态
                            handler.handleAccept(key);
                        }
                        else if (key.isConnectable())
                        {
                            handler.handleConnectable(key);
                        }
                        else if (key.isReadable())
                        { // 可读状态
                            handler.handleRead(key);
                        }
                        else if (key.isWritable()) //可写状态
                        {
                            handler.handleWrite(key);
                        }
                        // 处理过的key要移除掉
                        iterator.remove();
                    }
                }

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
