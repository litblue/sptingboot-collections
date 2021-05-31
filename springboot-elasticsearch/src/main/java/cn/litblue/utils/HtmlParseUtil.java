package cn.litblue.utils;

import cn.litblue.module.entity.VideosInfo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhoucm
 * @time: 2021/5/30  21:21
 * @description:
 */

@Slf4j
@Component
public class HtmlParseUtil {

    public ArrayList<VideosInfo> getVideoInfoList(String keyword) throws IOException {
        String url = "https://www.xvideos.com/?k=" + keyword;

        //解析网页
        Document parse = Jsoup.parse(new URL(url), 30000);
        Element element = parse.getElementById("content");

        Elements elementsByClass = element.getElementsByClass("thumb-block");

        ArrayList<VideosInfo> videosInfoList = new ArrayList<>();
        elementsByClass.forEach(elementItem ->{
            VideosInfo videosInfo = new VideosInfo();
            videosInfo.setThumbHref(elementItem.getElementsByTag("a").eq(0).attr("href"));
            videosInfo.setThumbDataSrc(elementItem.getElementsByTag("img").eq(0).attr("data-src"));
            videosInfo.setHdmark(elementItem.getElementsByClass("video-hd-mark").eq(0).text());
            videosInfo.setTitle(elementItem.getElementsByTag("a").eq(1).attr("title"));
            videosInfo.setDuration(elementItem.getElementsByClass("duration").eq(0).text());

            videosInfoList.add(videosInfo);
        });

        return videosInfoList;
    }

}
