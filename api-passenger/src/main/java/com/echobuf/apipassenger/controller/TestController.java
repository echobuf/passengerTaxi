package com.echobuf.apipassenger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: echobuf
 * @Date: 2022/12/1 - 12 - 01 - 02:25
 * @Description: online-taxi
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
