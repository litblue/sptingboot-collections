package cn.litblue.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author: zhoucm
 * @time: 2021/3/31  18:08
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private Integer age;

    private LocalDateTime createTime;
}
