package com.weitao.socket.chap03.music;

import java.io.*;

public class MusicCopy
{
    public static void main(String[] args)
        throws IOException
    {

        try
        {
            File dest = new File("E://log/html/Kenny G - Going home copy.mp3");
            BufferedInputStream bis =
                new BufferedInputStream(new FileInputStream(new File("E://log/html/Kenny G - Going home.mp3")));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest));
            if (dest.exists())
            {
                int len = 0;
                while ((len = bis.read()) != -1)
                {
                    bos.write(len);
                }
                bos.flush();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
