package com.weitao.socket.chap09;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @title: Thread
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/1023:49
 */
public class WorkThread implements Runnable
{
    private Socket socket;

    public WorkThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override public void run()
    {

        try (DataOutputStream out = new DataOutputStream(socket.getOutputStream());//响应客户端
            DataInputStream input = new DataInputStream(socket.getInputStream());//读取客户端
            Scanner scanner = new Scanner(System.in);)
        {

            while (true)
            {

                String read = input.readUTF();
                System.out.println(read);
                String send = scanner.nextLine();
                String threadName = Thread.currentThread().getName();
                out.writeUTF("线程[" + threadName + "]:" + send);
                out.flush();

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (socket != null)
            {
                try
                {
                    socket.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }
}
