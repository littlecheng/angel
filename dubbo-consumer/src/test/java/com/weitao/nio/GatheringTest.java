package com.weitao.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @title: GatheringTest
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/1415:26
 */
public class GatheringTest
{
    public static void main(String[] args)
        throws IOException
    {
        SocketChannel channel = SocketChannel.open();
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

        //write data into buffers

        ByteBuffer[] bufferArray = {header, body};

        channel.write(bufferArray);
    }
}
