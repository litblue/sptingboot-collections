package cn.litblue.springbootjwt.service.impl;

import cn.litblue.springbootjwt.dao.UserRepository;
import cn.litblue.springbootjwt.pojo.User;
import cn.litblue.springbootjwt.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/15  12:13
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    /**
     * 验证该用户登陆信息是否正确
     *
     * @param user 用户
     * @return result
     */
    @Override
    public boolean login(User user) {
        User user1 = userRepository.findUserByNameAndPassword(user.getName(), user.getPassword());
        return user1 != null;
    }

    /**
     * 根据用户名查找
     *
     * @param name name
     * @return user
     */
    @Override
    public User getByName(String name) {
        return userRepository.findUserByName(name);
    }
}
