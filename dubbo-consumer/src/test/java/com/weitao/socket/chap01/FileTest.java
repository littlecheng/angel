package com.weitao.socket.chap01;

import java.io.File;
import java.io.IOException;

public class FileTest {
    public static void main(String[] args) {
        String pathName = "e:\\hello.java";
        File f1 = new File(pathName);//将Test22文件封装成File对象。注意；有可以封装不存在文件或者文件夹，变成对象。
       if(f1.exists()){

       }else{
           try {
               f1.createNewFile();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
        System.out.println(f1);

        File f2 = new File("e:\\java_code\\day22e","hello1.java");
        System.out.println(f2);

        //将parent封装成file对象。
        File dir = new File("e:\\java_code\\day22e");
        File f3 = new File(dir,"hello3.java");
        System.out.println(f3);

    }
}
