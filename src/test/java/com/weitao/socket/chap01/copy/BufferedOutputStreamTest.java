package com.weitao.socket.chap01.copy;

import java.io.*;

public class BufferedOutputStreamTest {
    public static void main(String[] args) {
        String pathName = "e://test.txt";
        byte[] letters = new byte[18195];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = (byte) (65);
        }

        OutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(pathName));
            out.write(letters);
        } catch (FileNotFoundException e) {
            System.out.println( e.getMessage());
        } catch (IOException e) {
            System.out.println( e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println( e.getMessage());
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
