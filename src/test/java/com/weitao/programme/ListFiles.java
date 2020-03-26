package com.weitao.programme;

import java.io.File;

/**
 * @title: ListFiles
 * @projectName angel
 * @description: TODO
 * @author 程涛
 * @date 2020/3/269:51
 */
public class ListFiles
{
    private static Long total = 0L;

    public static void main(String[] args)
    {
        File file = new File("D://tmp");
        print(file);
        System.out.println("总数" + total);

    }

    public static void print(File file)
    {
        if (file != null)
        {

            if (file.exists())
            {
                if (file.isDirectory())
                {
                    File[] fileList = file.listFiles();
                    for (File f : fileList)
                    {
                        total++;
                        System.out.println(f.getName());
                        //递归调用
                        print(f);
                    }

                }
            }
        }
    }

}
