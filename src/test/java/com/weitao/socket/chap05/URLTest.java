package com.weitao.socket.chap05;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @title: URLTest
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/815:39
 */
public class URLTest
{
    public static void main(String[] args)
    {
        try
        {
            URL url = new URL("http://www.lolcats.com");
            InputStream inputStream =  url.openStream();
            int len = 0;
            while((len= inputStream.read())!=-1){
                System.out.println(len);
            }
            inputStream.close();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
