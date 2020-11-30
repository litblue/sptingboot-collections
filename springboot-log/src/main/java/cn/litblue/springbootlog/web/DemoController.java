package cn.litblue.springbootlog.web;

import cn.litblue.springbootlog.annotation.Log;
import cn.litblue.springbootlog.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/13  21:04
 */
@Slf4j
@RestController
public class DemoController {

    @Log(name = "insert_test", description = "测试一下插入操作日志写入")
    @GetMapping("/test")
    public void test(HttpServletRequest request){
        log.info("------------------------请求后台成功--------------------------------");
    }
}
