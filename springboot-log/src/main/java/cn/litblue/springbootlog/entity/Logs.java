package cn.litblue.springbootlog.entity;

import lombok.Data;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/13  20:48
 */
@Data
public class Logs {

    /**
     * 日志内容
     */
    private String content;
    /**
     * IP地址
     */
    private String ipAddress;
    /**
     * 日志key
     */
    private String logKey;
    /**
     * 日志类型
     */
    private Integer type;
}
