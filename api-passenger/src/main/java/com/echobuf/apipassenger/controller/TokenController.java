package com.echobuf.apipassenger.controller;

import com.echobuf.apipassenger.service.TokenService;
import com.echobuf.internalcommon.dto.ResponseResult;
import com.echobuf.internalcommon.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: echobuf
 * @Description: online-taxi
 */
@RestController
public class TokenController {
    @Autowired
    TokenService tokenService;
    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse ){

        return tokenService.refreshToken(tokenResponse.getRefreshToken());
    }
}
