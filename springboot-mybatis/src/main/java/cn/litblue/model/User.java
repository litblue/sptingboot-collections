package cn.litblue.model;

import cn.litblue.model.enums.StatusEnum;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author: litblue
 * @time: 2020/12/13  19:57
 * @version: 1.0.0
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
public class User {

    @TableId( value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String email;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;

    private StatusEnum status;

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }
}
