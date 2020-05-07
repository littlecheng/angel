package com.weitao.socket.chap09;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @title: Client2
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/111:04
 */
public class ClientB
{
    public static void main(String[] args)
    {
        try (Socket socket = new Socket("127.0.0.2", 100);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);)
        {
            System.out.println("等待中...请客户端B请说话，回车结束");

            while (true)
            {

                String read = scanner.nextLine();
                out.writeUTF("客户端B：" + read);
                out.flush();
                String accept = input.readUTF();
                System.out.println(accept);

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
