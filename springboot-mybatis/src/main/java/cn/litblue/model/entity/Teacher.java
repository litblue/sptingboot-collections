package cn.litblue.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author litblue
 * @since 2020-12-22
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "tech_id", type = IdType.AUTO)
      private Integer techId;

    private String techName;

    private Long userId;


}
