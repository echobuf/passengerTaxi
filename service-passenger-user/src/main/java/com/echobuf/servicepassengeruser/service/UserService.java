package com.echobuf.servicepassengeruser.service;

import com.echobuf.internalcommon.dto.ResponseResult;
import com.echobuf.servicepassengeruser.dto.PassengerUser;
import com.echobuf.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        if(passengerUsers.size()==0){
            PassengerUser user = new PassengerUser();
            user.setPassengerName("张三");
            user.setPassengerPhone(passengerPhone);
            user.setPassengerGender((byte) 0);
            user.setState((byte) 0);
            LocalDateTime now = LocalDateTime.now();
            user.setCreateTime(now);
            user.setUpdateTime(now);

            passengerUserMapper.insert(user);
        }
        return ResponseResult.success();
    }
}
