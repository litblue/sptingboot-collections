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
    public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "role_id", type = IdType.AUTO)
      private Long roleId;

    private String roleName;


}
