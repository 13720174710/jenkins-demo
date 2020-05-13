package com.study.current.likou.one;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("jenkins")
    public String test(){
        return "hello world jenkins";
    }

}
