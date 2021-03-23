package cn.litblue.config.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author: litblue
 * @time: 2020/12/9  21:09
 * @version: 1.0.0
 * @description: mybatis-plus 自动填充功能
 * {@link <a href="https://baomidou.com/guide/auto-fill-metainfo.html">自动填充功能|MyBatis-Plus</a>}
 *
 */

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
       this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
       this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}