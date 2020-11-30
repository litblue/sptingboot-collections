package cn.litblue.springbootlog.service.impl;

import cn.litblue.springbootlog.entity.Logs;
import cn.litblue.springbootlog.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/13  20:47
 */

@Slf4j
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public void insert(Logs logs) {
        log.info("假装这里插入数据库");
        log.info("logs info ================> {}", logs);
        log.info("假装插入数据库操作成功");
    }
}
