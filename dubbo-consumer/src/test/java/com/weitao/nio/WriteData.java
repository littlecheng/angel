package com.weitao.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @title: WriteData
 * @projectName angel
 * @description: Writes a sequence of bytes to this channel from the given buffer.
 * @author Administrator
 * @date 2020/3/1122:41
 */
public class WriteData
{
    public static void main(String[] args)
        throws IOException
    {
        RandomAccessFile aFile = new RandomAccessFile("E://nio/writeData.txt", "rw");
        FileChannel fileChannel = aFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(100);//分配的容量一定要大于写入的容量
        String newData = "New String to write to file..." + System.currentTimeMillis();
        System.out.println("限制是：" + buffer.limit() + ",容量是：" + buffer.capacity() + " ,位置是：" + buffer.position());
        buffer.clear();

        buffer.put(newData.getBytes());

        //  System.out.println("限制是：" + buffer.limit() + ",容量是：" + buffer.capacity() + " ,位置是：" + buffer.position());
        buffer.flip();//在写入前一定要flip下，position置为 0 ，不然写不进去
        // System.out.println("限制是：" + buffer.limit() + ",容量是：" + buffer.capacity() + " ,位置是：" + buffer.position());
        //System.out.println(buffer.hasRemaining());
        while (buffer.hasRemaining())
        {
            fileChannel.write(buffer);
        }
        //  System.out.println("限制是：" + buffer.limit() + ",容量是：" + buffer.capacity() + " ,位置是：" + buffer.position());
        buffer.clear();
        fileChannel.close();

    }
}
