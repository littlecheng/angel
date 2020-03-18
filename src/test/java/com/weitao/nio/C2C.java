package com.weitao.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @title: C2C
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/1415:36
 */
public class C2C
{
    public static void main(String[] args)
        throws IOException
    {
        RandomAccessFile fromFile = new RandomAccessFile("E://fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("E://toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        // toChannel.transferFrom(fromChannel, position, count);
        fromChannel.transferTo(position, count, toChannel);
    }
}
