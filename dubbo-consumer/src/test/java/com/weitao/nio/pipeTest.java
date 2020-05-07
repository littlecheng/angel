package com.weitao.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @title: pipeTest
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/1511:04
 */
public class pipeTest
{
    public static void main(String[] args)
        throws IOException
    {
        Pipe pipe = Pipe.open();
        System.out.println("写数据:");
        write(pipe);
        System.out.println("读数据:");
        read(pipe);

        // sinkChannelTOSourceChannel(pipe);

    }

    /**
     * 前提条件：是有个FileChannel才能使用channel to channel
     *  这里借助FileChannel 实现数据传输到sinkChannel
     */

    public static void sinkChannelTOSourceChannel(Pipe pipe)
    {
        Pipe.SinkChannel sinkChannel = pipe.sink();
        RandomAccessFile aFile = null;
        try
        {
            aFile = new RandomAccessFile("E://nio/pipe.txt", "rw");
            FileChannel fromChannel = aFile.getChannel();
            long position = 0;
            long count = 0;
            count = fromChannel.size();
            fromChannel.transferTo(position, count, sinkChannel);
            //sinkChannel读到数据
            read(pipe);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("error" + e);
        }

    }

    //读数据
    public static void read(Pipe pipe)
        throws IOException
    {
        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer buf = ByteBuffer.allocate(45);
        while (sourceChannel.read(buf) != -1)
        {
            //切换模式为读模式，其实就是把postion位置设置为0，可以从0开始读取
            buf.flip();
            System.out.print(getString(buf));
            //数据读完后清空缓冲区
            buf.clear();
        }
        sourceChannel.close();
    }

    //写数据
    public static void write(Pipe pipe)
        throws IOException
    {
        Pipe.SinkChannel sinkChannel = pipe.sink();
        String newData = "New String to write to file...你好" + System.currentTimeMillis();
        System.out.println(newData);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.clear();
        buf.put(newData.getBytes());

        buf.flip();

        while (buf.hasRemaining())
        {
            sinkChannel.write(buf);
        }
        buf.clear();
        sinkChannel.close();
    }

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
}
