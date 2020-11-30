package cn.litblue.dao;

import cn.litblue.datajpa.DataJpaApplication;
import cn.litblue.datajpa.dao.UserRepository;
import cn.litblue.datajpa.entity.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/4  13:15
 */


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataJpaApplication.class)
@Transactional
@Rollback(false)
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    /**
     * 查询所有
     * @return
     */
    @Test
    public void findAll() {
        List<User> users = userRepository.findAll();
        log.info("user info =====> {}",users);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
   @Test
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * 根据 id 删除
     * @param id
     */
    @Test
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * 更新用户信息
     * @param user
     */
    @Test
    public void update(User user) {
        userRepository.save(user);
    }


    /**
     * 分页查询
     */
    @Test
    public void pageTest(){
        Page<User> page = userRepository.findAll(PageRequest.of(0,2));
        log.info("page info =====> {}",page.getContent());
    }

    /**
     * 排序查询
     */
    @Test
    public void sortTest(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        List<User> users = userRepository.findAll(sort);
        log.info("user info =====> {}",users);
    }

    /**
     * 统计
     */
    @Test
    public void getCount(){
        long count = userRepository.count();
        log.info("count =====> {}", count);
    }


    @Test
    public void getUserBySpecification(){

        /*
         * 匿名内部类
         * 自定义查询条件：
         *  1. 实现Specification接口（提供泛型，查询的对象类型）
         *  2. 实现toPredicate方法
         *  3. 借助方法参数中的两个参数{
         *      root：获取 对象需要相查询的对象属性
         *      criteriaBuilder：构造查询条件的，内部封装了很多查询条件
         *  }
         */
        Specification<User> specification = (Specification<User>) (root, query, criteriaBuilder) -> {
            // 获取比较的属性
            Path<Object> name = root.get("name");

            // 构造查询条件
            /*
             * 第一个参数： 需要比较的属性
             * 第二个参数： 当前需要比较的取值
             */

            return criteriaBuilder.equal(name,"handy");
        };
        Optional<User> user = userRepository.findOne(specification);

        log.info("user ===> {}", user);
    }


    @Test
    public void getUsersBySpecification() {
        Specification<User> specification = (Specification<User>) (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name").as(String.class),"han%");
        List<User> users = userRepository.findAll(specification);
        log.info("users ===> {}", users);
    }

    @Test
    public void getUserBySpecifications(){
        Specification<User> specification = (Specification<User>) (root, query, criteriaBuilder) -> {

            Predicate predicateName = criteriaBuilder.like(root.get("name").as(String.class),"han%");
            Predicate predicateEmail = criteriaBuilder.like(root.get("email").as(String.class),"%@163.com");

            // 将多个查询条件组合到一起
            // criteriaBuilder.or();  以或的形式拼接多个查询条件
            return criteriaBuilder.and(predicateName,predicateEmail);
        };

        Sort sort = Sort.by(Sort.Direction.DESC,"id");

        List<User> users = userRepository.findAll(specification,sort);

        log.info("users ===> {}", users);
    }



}
