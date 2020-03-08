package com.weitao.socket.chap01.copy;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class TEST {
    public static void main(String[] args) {
        try {
            FileOutputStream fos=new FileOutputStream("e://BOSDemo.txt");
            BufferedOutputStream bos=new BufferedOutputStream(fos);
            String content="我是缓冲输出流测试数据！";
            bos.write(content.getBytes(),0,content.getBytes().length);
            bos.flush();
          //  bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //原文链接：https://blog.csdn.net/lyb1832567496/article/details/52727862
    }
}
