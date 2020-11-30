package cn.litblue.datajpa.dao;


import cn.litblue.datajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/9/27  14:43
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * 根据姓名 查询
     *
     */
    @Query(value="from User where name = ?1")
    User findUserByName(String name);
}
