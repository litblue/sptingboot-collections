package cn.litblue.springbootfastjson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;


/**
 * 启动类中注解中移除 Jackson 配置
 * @author Administrator
 *
 * */
@SpringBootApplication
public class SpringbootFastJsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFastJsonApplication.class, args);
    }

}
