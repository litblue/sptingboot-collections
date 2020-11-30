package cn.litblue.datajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @version 1.0.0
 * @author: litblue
 * @time 2020/9/25  23:04
 */

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 用户名
   */
  @Column(nullable = false)
  private String name;

  /**
   * 邮箱地址
   */
  @Column(nullable = true)
  private String email;

  /**
   * 使用注解的形式配置多表关系
   * 1. @oneToMany：配置一对多关系
   *     targetEntity：对方的字节码对象
   * 2. 配置外键
   */
  @OneToMany(targetEntity = Teacher.class)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private Set<Teacher> teachers = new HashSet<Teacher>(8);

  /**
   * 放弃外键维护权
   *    mapperBy： 对方配置关系的属性名称,主表就放弃外键维护权
   */
//  @OneToMany(mappedBy = "user")
//  private Set<Teacher> teachers = new HashSet<Teacher>(8);

  /**
   * 多对多关系映射
   */
  @ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL)
  @JoinTable(
          name = "user_role",    // 中间表名称
          joinColumns = {
                  @JoinColumn(name = "user_id",referencedColumnName = "id")  //中间表user_role字段关联role表的主键字段role_id
          }, inverseJoinColumns = {
          @JoinColumn(name = "role_id",referencedColumnName = "role_id")  //中间表user_role的字段关联user表的主键user_id
  })
  private Set<Role> roles = new HashSet<>(8);
}
