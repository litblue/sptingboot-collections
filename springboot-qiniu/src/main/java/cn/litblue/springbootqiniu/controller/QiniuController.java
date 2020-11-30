package cn.litblue.springbootqiniu.controller;

import cn.litblue.springbootqiniu.service.QiniuService;
import cn.litblue.springbootqiniu.vo.R;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/11/19  23:53
 */

@RestController
@RequestMapping("/api/qiniu")
public class QiniuController {

    private final QiniuService qiniuService;

    public QiniuController(QiniuService qiniuService) {
        this.qiniuService = qiniuService;
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @SneakyThrows
    @PostMapping("upload")
    public R upload(@RequestParam("file") MultipartFile file){
        return qiniuService.uploadFile(file.getInputStream(), file.getOriginalFilename());
    }
}
