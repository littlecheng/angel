package com.weitao.nio;

import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @title: SelectorTest
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/1415:51
 */
public class SelectorTest
{
    public static void main(String[] args)
        throws IOException
    {
        SocketChannel channel = SocketChannel.open();
        try (Selector selector = Selector.open())
        {
            channel.configureBlocking(false);
            SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_CONNECT);
            int interestSet = selectionKey.interestOps();
            System.out.println(interestSet);
            int readyOps = selectionKey.readyOps();
            System.out.println(readyOps);
            System.out.println(selectionKey.isAcceptable());
            System.out.println(selectionKey.isConnectable());
            System.out.println(selectionKey.isReadable());
            System.out.println(selectionKey.isWritable());

            Channel channel2 = selectionKey.channel();
            System.out.println(channel2);
            Selector selector2 = selectionKey.selector();
            System.out.println(selector2);

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (true)
            {

                int readyChannels = selector.selectNow();

                if (readyChannels == 0)
                    continue;

                while (keyIterator.hasNext())
                {

                    SelectionKey key = keyIterator.next();

                    if (key.isAcceptable())
                    {
                        // a connection was accepted by a ServerSocketChannel.
                        System.out.println(" a connection was accepted by a ServerSocketChannel.");
                    }
                    else if (key.isConnectable())
                    {
                        // a connection was established with a remote server.
                        System.out.println(" a connection was established with a remote server.");
                    }
                    else if (key.isReadable())
                    {
                        // a channel is ready for reading
                        System.out.println(" a channel is ready for reading.");
                    }
                    else if (key.isWritable())
                    {
                        // a channel is ready for writing
                        System.out.println("  a channel is ready for writing.");
                    }

                    keyIterator.remove();
                }

            }
        }
    }
}
