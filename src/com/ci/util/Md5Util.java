package com.ci.util;

import java.security.MessageDigest;

public class Md5Util {
    private MessageDigest md5 = null;

    /**
     * 用于获取一个String的md5值
     * @param string
     * @return
     */
    public String getMd5(String str) {
    	try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        byte[] bs = md5.digest(str.getBytes());
        StringBuilder sb = new StringBuilder(40);
        for(byte x:bs) {
            if((x & 0xff)>>4 == 0) {
                sb.append("0").append(Integer.toHexString(x & 0xff));
            } else {
                sb.append(Integer.toHexString(x & 0xff));
            }
        }
        return sb.toString();
    }
}
