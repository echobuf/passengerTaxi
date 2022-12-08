package com.echobuf.apipassenger.interceptor;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.echobuf.internalcommon.constant.TokenConstants;
import com.echobuf.internalcommon.dto.ResponseResult;
import com.echobuf.internalcommon.dto.TokenResult;
import com.echobuf.internalcommon.util.JWTUtils;
import com.echobuf.internalcommon.util.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @USER: echobuf
 * @Description: online-taxi
 */
@Component
public class JwtInterceptor implements HandlerInterceptor{

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        String resultString = "";

        //获取前端传过来的token
        String token = request.getHeader("Authorization");
        //判断token格式是否合法
        TokenResult tokenResult = JWTUtils.checkToken(token);
        if(tokenResult == null){
            resultString = "token invalid";
            result = false;
        }else{
            //判断token是否有效
            String passengerPhone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();
            String tokenKey = RedisPrefixUtils.generateTokenKey(passengerPhone,identity, TokenConstants.ACCESS_TOKEN_TYPE);
            String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);
            if(StringUtils.isBlank(tokenRedis) || !(token.trim().equals(tokenRedis.trim()))){
                resultString = "access token invalid";
                result = false;
            }
        }

        if(!result){
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }
        return result;
    }
}
