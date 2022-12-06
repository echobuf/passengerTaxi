package com.echobuf.serviceverificationcode.controller;

import com.echobuf.internalcommon.dto.ResponseResult;
import com.echobuf.internalcommon.response.NumberCodeResponse;
import lombok.experimental.Accessors;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: echobuf
 * @Date: 2022/12/5 - 12 - 05 - 21:29
 * @Description: online-taxi
 */
@RestController
@Accessors(chain = true)
public class NumberCodeController {

    @GetMapping("/verificationCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){

        System.out.println("验证码长度size = "+size);
        //生成验证码
        double mathRandom = (Math.random()*9+1) * Math.pow(10,size-1);
        int resultInt = (int)mathRandom;
        System.out.println("生成验证码: " + resultInt);
        //封装ResponseResult中的data即NumberCodeResponse
        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(resultInt);

        return ResponseResult.success(response);
    }
}
