package cn.litblue.springbootjwt.service;

import cn.litblue.springbootjwt.pojo.User;
import com.sun.istack.NotNull;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/15  12:12
 */
public interface UserService {

    /**
     * 根据用户名查找
     * @param name name
     * @return user
     */
    User getByName(@NotNull  String name);

    /**
     * 验证登陆
     * @param user
     * @return
     */
    boolean login(User user);
}
