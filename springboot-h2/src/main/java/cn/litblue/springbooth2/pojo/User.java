package cn.litblue.springbooth2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/15  11:46
 */
@Data
@Entity
@Table(name = "USER")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;



}
