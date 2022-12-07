package com.echobuf.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @USER: echobuf
 * @Description: online-taxi
 */
public class JWTUtils {

    //盐
    private static final String SIGNATURE = "echobuf@$$";

    private static final String JWT_KEY_PHONE = "passengerPhone";

    //生成token
    public static String generatorToken(String passengerPhone){
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE,passengerPhone);
        //token过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);//获取当前日历时间+1天的时间
        Date date = calendar.getTime();

        JWTCreator.Builder builder = JWT.create();
        //整合map
        map.forEach(
                (k,v)->{
                       builder.withClaim(k,v);
                }
        );
        //整合过期时间
        builder.withExpiresAt(date);
        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGNATURE));

        return sign;
    }

    //解析token
    public static String parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();

        return phone;

    }
    public static void main(String[] args) {
        String token = generatorToken("123456789");
        System.out.println("生成的token："+token);
        System.out.println("解析："+parseToken(token));
    }
}
