package cn.litblue.datajpa.dao;

import cn.litblue.datajpa.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/4  17:16
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
}
