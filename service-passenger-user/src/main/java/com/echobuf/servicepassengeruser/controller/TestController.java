package com.echobuf.servicepassengeruser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;

/**
 * @USER: echobuf
 * @Date: 2022/12/7 - 12 - 07 - 00:41
 * @Description: online-taxi
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "test service-passenger-user";
    }
}
