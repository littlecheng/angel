package com.weitao.socket.chap05;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @title: URLConnectionTest
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/816:38
 */
public class DownLoadWebPage
{
    public static void main(String[] args)
        throws IOException
    {

        InputStream in = null;
        FileOutputStream fileOutputStream = null;
        try
        {
            URL url = new URL(
                "https://c3.ludaogps.com/vod/sx/202003/dvdms-476/00001.ts?key=4b63e3f790243928b851c925e95abeff&e=1584435202");
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            System.out.println(connection.getURL());

            //头部信息
            Map<String, List<String>> maps = connection.getHeaderFields();
            Iterator<Map.Entry<String, List<String>>> iter = maps.entrySet().iterator();
            while (iter.hasNext())
            {
                Map.Entry<String, List<String>> map = iter.next();
                System.out.println("key=" + map.getKey() + " value =" + map.getValue());
            }

            in = connection.getInputStream();
            int length = 0;
            File file = new File("e://getHtml.html");
            fileOutputStream = new FileOutputStream(file);
            while ((length = in.read()) != -1)
            {
                fileOutputStream.write(length);
            }
            fileOutputStream.flush();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            fileOutputStream.close();
            in.close();
        }

    }
}
