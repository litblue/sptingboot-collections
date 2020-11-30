package cn.litblue.springbootqiniu.config;

import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/11/19  23:45
 */

@Slf4j
@Configuration
public class QiniuUploadConfig {

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.zone}")
    private String zone;

    /**
     * 配置空间的存储区域
     */
    @SneakyThrows
    @Bean
    public com.qiniu.storage.Configuration qiniuConfig() {
        switch (zone) {
            case "huadong":
                return new com.qiniu.storage.Configuration(Region.huadong());
            case "huabei":
                return new com.qiniu.storage.Configuration(Region.huabei());
            case "huanan":
                return new com.qiniu.storage.Configuration(Region.huanan());
            case "beimei":
                return new com.qiniu.storage.Configuration(Region.beimei());
            case "xinjiapo":
                return new com.qiniu.storage.Configuration(Region.xinjiapo());
            default:
                throw new Exception("存储区域配置错误");
        }
    }
    /**
     * 构建一个七牛上传工具实例
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(qiniuConfig());
    }

    /**
     * 认证信息实例
     */
    @Bean
    public Auth auth() {
        return Auth.create(accessKey, secretKey);
    }

    /**
     * 构建七牛空间管理实例
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth(), qiniuConfig());
    }
}
