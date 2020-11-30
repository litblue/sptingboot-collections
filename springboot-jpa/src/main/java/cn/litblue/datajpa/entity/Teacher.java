package cn.litblue.datajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * (Teacher)实体类
 *
 * @author litblue
 * @since 2020-10-04 15:27:29
 */
@Data
@Entity
@Table(name = "teacher")
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements Serializable {
    private static final long serialVersionUID = 220783525442476883L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer techId;

    @Column(name = "tech_name")
    private String techName;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

}