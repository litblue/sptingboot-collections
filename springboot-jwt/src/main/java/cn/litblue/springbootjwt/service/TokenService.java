package cn.litblue.springbootjwt.service;

import cn.litblue.springbootjwt.pojo.User;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/15  14:27
 */
public interface TokenService {

    /**
     * 获取 token
     * @param user 用户
     * @return token
     */
    String getToken(User user);
}
