package com.study.current.controller;

import com.study.current.annotation.ApiIdempotent;
import com.study.current.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/idempotent")
@Slf4j
public class ApiIdempotentController {

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public String test(){
        return tokenService.createToken();
    }

    @ApiIdempotent
    @GetMapping("/test/Idempotence")
    public Object testIdempotence() {
        String token = "接口幂等性测试";
        return token;
    }

}
