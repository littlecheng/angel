package com.weitao.programme;

/**
 * @title: StringUtil
 * @projectName angel
 * @description: 请编写一个函数，能将字符串main-action-holder，转换为mainActionHolder
 * @author Administrator
 * @date 2020/3/2610:51
 */
public class StringUtil
{
    public static void main(String[] args)
    {

        String letter = "main-action-holder";
        String[] list = letter.split("\\-");
        StringBuilder builder = new StringBuilder();
        builder.append(list[0]);
        for (String s : list)
        {
            String a = s.substring(0, 1).toUpperCase();
            builder.append(a).append(s.substring(1));
        }
        System.out.println(builder.toString());
    }
}
