package com.echobuf.apipassenger.controller;

import com.echobuf.apipassenger.request.VerificationCodeDTO;
import com.echobuf.apipassenger.service.VerificationCodeService;
import com.echobuf.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: echobuf
 * @Date: 2022/12/2 - 12 - 02 - 00:21
 * @Description: online-taxi
 */

@RestController
public class VerificationCodeController {

    @Autowired
    VerificationCodeService verificationCodeService;
    @GetMapping("/verification-code")
    public ResponseResult VerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("获取用户手机号："+passengerPhone);

        return verificationCodeService.generatorCode(passengerPhone);
    }
}
