package cn.litblue.springbootfastjson.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/9/13  19:30
 */

@Data
public class Student implements Serializable{
    private String name;
    private int age;

    @JSONField(format = "yyyy.MM.dd")
    private LocalDate birthday;

    private BigDecimal assets;

    private List<Object> objectList;
}
