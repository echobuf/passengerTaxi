package com.echobuf.apipassenger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: echobuf
 * @Description: online-taxi
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test api-passenger";
    }

    @GetMapping("/authTest")
    public String test2(){
        return "test auth";
    }

    @GetMapping("/noauthTest")
    public String test3(){
        return "test noauth";
    }
}
