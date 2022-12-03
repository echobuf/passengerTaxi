package com.echobuf.apipassenger.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @USER: echobuf
 * @Date: 2022/12/2 - 12 - 02 - 00:41
 * @Description: online-taxi
 */
@Service
public class VerificationCodeService {

    public String generatorCode(String passgerPhone){

        //调用验证码服务
        System.out.println("调用验证码服务，获取验证码");
        String code = "11111";
        //存入redis
        System.out.println("存入redis");
        //返回
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("message","success");
        return result.toString();
    }
}
