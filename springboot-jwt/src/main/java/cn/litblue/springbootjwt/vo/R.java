package cn.litblue.springbootjwt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/11  10:36
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R implements Serializable {

    /** 响应业务状态 */
    private Integer status;

    /** 响应消息 */
    private String msg;

    /** 响应中的数据 */
    private Object data;


    /** 业务处理成功，并返回数据 */
    public static R success(Object data) {
        return new R(200, "success", data);
    }

    /** 业务处理成功，只返回状态码与成功标识 */
    public static R success() {
        return new R(200,"success",null);
    }

    /** 业务处理失败，返回错误信息 */
    public static R error(String msg) {
        return new R(500, msg, null);
    }

}
