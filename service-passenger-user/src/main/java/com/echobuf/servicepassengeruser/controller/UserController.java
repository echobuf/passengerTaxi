package com.echobuf.servicepassengeruser.controller;

import com.echobuf.internalcommon.dto.ResponseResult;
import com.echobuf.internalcommon.request.VerificationCodeDTO;
import com.echobuf.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @USER: echobuf
 * @Date: 2022/12/7 - 12 - 07 - 02:36
 * @Description: online-taxi
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 根据手机号新建用户
     * @param verificationCodeDTO
     * @return
     */
    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("用户手机号："+passengerPhone);

        return userService.loginOrRegister(passengerPhone);
    }

    /**
     * 根据手机号查询用户信息
     * @param phone
     * @return
     */
    @GetMapping("/user/{phone}")
    public ResponseResult getUserByPhone(@PathVariable("phone") String phone){

        return userService.getUserByPhone(phone);
    }

}
