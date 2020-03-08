package com.weitao.socket.chap01;

import java.io.*;

public class InputStreamTest {
    public static void main(String[] args) {
        String pathName = "e://test.txt";
        String path = "e://test copy.txt";
        InputStream in = null;
        OutputStream  out = null;
        try {
             in =  new FileInputStream(pathName);
             out = new FileOutputStream(path);
            int len = 0;
            int i = 0;
            while ((len = in.read())!= -1) {
                System.out.println(len +"\t"+ (++i) );
                out.write(len);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try{
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
