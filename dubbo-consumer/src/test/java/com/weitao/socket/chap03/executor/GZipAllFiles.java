package com.weitao.socket.chap03.executor;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GZipAllFiles {

    public static final  int THREAD_COUNT = 4;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
        System.out.println(args[0]);
        for(String fileName : args){
            File f = new File(fileName);
            if(f.exists()){
                if(f.isDirectory()) {
                    File[] files = f.listFiles();
                    for(int i = 0;i< files.length ;i++){
                            if(!files[i].isDirectory()){
                                Runnable task = new GZipRunable(files[i]);
                                pool.submit(task);
                            }
                    }
                }else{
                    Runnable task = new GZipRunable(f);
                    pool.submit(task);
                }
            }
        }
        pool.shutdown();
    }
}
