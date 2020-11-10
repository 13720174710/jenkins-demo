package com.study.current.controller;

import com.study.current.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redisCluster")
@Slf4j
public class RedisClusterController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String setValue() {
        redisUtil.set("hello","world");
        log.info("set redis success...");
        return "success";
    }

}
