package cn.litblue.datajpa.dao;

import cn.litblue.datajpa.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/4  20:28
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
}
