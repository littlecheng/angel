package com.weitao.collecttest;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
public class AudioConvertUtils {
    /**
     * 根据远程资源路径，下载资源到本地临时目录
     *
     * @param remoteSourceUrl 远程资源路径
     * @param tmpFileFolder   本地临时目录
     * @return 下载后的文件物理路径
     */
    private static String downloadSource(String remoteSourceUrl, String tmpFileFolder) throws Exception {
        //下载资源
        URL url = new URL(remoteSourceUrl);
        DataInputStream dataInputStream = new DataInputStream(url.openStream());
        String tmpFilePath = tmpFileFolder + getOriginalFileName(remoteSourceUrl);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(tmpFilePath));
        byte[] bytes = new byte[1024];
        int length = 0;
        while ((length = dataInputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, length);
            // System.out.println("下载中....");
        }
        // System.out.println("下载完成...");
        dataInputStream.close();
        fileOutputStream.close();

        return tmpFilePath;
    }

    /**
     * 将本地音频文件转换成mp3格式文件
     *
     * @param localFilePath 本地音频文件物理路径
     * @param targetPath    转换后mp3文件的物理路径
     */
    public static void changeLocalSourceToMp3(String localFilePath, String targetPath) throws Exception {

        File source = new File(localFilePath);
        File target = new File(targetPath);
        AudioAttributes audio = new AudioAttributes();
        Encoder encoder = new Encoder();

        audio.setCodec("libmp3lame");
        audio.setBitRate(new Integer(128000));// 音频比特率
        audio.setSamplingRate(new Integer(44100));// 采样率
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);

        encoder.encode(source, target, attrs);
    }

    /**
     * 下载远程文件到本地，然后转换成mp3格式，并返回mp3的文件绝对路径
     * <p>每次会优先检测mp3文件是否已存在，存在则直接返回mp3绝对路径，否则下载然后转换成mp3再返回</p>
     *
     * @param remoteSourceUrl 远程文件的URL
     * @param tmpFolder       临时文件存放的目录，如/usr/consult/tmp/
     * @return 转换成mp3的文件的绝对路径，如/usr/consult/tmp/fcd124a2-ed6c-4407-b574-81ecc51b4eb4.mp3
     */
    public static String changeRemoteSourceToMp3(String remoteSourceUrl, String tmpFolder) throws Exception {
        // 检测该文件是否已经下载并转换过一次，如果是，则直接返回
        String remoteSourceNameWithoutSuffix = getNameWithoutSuffix(remoteSourceUrl);
        // 格式为/usr/consult/tmp/fcd124a2-ed6c-4407-b574-81ecc51b4eb4.mp3
        String mp3FilePath = tmpFolder + File.separator + remoteSourceNameWithoutSuffix + ".mp3";
        File audioFile = new File(mp3FilePath);
        if (audioFile.exists()) {
            // 文件已在之前转换过一次，直接返回即可
            return mp3FilePath;
        }

        // 下载资源到临时目录
        String tmpRemoteFilePath = downloadSource(remoteSourceUrl, tmpFolder);
        // 转换成mp3格式
        changeLocalSourceToMp3(tmpRemoteFilePath, mp3FilePath);

        return mp3FilePath;
    }

    /**
     * 根据文件url获取文件名（包含后缀名）
     *
     * @param url 文件url
     * @return 文件名（包含后缀名）
     */
    private static String getOriginalFileName(String url) {
        String[] sarry = url.split("/");
        return sarry[sarry.length - 1];
    }

    /**
     * 根据文件url获取文件名（不包含后缀名）
     *
     * @param url 文件url
     * @return 文件名（包含后缀名）
     */
    private static String getNameWithoutSuffix(String url) {
        String originalFileName = getOriginalFileName(url);
        return originalFileName.substring(0, originalFileName.indexOf("."));
    }

    public static void main(String[] args) throws Exception {

        String result = changeRemoteSourceToMp3(
                "https://m701.music.126.net/20200302180305/e58039511fd99c7c512cfe163c369e46/jdyyaac/025a/060b/5108/5018a4aeae1a079b4003fb422141e2ee.m4a", "E:\\");
                              //    https://m701.music.126.net/20200302185512/9011a6afba92ac6192b15b80f28349e7/jdyyaac/5109/555d/5459/fc3e541aedb79c9b42d7c3071369c9bd.m4a
        System.out.println(result);
    }
}
