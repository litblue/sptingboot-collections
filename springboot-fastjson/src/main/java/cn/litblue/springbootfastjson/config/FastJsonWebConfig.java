package cn.litblue.springbootfastjson.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.val;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/9/14  8:50
 */


@Configuration
public class FastJsonWebConfig {

    /**
     * 使用@Bean注入fastJsonHttpMessageConvert
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        // 需要定义一个Convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();

        // 设置MediaType
        List<MediaType> typeList = new ArrayList<>();
        typeList.add(MediaType.APPLICATION_JSON);
        fastConverter.setSupportedMediaTypes(typeList);

        // 设置fastjson的全局配置
        FastJsonConfig fastJsonConfig=new FastJsonConfig();
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);

        fastJsonConfig.setSerializerFeatures(
                // 是否输出值为null的字段,默认为false,我们将它打开
                SerializerFeature.WriteMapNullValue,
                // 将Collection类型字段的字段空值输出为[]
                SerializerFeature.WriteNullListAsEmpty,
                // 将字符串类型字段的空值输出为空字符串
                SerializerFeature.WriteNullStringAsEmpty,
                // 将数值类型字段的空值输出为0
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteDateUseDateFormat,
                // 禁用循环引用
                SerializerFeature.DisableCircularReferenceDetect);


        // 在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        return new HttpMessageConverters(fastConverter);
    }
}
