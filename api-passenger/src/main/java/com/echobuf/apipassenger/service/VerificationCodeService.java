package com.echobuf.apipassenger.service;

import com.echobuf.apipassenger.remote.ServiceVerificationCodeClient;
import com.echobuf.internalcommon.dto.ResponseResult;
import com.echobuf.internalcommon.response.NumberCodeResponse;
import com.echobuf.internalcommon.response.TokenResponse;
import net.sf.json.JSONObject;
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

    private String verificationCodePrefix = "passenger-verification-code-";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult generatorCode(String passgerPhone) {

        //调用验证码服务(业务逻辑写死 size = 6)
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationCodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();

        //存入redis
        //key,value,过期时间
        String key = verificationCodePrefix + passgerPhone;
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        //返回
        return ResponseResult.success("");
    }

    public ResponseResult checkVerificationCode(String passengerPhone,String verificationCode){
        //根据手机号，去redis查看是否有对应的key

        //校验验证码

        //根据校验结果处理对应情况

        //如果校验成功则返回附带一个token
        String token = "token str";
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        return ResponseResult.success(tokenResponse);
    }
}
