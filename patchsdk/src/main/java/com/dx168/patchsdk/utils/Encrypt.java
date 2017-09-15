package com.dx168.patchsdk.utils;

/**
 * 加密解密管理类
 *
 * @author Administrator
 */
public class Encrypt {
    /**
     * 加解密
     * @return
     */
    public static boolean encrypt(byte[] bytes) {
        try {
            int count = bytes.length;
            for (int i = 0; i < count; ++i) {
                byte rawByte = bytes[i];
                bytes[i] = (byte) (rawByte ^ i);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
//        String dir = "/Users/dongyuangui/DeskTop/activity_full_update.xml";
//        String dir = "/Users/dongyuangui/DeskTop/aatest.apk";
//        long t = System.currentTimeMillis();
//        encrypt(dir);
//        System.out.println("time " + (System.currentTimeMillis() - t));
    }
}
