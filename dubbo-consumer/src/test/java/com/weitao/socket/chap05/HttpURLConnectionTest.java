package com.weitao.socket.chap05;



import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @title: HttpURLConnectionTest
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/818:35
 */
public class HttpURLConnectionTest

{
    public static void main(String[] args)
        throws MalformedURLException, IOException
    {
        URL url = new URL("https://baijiahao.baidu.com/s?id=1660565967502891745");

        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        InputStream in = con.getInputStream();
        int contentLength = con.getContentLength();
        String contentType = con.getContentType();
        String contentEncoding  = con.getContentEncoding();//内容编码
        String charset = contentType.substring(contentType.lastIndexOf("=")+1,contentType.length());
        System.out.println(contentLength +" "+ contentType +" "+ contentEncoding+" " + charset);

        int length = 0;
        File file = new File("e://bufferedWriter.html");
        BufferedOutputStream buf = new BufferedOutputStream(new FileOutputStream(file));
        //DataOutputStream buf = new DataOutputStream(new FileOutputStream(file));
       // BufferedWriter buf  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charsets.ISO_8859_1));
       /* while ((length = in.read()) != -1)
            buf.write(length);
            buf.close();
         */



        byte[] datas = new byte[contentLength];
        while(length< contentLength){
            int bytesRead   =  in.read(datas,length,contentLength - length);
            if(bytesRead == -1 ){
                break;
            }
            length+= bytesRead;

        }
        String strRead = new String(datas, "UTF-8");
        System.out.println(strRead);
        if(length != contentLength){
            throw new IOException("only read "+ length  +" bytes;Expected "+ contentLength +" bytes");
        }

        FileOutputStream out = new FileOutputStream(file);
        out.write(datas);
        out.flush();
        out.close();
    }
}
