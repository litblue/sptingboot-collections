package cn.litblue.springbooth2.dao;

import cn.litblue.springbooth2.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/15  11:49
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
}
