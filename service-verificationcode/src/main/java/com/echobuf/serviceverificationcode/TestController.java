package com.echobuf.serviceverificationcode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: echobuf
 * @Date: 2022/12/5 - 12 - 05 - 21:00
 * @Description: online-taxi
 */
@RestController
public class TestController {
    @GetMapping("test")
    public String test(){
        return "test service-verification";
    }
}
