package com.echobuf.apipassenger.controller;

import com.echobuf.apipassenger.service.UserService;
import com.echobuf.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @USER: echobuf
 * @Description: online-taxi
*/
@RestController
public class UserConttoller {

    @Autowired
    UserService userService;
    @GetMapping("/users")
    public ResponseResult getUser(HttpServletRequest request){

        //获取accessToken
        String accessToken = request.getHeader("Authorization");
        //根据token获取用户信息
        return userService.getUserByAccessToken(accessToken);
    }
}
