package com.echobuf.servicepassengeruser.controller;

import com.echobuf.internalcommon.dto.ResponseResult;
import com.echobuf.internalcommon.request.VerificationCodeDTO;
import com.echobuf.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: echobuf
 * @Date: 2022/12/7 - 12 - 07 - 02:36
 * @Description: online-taxi
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("用户手机号："+passengerPhone);

        return userService.loginOrRegister(passengerPhone);
    }
}
