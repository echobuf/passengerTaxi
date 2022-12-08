package com.echobuf.apipassenger.service;

import com.echobuf.internalcommon.constant.CommonStatusEnum;
import com.echobuf.internalcommon.constant.TokenConstants;
import com.echobuf.internalcommon.dto.ResponseResult;
import com.echobuf.internalcommon.dto.TokenResult;
import com.echobuf.internalcommon.response.TokenResponse;
import com.echobuf.internalcommon.util.JWTUtils;
import com.echobuf.internalcommon.util.RedisPrefixUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @USER: echobuf
 * @Description: online-taxi
 */
@Service
public class TokenService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public ResponseResult refreshToken(String refreshTokenSrc){
        //解析refreshToken
        TokenResult tokenResult = JWTUtils.checkToken(refreshTokenSrc);
        if(tokenResult==null){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();
        //生成refreshToken的key
        String refreshTokenKey = RedisPrefixUtils.generateTokenKey(phone,identity,TokenConstants.REFRESH_TOKEN_TYPE);
        //根据key去redis中获取对应的refreshToken
        String refreshTokenInRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);
        //校验两个refreshToken
        if(StringUtils.isBlank(refreshTokenInRedis) || !(refreshTokenSrc.trim().equals(refreshTokenInRedis.trim()))){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        //生成双token,存储到redis，设置过期时间
        String accessTokenKey = RedisPrefixUtils.generateTokenKey(phone,identity, TokenConstants.ACCESS_TOKEN_TYPE);
        String accessToken = JWTUtils.generatorToken(phone,identity,accessTokenKey);
        String refreshToken = JWTUtils.generatorToken(phone,identity,refreshTokenKey);
        stringRedisTemplate.opsForValue().set(accessTokenKey,accessToken,30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey,refreshToken,31,TimeUnit.DAYS);
        //构建TokenResponse对象封装进ResponseResult中的data返回
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);

        return ResponseResult.success(tokenResponse);
    }
}
