package cn.litblue;

import io.swagger.annotations.Api;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: litblue
 * @time: 2020/12/09  23:10
 * @version: 1.0.0
 * @description:  启动类
 */

@EnableAsync
@Api(hidden = true)
@SpringBootApplication
@EnableTransactionManagement
/* 扫描 mapper */
@MapperScan("cn.litblue.mapper")
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}
