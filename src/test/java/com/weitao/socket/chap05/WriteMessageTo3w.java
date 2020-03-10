package com.weitao.socket.chap05;

import org.apache.commons.codec.Charsets;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @title: WriteMessageTo3w
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/922:14
 */
public class WriteMessageTo3w
{
    public static void main(String[] args)
    {
        OutputStream raw = null;
        try
        {
            URL url = new URL("http://www.baidu.com/s");
            URLConnection connection = url.openConnection();
            System.out.println(connection + " ");
            connection.setDoOutput(true);
            raw = connection.getOutputStream();
            OutputStream buffered = new BufferedOutputStream(raw);
            OutputStreamWriter out = new OutputStreamWriter(buffered, Charsets.UTF_8);
            out.write("美女");
            out.flush();
            out.close();


            InputStream in = connection.getInputStream();
            int length = 0;
            File file = new File("e://writeMessageTo3w.html");
            BufferedOutputStream buf = new BufferedOutputStream(new FileOutputStream(file));
            while ((length = in.read()) != -1)
                buf.write(length);
            buf.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
