package cn.litblue.springbootjwt.service.impl;

import cn.litblue.springbootjwt.pojo.TokenSign;
import cn.litblue.springbootjwt.pojo.User;
import cn.litblue.springbootjwt.service.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/15  14:28
 */

@Service
public class TokenServiceImpl implements TokenService {

    /**
     * 获取 token
     *
     * @param user 用户
     * @return token
     */
    @Override
    public String getToken(User user) {

        // 设置token过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, 30);

        // 创建token, 并返回
        return  JWT.create()
                .withClaim("user_id", user.getId())
                .withClaim("user_name",user.getName())
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(TokenSign.SIGN));
    }
}
