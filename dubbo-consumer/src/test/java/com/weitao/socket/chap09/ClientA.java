package com.weitao.socket.chap09;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @title: Client1
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/110:41
 */
public class ClientA
{
    public static void main(String[] args)
    {
        try (Socket socket = new Socket("127.0.0.1", 100);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);)
        {
            System.out.println("等待中...请客户端A请说话,回车结束");
            while (true)
            {
                String read = scanner.nextLine();
                out.writeUTF("客户端A：" + read);
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
