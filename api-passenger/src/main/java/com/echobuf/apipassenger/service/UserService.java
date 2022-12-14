package com.echobuf.apipassenger.service;

import com.echobuf.internalcommon.dto.PassengerUser;
import com.echobuf.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @USER: echobuf
 * @Description: online-taxi
 */
@Service
@Slf4j
public class UserService {

    public ResponseResult getUserByAccessToken(String accessToken){
        log.info("accessToken : ",accessToken);
        //根据accessToken解析出用户手机号

        //根据手机号获取用户信息

        PassengerUser passengerUser = new PassengerUser();
        passengerUser.setPassengerName("张三");
        passengerUser.setProfilePhoto("头像");
        return ResponseResult.success(passengerUser);
    }
}
