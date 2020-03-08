package com.weitao.socket.chap01;

import java.io.*;
public class OutputStreamTest {
    public static void main(String[] args) {
        String pathName = "e://test.txt";
        System.out.println(Integer.MIN_VALUE);
        byte[] letters = new byte[8190];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = (byte) (66);
        }

        OutputStream out = null;
        try {
            out = new FileOutputStream(pathName);
            out.write(letters);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


      /*  try (OutputStream out1 = new FileOutputStream(pathName)) {
            out1.write(letters);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }*/


    }
}
