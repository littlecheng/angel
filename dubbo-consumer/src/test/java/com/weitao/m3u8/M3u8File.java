package com.weitao.m3u8;

/**
 * @title: M3u8File
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/1918:04
 */
public class M3u8File
{
    String fileName; //片段文件名
    String filePath;//片段文件路径
    String tsLengthOfTime;//ts片段时长,单位为秒

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getTsLengthOfTime()
    {
        return tsLengthOfTime;
    }

    public void setTsLengthOfTime(String tsLengthOfTime)
    {
        this.tsLengthOfTime = tsLengthOfTime;
    }
}
