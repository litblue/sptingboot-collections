package cn.litblue.springbootjwt.controller;

import cn.litblue.springbootjwt.pojo.User;
import cn.litblue.springbootjwt.security.annotation.Verified;
import cn.litblue.springbootjwt.service.TokenService;
import cn.litblue.springbootjwt.service.UserService;
import cn.litblue.springbootjwt.vo.R;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/15  12:10
 */

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    /**
     * 登陆校验
     * @param user 登录u参数
     * @return token
     */
    @PostMapping("login")
    public R login(User user){
        Assert.notNull(user, "user can not be null.");

        User userInfo = userService.getByName(user.getName());

        if (userInfo == null){
            return R.error("该用户不存在");
        } else {
            if (!userInfo.getPassword().equals(user.getPassword())){
                return R.error("登陆失败，请核对信息。");
            } else {
                String token = tokenService.getToken(user);
                return R.success(token);
            }
        }
    }

    @Verified
    @GetMapping("test")
    public String testSecurity(){
        return "哈哈哈哈，我不用登陆，我有@Verified注解";
    }


    @GetMapping("token/test")
    public String tokenTest(){
        return "需要登陆后才能看";
    }
}
