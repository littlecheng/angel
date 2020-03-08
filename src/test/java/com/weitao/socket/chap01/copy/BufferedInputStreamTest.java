package com.weitao.socket.chap01.copy;

import java.io.*;

public class BufferedInputStreamTest {
    public static void main(String[] args) {
        String pathName = "e://test.txt";
        String path = "e://test copy.txt";
        InputStream in = null;
        OutputStream  out = null;
        try {
             in =   new BufferedInputStream(new FileInputStream(pathName));
             out = new BufferedOutputStream(new FileOutputStream(path));
            int len = 0;
            int i = 0;
            while ((len = in.read())!= -1) {
                out.write(len);
            }
            out.flush();


        } catch (FileNotFoundException e) {
            System.out.println( e.getMessage());
        } catch (IOException e) {
            System.out.println( e.getMessage());
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println( e.getMessage());
                }
            }
            if(out!=null){
                try{
                    out.close();
                } catch (IOException e) {
                    System.out.println( e.getMessage());
                }
            }
        }


    }
}
