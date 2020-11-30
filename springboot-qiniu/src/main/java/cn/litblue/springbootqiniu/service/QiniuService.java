package cn.litblue.springbootqiniu.service;


import cn.litblue.springbootqiniu.vo.R;
import com.qiniu.common.QiniuException;

import java.io.File;
import java.io.InputStream;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/11/15  23:58
 */
public interface QiniuService {
    /**
     * 以文件的形式上传
     *
     * @param file
     * @param fileName :
     * @exception QiniuException
     * @return: java.lang.String
     */
    R uploadFile( File file,  String fileName) throws QiniuException;

    /**
     * 以流的形式上传
     *
     * @param inputStream
     * @param fileName :
     * @exception QiniuException
     * @return: java.lang.String
     */
    R uploadFile( InputStream inputStream,  String fileName) throws QiniuException;

    /**
     * 删除文件
     *
     * @param name :
     * @return: java.lang.String
     */
    R delete(String name) throws QiniuException;

}