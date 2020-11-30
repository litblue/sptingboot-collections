package cn.litblue.basedsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/11/29  19:42
 */

@RestController
@RequestMapping("api/test")
public class TestController {

    @GetMapping("hello")
    public String hello(){
        return "hello security";
    }


}
