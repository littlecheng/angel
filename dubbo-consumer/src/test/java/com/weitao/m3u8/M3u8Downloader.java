package com.weitao.m3u8;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @title: M3u8Downloader
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/1917:58
 */
public class M3u8Downloader
{
    private static final String BAT_DIREATORY = "E:\\ts\\";// cmd 命令的路径

    private static final String PATTERN = "yyyy.MM.dd HH:mm:ss";

    public static void main(String[] args)
        throws IOException, InterruptedException
    {

        System.out.println("main " + new SimpleDateFormat(PATTERN).format(new Date()));
        Double totalTime = 0d;//记录影片时长
        String domain = "https://2.ddyunbo.com";//爬取网址
        String source = "C:\\Users\\Administrator\\Desktop\\ts\\" + args[0];//第一个参数是该目录下的文件名称(如xxx.m3u8)
        String target = BAT_DIREATORY + args[1];//第二个参数是目标文件目录,如xxx)，第二个参数应该和第一个参数保持同名(不带后缀.m3u8)

        File file = new File(source);
        System.out.println(source + " " + target);
        ArrayList<M3u8File> list = parseM3u8File(file, domain);
        int numberOfCores = Runtime.getRuntime().availableProcessors();
        int poolSize = (int)(numberOfCores / (1 - 0.9));
        ThreadPoolExecutor service = (ThreadPoolExecutor)Executors.newFixedThreadPool(poolSize);

        for (M3u8File fi : list)
        {
            totalTime += Double.valueOf(fi.getTsLengthOfTime());
            service.submit(new TsDownLoadThread(fi, args[1]));
        }
        service.shutdown();

        BigDecimal b = new BigDecimal(totalTime);
        System.out.println("影片时长:" + secondToTime(b.longValue()));
        System.out.println("pool shutdown:" + service.isShutdown());
        System.out.println("pool isTerminated:" + service.isTerminated());
        while (!service.isTerminated())
        {
            service.awaitTermination(1, TimeUnit.SECONDS);
        }

        File files = new File(target);
        File[] fileList = files.listFiles();

        System.out.println("文件片段数量:" + list.size());
        System.out.println("main end " + new SimpleDateFormat(PATTERN).format(new Date()));
        //防止网络原因导致线程没有全部下载完成，就空循环什么也不做，直到下载完成(可能需要重新执行main方法)
        while (fileList.length != list.size())
        {

        }
        System.out.println("all task complete");
        String path = BAT_DIREATORY + args[1] + ".bat";
        generateBAT(BAT_DIREATORY, args[1], list);
        if (!new File(BAT_DIREATORY + args[1] + ".ts").exists())
        {
            callCmd(path, false);
        }

    }

    //执行bat

    /**
     *
     * @param file  bat文件路径
     * @param isCloseWindow 执行完毕后是否关闭cmd窗口
     */
    private static void callCmd(String file, boolean isCloseWindow)
    {
        try
        {
            String cmdCommand;
            if (isCloseWindow)
            {
                cmdCommand = "cmd.exe /c start " + file;
            }
            else
            {
                cmdCommand = "cmd.exe /k start " + file;
            }
            Runtime.getRuntime().exec(cmdCommand);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    //生成bat文件

    /**
     *
     * @param target 文件目录地址
     * @param fileName 文件名
     * @param list 所需要的文件名称，追加时用的
     */

    private static void generateBAT(String target, String fileName, ArrayList<M3u8File> list)
    {
        try
        {
            File file = new File(target + fileName + ".bat");
            StringBuilder builder = new StringBuilder();
            builder.append("copy /b ");
            if (!file.exists())
            {
                file.createNewFile();
                for (M3u8File f : list)
                {
                    builder.append(target + fileName + "\\" + f.getFileName());
                    builder.append("+");
                }
                builder.deleteCharAt(builder.toString().length() - 1);
                builder.append(" " + target + fileName + ".ts");
                FileWriter writer = new FileWriter(file);
                writer.write(builder.toString());
                writer.close();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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
    public static ArrayList<M3u8File> parseM3u8File(File file, String domain)
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
