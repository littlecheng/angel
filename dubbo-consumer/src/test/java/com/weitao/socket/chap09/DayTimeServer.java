package com.weitao.socket.chap09;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @title: DayTimeServer
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/1017:31
 */
public class DayTimeServer
{
    public static  final  int PORT = 13;
    public static  final  String DATE = "yyyy-MM-dd HH:mm:ss";
    public static void main(String[] args)
    {
        try(ServerSocket socket = new ServerSocket(PORT))
        {
            while (true)
            {
                System.out.println("接受命令...");
                try (Socket connection = socket.accept())
                {
                    Writer out = new OutputStreamWriter(connection.getOutputStream());
                    Date now = new Date();
                    String date = new SimpleDateFormat(DATE).format(now);
                    out.write("it's :"+date+"\r\n");
                    out.flush();
                    connection.close();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
