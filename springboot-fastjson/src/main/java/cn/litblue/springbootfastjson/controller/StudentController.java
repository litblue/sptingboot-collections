package cn.litblue.springbootfastjson.controller;

import cn.litblue.springbootfastjson.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/9/13  20:11
 */

@RestController
public class StudentController {

    @GetMapping("/student")
    public Student getStudent(){

        LocalDate birthtime = LocalDate.of(1998,12,10);

        Student student = new Student();
        student.setName("阿里巴巴爸爸");
        student.setAge(21);
        student.setBirthday(birthtime);
        student.setAssets(new BigDecimal("200.50"));

        List<Object> objectList = new ArrayList<>();
        Map<String, String> stringHashMap = new HashMap<>();
        stringHashMap.put("str","str");
        objectList.add(null);
        objectList.add(stringHashMap);

        student.setObjectList(objectList);

        return student;
    }
}
