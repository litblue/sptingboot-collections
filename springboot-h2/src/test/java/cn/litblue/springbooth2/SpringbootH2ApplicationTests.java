package cn.litblue.springbooth2;

import cn.litblue.springbooth2.dao.UserRepository;
import cn.litblue.springbooth2.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Optional;

@Slf4j
@SpringBootTest
class SpringbootH2ApplicationTests {

    @Resource
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        User user = new User();
        user.setName("litblue");
        userRepository.save(user);

        Optional<User> user1 = userRepository.findById(1);

        userRepository.deleteById(2);

        log.info("user----------{}", user1);
        log.info("all-----------{}", userRepository.findAll());
        log.info("------------------finshed-----------------");
    }
}
