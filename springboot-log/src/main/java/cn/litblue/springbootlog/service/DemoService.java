package cn.litblue.springbootlog.service;

import cn.litblue.springbootlog.entity.Logs;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/13  20:47
 */
public interface DemoService {

    /**
     * 插入日志信息
     * @param logs
     */
    void insert(Logs logs);
}
