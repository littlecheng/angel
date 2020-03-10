package com.weitao.socket.chap08;

import org.apache.commons.codec.Charsets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @title: Socket01
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/1012:12
 */
public class DayTime
{
    public static void main(String[] args)
    {
        try(Socket socket = new Socket("time.nist.gov",13)){
                socket.setSoTimeout(15000);
            InputStream inputStream = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, "ASCII");
            int c ;
            StringBuilder sb = new StringBuilder();
            while((c = reader.read()) !=-1){
                sb.append((char)c);
            }
            System.out.println(sb);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
