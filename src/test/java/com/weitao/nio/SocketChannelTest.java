package com.weitao.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @title: SocketChannelTest
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/1412:23
 */
public class SocketChannelTest
{
    public static void main(String[] args)
        throws IOException
    {
        SocketChannel socketChannel = SocketChannel.open();
        //   socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));

        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.put(newData.getBytes());
        buf.flip();
        while (socketChannel.finishConnect())
        {
            while (buf.hasRemaining())
            {
                socketChannel.write(buf);
            }
        }

        socketChannel.close();
    }
}
