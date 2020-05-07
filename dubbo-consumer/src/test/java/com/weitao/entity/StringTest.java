package com.weitao.entity;

public class StringTest
{
    public static void main(String[] args) {
     //   String  content = "abcd";
     //   boolean result = content.equals("abc");
     //   System.out.println(result);

        String c1 = new String("abc");
        boolean re = c1.equals("abc");
        System.out.println(re);//true


        String c2 = new String("abc");
        String c3 = new String("abc");
        boolean re3 = c2.equals(c3);
        System.out.println(re3);


     /*   String str0 = "helloWorldChina";
        String str1 = "helloWorld";
        String str3 = str1 + "China";
        System.out.println(str0 == str3);//false

        final String str2 = "helloWorld";
        String str4 = str2 + "China";
        System.out.println(str0 == str4);//true

        final String str5;
        str5 = "helloWorld";
        String str6 = str5 + "China";
        System.out.println(str0 == str6);
*/

     /*   String str1 = "helloworld";
        String str2 = "helloworld";
//也可以,但基本不这样写
        String str3 = new String("helloworld");
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str1 == str3.intern());
*/

        /*String str4 = new String("helloChina");
        System.out.println(str4.intern() == str4);//false
        str4 = str4.intern();
        String str5 = "helloChina";
        String str6 = "helloZhonghua";
        System.out.println(str4 == str5);//true
*/

        String str7 = "good good" + " study";
        String str8 = "good good study";
        System.out.println(str7 == str8);


    }
}
