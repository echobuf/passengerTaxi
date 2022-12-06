package com.echobuf.servicepassengeruser.service;

import com.echobuf.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @USER: echobuf
 * @Date: 2022/12/7 - 12 - 07 - 02:48
 * @Description: online-taxi
 */
@Service
public class UserService {
    public ResponseResult loginOrRegister(String passengerPhone){
        // 根据用户手机号去DB查询是否有此用户，有则返回没有则创建

        return ResponseResult.success();
    }
}
