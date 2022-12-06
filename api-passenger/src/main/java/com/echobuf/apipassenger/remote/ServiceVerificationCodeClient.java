package com.echobuf.apipassenger.remote;

import com.echobuf.internalcommon.dto.ResponseResult;
import com.echobuf.internalcommon.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @USER: echobuf
 * @Date: 2022/12/6 - 12 - 06 - 02:59
 * @Description: online-taxi
 */
@FeignClient("service-verificationcode")
public interface ServiceVerificationCodeClient {
    @RequestMapping(method = RequestMethod.GET,value = "/verificationCode/{size}")
    ResponseResult<NumberCodeResponse> getNumberCode(@PathVariable("size")  int size);
}
