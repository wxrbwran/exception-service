package com.xzlcorp.exception.common.utils;

import com.xzlcorp.exception.common.common.Constant;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.tomcat.util.codec.binary.Base64;


/**
 * 描述：   MD5工具
 * @author wuxiaoran
 */

public class Md5Utils {

  public static String getMD5String(String strValue) throws NoSuchAlgorithmException {
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    return Base64.encodeBase64String(md5.digest((strValue + Constant.SALT).getBytes(
        StandardCharsets.UTF_8)));
  }
}
