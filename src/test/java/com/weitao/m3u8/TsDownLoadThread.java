package com.weitao.m3u8;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSessionContext;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * @title: TsDownLoadThread
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/2019:12
 */
public class TsDownLoadThread implements Runnable
{
    static
    {
        //  配置认证管理器
        javax.net.ssl.TrustManager[] trustAllCerts = {new TrustAllTrustManager()};
        SSLContext sc = null;
        try
        {
            sc = SSLContext.getInstance("SSL");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        SSLSessionContext sslsc = sc.getServerSessionContext();
        sslsc.setSessionTimeout(0);
        try
        {
            sc.init(null, trustAllCerts, null);
        }
        catch (KeyManagementException e)
        {
            e.printStackTrace();
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HostnameVerifier hv = (urlHostName, session) -> true;
        //  激活主机认证
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

    private  M3u8File m3u8File;
    private String directory;


    public TsDownLoadThread(M3u8File m3u8File,String directory)
    {
       this.m3u8File = m3u8File;
       this.directory = directory;
    }

    @Override public void run()
    {
        downLoadByUrl(m3u8File.getFilePath());
    }

    //下载文件
    private void downLoadByUrl(String tsUrl )
    {
        BufferedInputStream input = null;
        BufferedOutputStream out = null;
        File directory = new File("E://ts//"+ this.directory);
        if (!directory.exists())
         {
             directory.mkdirs();
         }


        try
        {
            File file = new File("E://ts//" +this.directory+"//"+ m3u8File.getFileName());
            if (!file.exists())
            {
                Thread.sleep(1000);
                URL url = new URL(m3u8File.getFilePath());
                HttpURLConnection  con = (HttpURLConnection)url.openConnection();
                con.setConnectTimeout(800000);//60秒
                con.setReadTimeout(800000);//60秒
                con.setRequestMethod("GET");
                input = new BufferedInputStream(con.getInputStream());
                int contentLength = con.getContentLength();
                System.out.println(tsUrl + "\t byte=" + contentLength + "\t " + m3u8File.getTsLengthOfTime());
                int length = 0;
                byte[] datas = new byte[contentLength];//根据内容长度动态设置
                while (length < contentLength)
                {
                    int bytesRead = input.read(datas, length, contentLength - length);
                    if (bytesRead == -1)
                    {
                        break;
                    }
                    length += bytesRead;

                }
                if (length != contentLength)
                {
                    throw new IOException(
                        m3u8File.getFileName() + " only read " + length + " bytes;Expected " + contentLength
                            + " bytes");
                }
                out = new BufferedOutputStream(new FileOutputStream(file));
                out.write(datas);
                input.close();
                out.close();
                con.disconnect();
            }

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
        catch (SocketTimeoutException e)
        {
            e.printStackTrace();
        }
       catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }
}
