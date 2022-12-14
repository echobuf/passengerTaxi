package com.echobuf.apipassenger.service;

import com.echobuf.apipassenger.remote.ServicePassengerUser;
import com.echobuf.internalcommon.dto.PassengerUser;
import com.echobuf.internalcommon.dto.ResponseResult;
import com.echobuf.internalcommon.dto.TokenResult;
import com.echobuf.internalcommon.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @USER: echobuf
 * @Description: online-taxi
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    ServicePassengerUser servicePassengerUser;

    public ResponseResult getUserByAccessToken(String accessToken){
        log.info("accessToken : ",accessToken);
        //根据accessToken解析出用户手机号
        TokenResult tokenResult = JWTUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        //根据手机号获取用户信息
        ResponseResult<PassengerUser> result = servicePassengerUser.getUserByPhone(phone);
        return ResponseResult.success(result.getData());
    }
}
