package cn.litblue.module.entity;

import lombok.Data;

/**
 * @author: zhoucm
 * @time: 2021/5/30  21:50
 * @description:
 */

@Data
public class VideosInfo {

    /**
     * 视频播放链接相对地址
     */
    private String thumbHref;

    /**
     * 封面链接
     */
    private String thumbDataSrc;

    /**
     * 清晰度
     */
    private String hdmark;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频长短
     */
    private String duration;

}
