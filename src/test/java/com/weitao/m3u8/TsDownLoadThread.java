package com.weitao.m3u8;

import com.google.common.base.Charsets;

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

    private String filePath;

    private String fileName;

    private String tsLengthOfTime;

    public TsDownLoadThread(String filePath, String fileName, String tsLengthOfTime)
    {
        this.filePath = filePath;
        this.fileName = fileName;
        this.tsLengthOfTime = tsLengthOfTime;
    }

    @Override public void run()
    {
        downLoadByUrl(filePath);
    }

    //下载文件
    private void downLoadByUrl(String tsUrl)
    {
        InputStream in = null;
        FileOutputStream out;
        File directory = new File("E://ts");
        if (!directory.exists())
        {
            directory.mkdir();
        }


        HttpURLConnection con = null;
        try
        {
            File file = new File("E://ts//" + fileName);
            if (!file.exists())
            {
                URL url = new URL(filePath);
                con = (HttpURLConnection)url.openConnection();
                con.setConnectTimeout(600000);//毫秒
                con.setReadTimeout(600000);//毫秒
                con.setRequestMethod("GET");
                System.out.println(con.getResponseCode());

                in = con.getInputStream();
                int contentLength = con.getContentLength();
                System.out.println(tsUrl + "\t byte=" + contentLength + "\t " + tsLengthOfTime);
                int length = 0;
                byte[] datas = new byte[contentLength];//根据内容长度动态设置
                while (length < contentLength)
                {
                    int bytesRead = in.read(datas, length, contentLength - length);
                    if (bytesRead == -1)
                    {
                        break;
                    }
                    length += bytesRead;

                }

                if (length != contentLength)
                {
                    throw new IOException("only read " + length + " bytes;Expected " + contentLength + " bytes");
                }

                out = new FileOutputStream(file);
                out.write(datas);
                out.flush();
                out.close();
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

        }
        catch (SocketTimeoutException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (con != null)
            {
                con.disconnect();
            }

        }

    }
}
