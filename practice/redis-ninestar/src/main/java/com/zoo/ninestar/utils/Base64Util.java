package com.zoo.ninestar.utils;


import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class Base64Util {
    /*
    public static String encodeStr(String plainText){
        byte[] b=plainText.getBytes(StandardCharsets.UTF_8);
        Base64 base64=new Base64();
        b=base64.encode(b);
        String s=new String(b);
        return s;
    }

    public static String decodeStr(String encodeStr){
        byte[] b=encodeStr.getBytes(StandardCharsets.UTF_8);
        Base64 base64=new Base64();
        b=base64.decode(b);
        String s=new String(b);
        return s;
    }

    public static void main(String[] args) {

        String str1 = "一蓑烟雨任平生";
        String enstr1 = encodeStr(str1);
        String str2 = decodeStr(enstr1);
        System.out.println("原字符串：" + str1     + "     长度：" + str1.length());
        System.out.println("encode字符串：" + enstr1    + "     长度：" + enstr1.length());
        System.out.println("decode字符串：" + str2    + "     长度：" + str2.length() );

    }

     */

    public static String encodeStr(String plainText) throws Exception {
        byte[] b=plainText.getBytes("UTF-8");
        Base64 base64=new Base64();
        b=base64.encode(b);
        String s=new String(b);
        return s;
    }

    /**
     * 作者：*TODO 使用Base64加密
     * return
     */
    public static String decodeStr(String encodeStr) throws Exception {
        byte[] b=encodeStr.getBytes("UTF-8");
        Base64 base64=new Base64();
        b=base64.decode(b);
        String s=new String(b);
        return s;
    }

    public static void main(String[] args) throws Exception {

        String str1 = "一蓑烟雨任平生";
        String enstr1 = encodeStr(str1);
        String str2 = decodeStr(enstr1);
        System.out.println("原字符串：" + str1     + "     长度：" + str1.length());
        System.out.println("encode字符串：" + enstr1    + "     长度：" + enstr1.length());
        System.out.println("decode字符串：" + str2    + "     长度：" + str2.length() );

    }

}
