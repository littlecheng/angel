package com.weitao.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @title: ChannelTest
 * @projectName angel
 * @description: Reads a sequence of bytes from this channel into the given buffer.
 * @author Administrator
 * @date 2020/3/1116:37
 */
public class ReadData
{
    public static void main(String[] args)
    {
        FileInputStream inputStream = null;
        FileChannel fileChannel = null;

        try
        {
            File file = new File("E://nio/channel.txt");
            if (!file.exists())
            {
                file.createNewFile();
            }
            inputStream = new FileInputStream(file);
            fileChannel = inputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(20);
            int byteRead = fileChannel.read(buffer);
            while (byteRead != -1)
            {

                System.out.println("Read " + byteRead);
                //切换模式为读模式，其实就是把postion位置设置为0，可以从0开始读取
                buffer.flip();
                //如果缓冲区还有数据
                while (buffer.hasRemaining())
                {
                    //输出一个字符
                    System.out.print((char)buffer.get());
                }

                //数据读完后清空缓冲区
                buffer.clear();
                //继续把通道内剩余数据写入缓冲区
                byteRead = fileChannel.read(buffer);
                System.out.println(" byteRead=" + byteRead);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fileChannel.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
