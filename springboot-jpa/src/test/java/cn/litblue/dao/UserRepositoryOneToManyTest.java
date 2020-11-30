package cn.litblue.dao;

import cn.litblue.datajpa.DataJpaApplication;
import cn.litblue.datajpa.dao.TeacherRepository;
import cn.litblue.datajpa.dao.UserRepository;
import cn.litblue.datajpa.entity.Teacher;
import cn.litblue.datajpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/4  17:15
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataJpaApplication.class)
@Transactional
@Rollback(false)
public class UserRepositoryOneToManyTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;


    /**
     * 这样保存 外数据库键需要设置允许为空
     */
    @Test
    public void saveTest(){
        User user = new User();
        user.setName("test");
        user.setEmail("litbluehandy@gmail.com");

        Teacher teacher = new Teacher();
        teacher.setTechName("傻逼");

        user.getTeachers().add(teacher);

        teacherRepository.save(teacher);
        userRepository.save(user);
    }

    /**
     * 测试删除
     */
    @Test
    public void deleteTest(){
        userRepository.deleteById(9L);
    }
}
