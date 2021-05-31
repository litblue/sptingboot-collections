package cn.litblue.controller;

import cn.litblue.module.entity.VideosInfo;
import cn.litblue.service.ContentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhoucm
 * @time: 2021/5/30  19:51
 * @description:
 */
@RestController
@RequestMapping("/content")
public class ContentController {

    @Resource
    private ContentService contentService;

    /**
     * 爬取数据，并加到 es 中
     * @return
     */
    @PostMapping("/parseContent")
    public Boolean parseContent(String keyword){
        try {
            return contentService.parseContent(keyword);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 从es 库中获取
     * @param keyword
     * @param start
     * @param rows
     * @return
     */
    @GetMapping("/queryContent")
    public List<VideosInfo> queryContent(String keyword, int start, int rows){
        try {
            return contentService.queryContent(keyword, start, rows);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
