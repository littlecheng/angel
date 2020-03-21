package com.weitao.m3u8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @title: M3u8Downloader
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/1917:58
 */
public class M3u8Downloader
{
    //传2个参数，第一个参数是源文件目录(文件名称)，第二个参数是目标文件目录(起个文件目录就行)
    public static void main(String[] args)
        throws IOException
    {
        String dest = "C:\\Users\\Administrator\\Desktop\\ts\\";
        File file = new File(dest+ args[0]);
        System.out.println(args[0]+" "+args[1]);
        String domain = "https://2.ddyunbo.com";
        ArrayList<M3u8File> list = parseM3u8File(file, domain,args[1]);
        Double totalTime = 0d;
        ThreadPoolExecutor service = (ThreadPoolExecutor)Executors.newFixedThreadPool(100);

       //  service.submit(new TsDownLoadThread(list.get(0),args[1]));
       for (M3u8File fi : list)
        {
            //线程数
            int activeCount = service.getActiveCount();
            long taskCount = service.getTaskCount();
            BlockingQueue queue = service.getQueue();
            System.out.println(
                "A new task has been added " + fi.getFileName() + ", activeCount=" + activeCount + ", taskCount="
                    + taskCount + ", queue=" + queue.size());
            totalTime += Double.valueOf(fi.getTsLengthOfTime());
            service.submit(new TsDownLoadThread(fi,args[1]));

        }
        service.shutdown();
        BigDecimal b = new BigDecimal(totalTime);
        System.out.println("影片时长:" + secondToTime(b.longValue()));

    }

    /**
     * 返回日时分秒
     * @param second
     * @return
     */
    private static String secondToTime(long second)
    {
        long days = second / 86400;//转换天数
        second = second % 86400;//剩余秒数
        long hours = second / 3600;//转换小时数
        second = second % 3600;//剩余秒数
        long minutes = second / 60;//转换分钟
        second = second % 60;//剩余秒数
        if (0 < days)
        {
            return days + "天，" + hours + "小时，" + minutes + "分，" + second + "秒";
        }
        else
        {
            return (hours > 10 ? hours : ("0" + hours)) + ":" + (minutes > 10 ? minutes : ("0" + minutes)) + ":" + (
                second > 10 ?
                    second :
                    ("0" + second));
            // return hours+"小时，"+minutes+"分，"+second+"秒";
        }
    }

    //根据文件解析ts片段
    public static ArrayList<M3u8File> parseM3u8File(File file, String domain,String parentDirectory)
        throws IOException
    {
        ArrayList<M3u8File> list = new ArrayList<>();//记录ts地址
        BufferedReader buf = new BufferedReader(new FileReader(file));
        String line;
        int twoLine = 0;
        M3u8File m3u8File = new M3u8File();
        while ((line = buf.readLine()) != null)
        {
            if (twoLine == 2)
            {
                twoLine = 0;
                m3u8File = new M3u8File();
            }
            if (line.contains("EXTINF"))
            {//处理时长
                String[] content = line.split("\\:");
                String tsTime = content[1].substring(0, content[1].length() - 1);
                m3u8File.setTsLengthOfTime(tsTime);
                twoLine++;
            }
            if (line.endsWith(".ts"))
            {
                m3u8File.setFilePath(domain + line);
                String fileName = line.substring(line.lastIndexOf("/") + 1, line.length());
                m3u8File.setFileName(fileName);
                twoLine++;
                list.add(m3u8File);
            }
        }
        return list;
    }

}
