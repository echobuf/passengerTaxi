package com.echobuf.internalcommon.util;

/**
 * @USER: echobuf
 * @Description: online-taxi
 */
public class RedisPrefixUtils {
    public static String verificationCodePrefix = "passenger-verification-code-";
    public static String tokenPrefix = "token-";

    /**
     * 根据手机号生成验证码在redis中的key
     * @param phone
     * @return
     */
    public static String generateVerificationCodeKey(String phone){
        return verificationCodePrefix + phone;
    }

    /**
     * 根据phone，identity生成token在redis中的的key
     * @param phone
     * @param identity
     * @return
     */
    public static String generateTokenKey(String phone,String identity,String tokenType){
        return tokenPrefix + phone + "-" + identity + "-" + tokenType;
    }
}
