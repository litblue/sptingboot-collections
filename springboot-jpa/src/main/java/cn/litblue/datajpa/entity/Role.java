package cn.litblue.datajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * (Role)实体类
 *
 * @author litblue
 * @since 2020-10-04 15:27:29
 */

@Data
@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    private static final long serialVersionUID = 973804534388629430L;
    /**
    * 角色id
    */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "role_id")
    private Long roleId;
    /**
    * 角色名
    */
    @Column(name = "role_name")
    private String roleName;


    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>(8);
}