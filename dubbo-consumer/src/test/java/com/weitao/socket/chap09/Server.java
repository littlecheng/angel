package com.weitao.socket.chap09;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @title: Server
 * @projectName angel
 * @description: 多个客户端和一个服务器端(多线程, 不阻塞每个客户端)通信。
 * @author Administrator
 * @date 2020/3/1023:46
 */
public class Server
{
    public static void main(String[] args)
    {
        ExecutorService service = Executors.newFixedThreadPool(10);
        try (ServerSocket serverSocket = new ServerSocket(100);)
        {
            System.out.println("等待客户端提问...");
            while (true)
            {
                Socket socket = serverSocket.accept();
                service.submit(new WorkThread(socket));
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
