package com.echobuf.apipassenger.service;

import com.echobuf.apipassenger.remote.ServiceVerificationCodeClient;
import com.echobuf.internalcommon.dto.ResponseResult;
import com.echobuf.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @USER: echobuf
 * @Date: 2022/12/2 - 12 - 02 - 00:41
 * @Description: online-taxi
 */
@Service
public class VerificationCodeService {

    @Autowired
    ServiceVerificationCodeClient serviceVerificationCodeClient;

    public String generatorCode(String passgerPhone) {

        //调用验证码服务(业务逻辑写死 size = 6)
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationCodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println("调用验证码服务，获取验证码: " + numberCode);
        //存入redis
        System.out.println("存入redis");
        //返回
        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("message", "success");
        return result.toString();
    }
}
