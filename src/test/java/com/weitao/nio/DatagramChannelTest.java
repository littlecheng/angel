package com.weitao.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @title: DatagramChannelTest
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/1413:56
 */
public class DatagramChannelTest
{
    public static void main(String[] args)
        throws IOException
    {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();

        channel.receive(buf);

    }

    public static void sendData(DatagramChannel channel)
        throws IOException
    {
        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();

        int bytesSent = channel.send(buf, new InetSocketAddress("jenkov.com", 80));
    }
}
