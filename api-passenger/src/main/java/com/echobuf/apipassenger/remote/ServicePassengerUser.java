package com.echobuf.apipassenger.remote;

import com.echobuf.internalcommon.dto.ResponseResult;
import com.echobuf.internalcommon.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @USER: echobuf
 * @Date: 2022/12/8 - 12 - 08 - 01:34
 * @Description: online-taxi
 */
@FeignClient("service-passenger-user")
public interface ServicePassengerUser {
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);

    @RequestMapping(method = RequestMethod.GET,value = "/user/{phone}")
    ResponseResult getUserByPhone(@PathVariable("phone") String phone);
}