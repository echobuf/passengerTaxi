package com.echobuf.servicepassengeruser.service;

import com.echobuf.internalcommon.dto.ResponseResult;
import com.echobuf.servicepassengeruser.dto.PassengerUser;
import com.echobuf.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @USER: echobuf
 * @Date: 2022/12/7 - 12 - 07 - 02:48
 * @Description: online-taxi
 */
@Service
public class UserService {

    @Autowired
    PassengerUserMapper passengerUserMapper;

    public ResponseResult loginOrRegister(String passengerPhone){
        // 根据用户手机号去DB查询是否有此用户，有则返回没有则创建
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        System.out.println(passengerUsers.size()==0?"无记录":"有记录："+passengerUsers.get(0).getPassengerPhone()+passengerUsers.get(0).getPassengerName());

        return ResponseResult.success();
    }
}
