package com.mall.common.utils;

import org.apache.commons.codec.digest.Md5Crypt;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Utils {

    public static String md5Crypt(String cryptString){
        return Md5Crypt.apr1Crypt(cryptString);
    }

    public static String getMD5(String md5String){
        try {
            //生成一个MD5计算摘要
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            //计算md5函数
            messageDigest.update(md5String.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String md5 = new BigInteger(1, messageDigest.digest()).toString(16);
            //BigInteger会把0省略掉，需补全至32位
            return fillMD5(md5);
        }catch (Exception exception){
            throw new RuntimeException("md5加密错误:" + exception.getMessage(), exception);
        }
    }

    public static String fillMD5(String md5){
        return md5.length() == 32 ? md5 : fillMD5("0" + md5);
    }
}
