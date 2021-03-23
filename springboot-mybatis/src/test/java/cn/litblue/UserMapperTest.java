package cn.litblue;

import cn.litblue.mapper.UserMapper;
import cn.litblue.model.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author: litblue
 * @time: 2020/12/14  12:55
 * @version: 1.0.0
 * @description:
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void selectUserTest(){

    }

    @Test
    public void saveUserTest(){
        User user = new User("litblue", "miss@test.com");
        try {
            Integer result = userMapper.insert(user);
            Assert.notNull(user.getId(), "id is null");
            log.info("success--{}", result);
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Test
    public void updateUserTest(){
        User user = userMapper.selectById(10L);
        user.setEmail("test@163.com");
        user.setName("shabi");

        userMapper.updateById(user);
    }

    @Test
    public void deletedUserTest(){
        userMapper.deleteById(5);
    }

    @Test
    public void selectUsersTest(){
        // 不加任何条件查询
//        userMapper.selectList(null).forEach(System.out::println);


        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }
}
