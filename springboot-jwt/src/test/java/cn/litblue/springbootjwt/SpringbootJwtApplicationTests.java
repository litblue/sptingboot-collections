package cn.litblue.springbootjwt;

import cn.litblue.springbootjwt.dao.UserRepository;
import cn.litblue.springbootjwt.pojo.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest
class SpringbootJwtApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void create() {
        HashMap<String, Object> map = new HashMap<>(4);

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 60000);

        String token = JWT.create()
                .withHeader(map) // header
                .withClaim("user_id", 123)      // payload
                .withClaim("user_name","litblue")
                .withExpiresAt( instance.getTime())
                .sign(Algorithm.HMAC256("handy"));    // 签名

        System.out.println(token);
    }


    @Test
    void verification(){
        // 创建验证对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("handy")).build();

        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxMjMsInVzZXJfbmFtZSI6ImxpdGJsdWUiLCJleHAiOjE2MDI3ODgxNDF9.p8YL3WIJmw1QBsQl2_b26QqCgXlovGSF3oVXeKp1FF0");

        System.out.println(verify.getClaim("user_id").asInt());
        System.out.println(verify.getClaim("user_name").asString());
    }

    /**
     * 插入初始测试数据
     */
    @Test
    void init(){
        User user = new User();
        user.setName("litblue");
        user.setPassword("miss");

        userRepository.save(user);
    }
}
