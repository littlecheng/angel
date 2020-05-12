package com.weitao.vo;

/**
 * @title: Greeting
 * @projectName demo
 * @description: TODO
 * @author Administrator
 * @date 2020/5/1019:33
 */
public class Greeting
{
    private final long id;

    private final String content;

    public Greeting(long id, String content)
    {
        this.id = id;
        this.content = content;
    }

    public long getId()
    {
        return id;
    }

    public String getContent()
    {
        return content;
    }
}
