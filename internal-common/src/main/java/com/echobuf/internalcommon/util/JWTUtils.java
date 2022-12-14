package com.echobuf.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.echobuf.internalcommon.constant.TokenConstants;
import com.echobuf.internalcommon.dto.TokenResult;
import org.apache.commons.lang.StringUtils;

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

    private static final String JWT_KEY_PHONE = "phone";
    private static final String JWT_KEY_IDENTITY = "identity";
    private static final String JWT_TOKEN_TYPE = "tokenType";
    private static final String JWT_TOKEN_TIME = "tokenTime";

    //生成token
    public static String generatorToken(String phone,String identity,String tokenType){
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE,phone);
        map.put(JWT_KEY_IDENTITY,identity);
        map.put(JWT_TOKEN_TYPE,tokenType);

        JWTCreator.Builder builder = JWT.create();

//        //token过期时间
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE,1);//获取当前日历时间+1天的时间
//        Date date = calendar.getTime();
//        //整合过期时间
//        builder.withExpiresAt(date);
        //整合时间,防止生成的token完全一致
        map.put(JWT_TOKEN_TIME,Calendar.getInstance().getTime().toString());
        //整合map
        map.forEach(
                (k,v)->{
                       builder.withClaim(k,v);
                }
        );

        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGNATURE));

        return sign;
    }

    //解析token
    public static TokenResult parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();

        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);
        return tokenResult;
    }

    //解析token格式是否合法
    public static TokenResult checkToken(String token){
        TokenResult tokenResult = null;
        try {
            tokenResult = JWTUtils.parseToken(token);
        }catch (Exception e){
            //parseToken()报错说明参数不是一个token串，不能被解析
        }
        return tokenResult;
    }
    //测试
    public static void main(String[] args) {
        String token = generatorToken("123456789","1","accessToken");
        System.out.println("生成的token："+token);
        System.out.println("解析："+parseToken(token));
    }
}

