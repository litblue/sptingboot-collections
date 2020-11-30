package cn.litblue.dao;

import cn.litblue.datajpa.DataJpaApplication;
import cn.litblue.datajpa.dao.RoleRepository;
import cn.litblue.datajpa.dao.UserRepository;
import cn.litblue.datajpa.entity.Role;
import cn.litblue.datajpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.beans.Transient;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/4  20:11
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataJpaApplication.class)
@Transactional
@Rollback(false)
public class UserRoleRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * 多对多 级联添加
     *
     */
    @Test
    public void saveTest(){
        // 创建对象
        User user = new User();
        user.setName("many");
        user.setEmail("demo@gamil.com");

        Role role = new Role();
        role.setRoleName("handy");

        // 建立关联
        user.getRoles().add(role);
        // 保存
        userRepository.save(user);
    }

    /**
     * 测试删除
     * 在多对多的删除时，双向级联删除根本不能配置
     * 如果配了的话，如果数据之间有相互引用关系，可能会清空所有数据
     */
    @Test
    public void deleteTest(){
        userRepository.deleteById(1L);
    }
}
