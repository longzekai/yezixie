package com.dou361.baseutils.utils;

/**
 * ========================================
 * <p>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p>
 * 作 者：chenguanming
 * <p>
 * 个人网站：http://www.dou361.com
 * <p>
 * 版 本：1.0
 * <p>
 * 创建日期：2016/3/21 11:36
 * <p>
 * 描 述：字符串简单加密
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class CryptUtil {

    /**
     * 默认加密key
     */
    private static String cryptKey = "dou361";

    private final static byte[] hex = "0123456789ABCDEF".getBytes();

    private CryptUtil() {
    }

    private static int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    // 从字节数组到十六进制字符串转换
    public static String bytes2HexString(byte[] b) {
        byte[] buff = new byte[2 * b.length];
        for (int i = 0; i < b.length; i++) {
            buff[2 * i] = hex[(b[i] >> 4) & 0x0f];
            buff[2 * i + 1] = hex[b[i] & 0x0f];
        }
        return new String(buff);
    }

    // 从十六进制字符串到字节数组转换
    public static byte[] hexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    /**
     * AES加密算法，不受密钥长度限制
     *
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        byte[] bytes = bytes2HexString(content.getBytes()).getBytes();
        byte[] keys = cryptKey.getBytes();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int number;
            if (i >= keys.length) {
                number = (int) bytes[i] ^ (int) keys[0];
            } else {
                number = (int) bytes[i] ^ (int) keys[i];
            }
            String strNumber = Integer.toHexString(number);
            // 4.补全
            if (strNumber.length() == 1) {
                sb.append("0");
            }
            sb.append(strNumber);
        }
        return sb.toString();
    }

    /**
     * aes解密算法，不受密钥长度限制
     *
     * @param content
     * @return
     */
    public static String decrypt(String content) {
        int size = content.length() / 2;
        byte[] bytes = new byte[size];
        byte[] keys = cryptKey.getBytes();
        for (int i = 0; i < size; i++) {
            if (i >= keys.length) {
                bytes[i] = (byte) (Integer.valueOf(content.substring(2 * i, 2 * i + 2), 16) ^ keys[0]);
            } else {
                bytes[i] = (byte) (Integer.valueOf(content.substring(2 * i, 2 * i + 2), 16) ^ keys[i]);
            }
        }
        return new String(hexString2Bytes(new String(bytes)));
    }

    public static String getCryptKey() {
        return cryptKey;
    }

    public static void setCryptKey(String Key) {
        cryptKey = Key;
    }

}