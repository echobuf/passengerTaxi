package com.echobuf.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

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

    //生成token
    public static String generatorToken(Map<String,String> map){
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
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("passengerName","张三");
        map.put("passengerPhone","12346");
        System.out.println(generatorToken(map));
    }
}

