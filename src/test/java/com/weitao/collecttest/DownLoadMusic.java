package com.weitao.collecttest;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class DownLoadMusic
{
    static String contextPath = "E://music//";

    static String prefixUrl = "https://music.163.com/song/media/outer/url?id=";

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url)
        throws Exception
    {
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection connection = realUrl.openConnection();
        // 设置通用的请求属性
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        // 建立实际的连接
        connection.connect();
        // 获取所有响应头字段
        Map<String, List<String>> map = connection.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : map.keySet())
        {
            if ("Location".equalsIgnoreCase(key))
            {
                return map.get(key).toArray()[0].toString();
            }

        }
        return "";
    }

    public static void main(String[] args)
        throws Exception
    {
        File music = new File("E://music");
        String suffix = ".mp3";

        if (!music.exists())
        {
            music.mkdir();
        }
        List<Map<Integer, MusicLnfo>> list = fillData();

        for (int i = 0; i < list.size(); i++)
        {
            Iterator<Map.Entry<Integer, MusicLnfo>> iterator = list.get(i).entrySet().iterator();
            while (iterator.hasNext())
            {
                MusicLnfo musicLnfo = iterator.next().getValue();
                String musicUrl = sendGet(musicLnfo.getUrl() + suffix);//得到音乐地址
                String fileName =
                    contextPath + musicLnfo.getTitle() + "-" + musicLnfo.getSinger().replaceAll("\\/", "&") + ".mp3";
                if (!"404".equalsIgnoreCase(musicUrl))
                {
                    downloadSource(musicUrl, fileName);//下载歌曲到本地,并返回绝对路径
                }

            }
        }

    }

    /**
     * 根据远程资源路径，下载资源到本地临时目录
     *
     * @param remoteSourceUrl 远程资源路径
     * @param fileName   文件路径
     */
    private static void downloadSource(String remoteSourceUrl, String fileName)
        throws Exception
    {
        //下载资源
        File file = new File(fileName);
        if (!file.exists())
        {
            file.createNewFile();
            URL url = new URL(remoteSourceUrl);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bytes = new byte[1024 * 10];
            int length = 0;
            while ((length = dataInputStream.read(bytes)) != -1)
            {
                fileOutputStream.write(bytes, 0, length);
            }
            dataInputStream.close();
            fileOutputStream.close();
        }
    }

    /**
     * 根据文件url获取文件名（包含后缀名）
     *
     * @param url 文件url
     * @return 文件名（包含后缀名）
     */
    private static String getOriginalFileName(String url)
    {
        String[] sarry = url.split("/");
        return sarry[sarry.length - 1];
    }

    private static List<Map<Integer, MusicLnfo>> fillData()
    {

        Map<Integer, MusicLnfo> map = new HashMap<>();
        List<Map<Integer, MusicLnfo>> list = new ArrayList<>();
        map.put(1, new MusicLnfo(1, prefixUrl + 194405, "笑看风云", "郑少秋"));

        list.add(map);
        return list;
    }

}
