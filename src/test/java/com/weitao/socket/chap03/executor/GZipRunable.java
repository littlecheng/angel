package com.weitao.socket.chap03.executor;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class GZipRunable implements Runnable {

    private final File input ;
    public GZipRunable(File file){
        this.input = file;
    }


    @Override
    public void run() {
        if(!input.getName().endsWith(".gz")) {
            File output = new File(input.getParent(), input.getName() + ".gz");
            if (!output.exists()) {

                try (
                        InputStream in = new BufferedInputStream(new FileInputStream(input));
                        OutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(output)));
                ) {
                    int b;
                    while ((b = in.read()) != -1) {
                        out.write(b);
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
