package com.weitao.socket.chap04;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @title: InetAddressTest
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/3/814:03
 */
public class InetAddressTest
{
    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            InetAddress address = InetAddress.getByName("www.baidu.com");
            InetAddress add = InetAddress.getByName("183.232.231.172");
            System.out.println(address.getAddress().length);
            System.out.println(add.getHostAddress());
            System.out.println(address.getHostName());
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
    }
}
