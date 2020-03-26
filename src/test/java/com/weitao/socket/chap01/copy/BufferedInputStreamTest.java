package com.weitao.socket.chap01.copy;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BufferedInputStreamTest
{
    static final String PATTERN = "yyyy.MM.dd HH:mm:ss";

    public static void main(String[] args)
    {
        // testRead();
        //   testReadWithArr();
        testReadWith3param();
    }

    public static void testReadWith3param()
    {
        System.out.println(" begin " + new SimpleDateFormat(PATTERN).format(new Date()));
        String pathName = "e://ddd.ts";
        String path = "e://test copy7.ts";
        InputStream in = null;
        OutputStream out = null;
        try
        {
            in = new BufferedInputStream(new FileInputStream(pathName));
            out = new BufferedOutputStream(new FileOutputStream(path));
            byte[] arr = new byte[2048];
            int len = 0;
            while ((len = in.read(arr, 0, arr.length)) != -1)
            {
                out.write(arr, 0, len);
            }
            out.flush();
            in.close();

        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println(" end " + new SimpleDateFormat(PATTERN).format(new Date()));
    }

    public static void testReadWithArr()
    {
        String pathName = "e://test.txt";
        String path = "e://test copy.txt";
        InputStream in = null;
        OutputStream out = null;
        try
        {
            in = new BufferedInputStream(new FileInputStream(pathName));
            out = new BufferedOutputStream(new FileOutputStream(path));
            byte[] arr = new byte[1024];
            int len = 0;
            while ((len = in.read(arr)) != -1)
            {
                out.write(arr, 0, len);
            }
            out.flush();
            in.close();

        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public static void testRead()
    {
        String pathName = "e://test.txt";
        String path = "e://test copy.txt";
        InputStream in = null;
        OutputStream out = null;
        try
        {
            in = new BufferedInputStream(new FileInputStream(pathName));
            out = new BufferedOutputStream(new FileOutputStream(path));
            int len = 0;
            while ((len = in.read()) != -1)
            {
                out.write(len);
            }
            out.flush();
            in.close();

        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
