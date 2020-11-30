package cn.litblue.springbootqiniu.service.impl;

import cn.litblue.springbootqiniu.service.QiniuService;
import cn.litblue.springbootqiniu.vo.R;
import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.SneakyThrows;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/11/19  23:47
 */

@Service
public class QiniuServiceImpl implements QiniuService {
    @Resource
    private UploadManager uploadManager;

    @Resource
    private BucketManager bucketManager;

    @Resource
    private Auth auth;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Value("${qiniu.path}")
    private String path;

    /**
     * 定义七牛云上传的策略管理
     */
    private StringMap putPolicy;


    /**
     * 重试次数
     */
    private final int RETRY_TIMES = 3;

    /**
     * 成功码
     */
    private final int SUCCESS_CODE = 200;


    /**
     * 上传 File 格式的文件
     *
     * @param file
     * @param fileName :
     * @return
     * @throws QiniuException
     */
    @Override
    public R uploadFile(File file, String fileName) throws QiniuException {
        Assert.notNull(file,"file must not be null");
        Assert.notNull(fileName,"file name must not be null");

        String prefix = RandomStringUtils.randomAlphabetic(4);
        fileName  = prefix + fileName;

        Response response = this.uploadManager.put(file, fileName, getUploadToken());
        int retry = 0;
        while (response.needRetry() && retry < RETRY_TIMES) {
            response = this.uploadManager.put(file, fileName, getUploadToken());
            retry++;
        }
        if (response.statusCode == SUCCESS_CODE) {
            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);

           path =  path + "/" + putRet.key;

            return R.success(path);
        } else {
            return R.error("文件上传失败");
        }

    }

    /**
     * 上传 流文件
     *
     * @param inputStream
     * @param fileName :
     * @return
     * @throws QiniuException
     */
    @SneakyThrows
    @Override
    public R uploadFile(InputStream inputStream, String fileName) throws QiniuException {
        Assert.notNull(inputStream,"inputStream must not be null");
        Assert.notNull(fileName,"file name must not be null");

        String prefix = RandomStringUtils.randomAlphabetic(4);
        fileName  = prefix + fileName;

        Response response = this.uploadManager.put(inputStream, fileName, getUploadToken(), null, null);
        int retry = 0;
        while (response.needRetry() && retry < RETRY_TIMES) {
            response = this.uploadManager.put(inputStream, fileName, getUploadToken(), null, null);
            retry++;
        }

        if (response.statusCode == SUCCESS_CODE) {

            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);

            path =  path + "/" + putRet.key;
            return R.success(path);
        } else {
            return R.error("文件上传失败");
        }
    }

    /**
     * 删除 文件
     *
     * @param name :
     * @return
     * @throws QiniuException
     */
    @Override
    public R delete(String name) throws QiniuException {
        Assert.notNull(name, "picture must not be null");

        Response response = bucketManager.delete(this.bucket, name);
        int retry = 0;
        while (response.needRetry() && retry++ < RETRY_TIMES) {
            response = bucketManager.delete(this.bucket, name);
        }
        return response.statusCode == 200 ? R.success() : R.error("删除失败");
    }

    /**
     * 获取上传凭证
     */
    private String getUploadToken() {
        return this.auth.uploadToken(bucket, null, 3600, putPolicy);
    }
}
