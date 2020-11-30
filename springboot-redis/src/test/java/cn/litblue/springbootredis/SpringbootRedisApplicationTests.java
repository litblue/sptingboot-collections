package cn.litblue.springbootredis;

import cn.litblue.springbootredis.entity.User;
import cn.litblue.springbootredis.utils.RedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRedisApplication.class)
class SpringbootRedisApplicationTests {

    @Autowired
    private RedisOperator redisOperator;

    @Test
    public void saveTest(){
        User user = new User(2,"handy");
        redisOperator.set("user-test2",user);
        log.info("redis是否存在相应的key ======> {}", redisOperator.exist("user-test2"));

        Map<String,Object> map = new HashMap<>();
        map.put("msg","success");
        map.put("code","200");
        redisOperator.hset("status","map" ,map);
        log.info(redisOperator.hgetall("status").toString());
    }


    @Test
    public void queryTest(){
        log.info(redisOperator.hgetall("status").toString());
        log.info(redisOperator.hget("status","map").toString());
        log.info(redisOperator.get("user-test2").toString());
    }

    @Test
    public void deleteTest(){
        redisOperator.del("user-test2");
        redisOperator.hdel("status","map");
    }

}
