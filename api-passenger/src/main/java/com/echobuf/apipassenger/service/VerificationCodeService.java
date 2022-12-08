package com.echobuf.apipassenger.service;

import com.echobuf.apipassenger.remote.ServicePassengerUser;
import com.echobuf.apipassenger.remote.ServiceVerificationCodeClient;
import com.echobuf.internalcommon.constant.CommonStatusEnum;
import com.echobuf.internalcommon.constant.IdentityConstants;
import com.echobuf.internalcommon.constant.TokenConstants;
import com.echobuf.internalcommon.dto.ResponseResult;
import com.echobuf.internalcommon.request.VerificationCodeDTO;
import com.echobuf.internalcommon.response.NumberCodeResponse;
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
 * @Date: 2022/12/2 - 12 - 02 - 00:41
 * @Description: online-taxi
 */
@Service
public class VerificationCodeService {

    @Autowired
    ServiceVerificationCodeClient serviceVerificationCodeClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ServicePassengerUser servicePassengerUser;


    /**
     * 生成验证码
     * @param passengerPhone
     * @return
     */
    public ResponseResult generatorCode(String passengerPhone) {

        //调用验证码服务(业务逻辑写死 size = 6)
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationCodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();

        //存入redis
        //key,value,过期时间
        String key = RedisPrefixUtils.generateVerificationCodeKey(passengerPhone);
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        //返回
        return ResponseResult.success("");
    }

    /**
     * 校验验证码
     * @param passengerPhone
     * @param verificationCode
     * @return
     */
    public ResponseResult checkVerificationCode(String passengerPhone,String verificationCode){
        //根据手机号，去redis查看是否有对应的key
        String key = RedisPrefixUtils.generateVerificationCodeKey(passengerPhone);
        String redisCode = stringRedisTemplate.opsForValue().get(key);
        //校验验证码，根据校验结果处理对应情况
        if(StringUtils.isBlank(redisCode)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if(!verificationCode.trim().equals(redisCode.trim())){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        //校验成功，根据用户手机号查询用户是否注册
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUser.loginOrRegister(verificationCodeDTO);
        //颁发token
        String accessToken = JWTUtils.generatorToken(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JWTUtils.generatorToken(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);
        //把token存入redis
        String accessTokenKey = RedisPrefixUtils.generateTokenKey(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey,accessToken,30, TimeUnit.DAYS);
        String refreshTokenKey = RedisPrefixUtils.generateTokenKey(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenKey,refreshToken,31, TimeUnit.DAYS);

        //响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(tokenResponse);
    }
}
