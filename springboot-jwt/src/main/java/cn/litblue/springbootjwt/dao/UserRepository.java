package cn.litblue.springbootjwt.dao;

import cn.litblue.springbootjwt.pojo.User;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/15  11:49
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * 根据姓名，密码查找
     *
     * @param name 姓名
     * @param password 密码
     * @return 结果
     */
    User findUserByNameAndPassword(@NotNull String name, @NotNull String password);

    /**
     *  根据姓名查找
     * @param name name
     * @return result
     */
    User findUserByName(@NotNull String name);
}
