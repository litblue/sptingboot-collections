package cn.litblue.service;

import cn.litblue.module.entity.VideosInfo;
import cn.litblue.utils.HtmlParseUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zhoucm
 * @time: 2021/5/30  22:32
 * @description:
 */

@Slf4j
@Service
public class ContentService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Resource
    private HtmlParseUtil htmlParseUtil;

    private final static String INDEX ="xvideos";

    /**
     * 爬取数据，并存到 es 种
     * @param keyword
     * @return
     * @throws IOException
     */
    public Boolean parseContent(String keyword) throws IOException {
        ArrayList<VideosInfo> videosInfoArrayList = htmlParseUtil.getVideoInfoList(keyword);

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(TimeValue.timeValueSeconds(20));

        for (VideosInfo videosInfo : videosInfoArrayList) {
             bulkRequest.add(
                     new IndexRequest(INDEX)
                     .source(JSON.toJSONString(videosInfo), XContentType.JSON)
             );
        }

        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        return !bulk.hasFailures();
    }


    public List<VideosInfo> queryContent(String keyword, int start, int rows) throws IOException {
        if (start<1){
            start = 1;
        }

        // 条件搜索
        SearchRequest searchRequest = new SearchRequest(INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 分页
        searchSourceBuilder.from(start).size(rows);

        // 精准匹配
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("title", keyword);
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(6));

        // 构建高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        // 多个高亮
        highlightBuilder.requireFieldMatch(true);

        highlightBuilder.field("title");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("<span>");

        searchSourceBuilder.highlighter(highlightBuilder);

        // 执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        // 解析结果
        List<VideosInfo> videosInfoArrayList = new ArrayList<>();
        for (SearchHit hit : search.getHits().getHits()) {

            // 获取高亮字段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");

            // 原结果
            VideosInfo videosInfo = JSON.parseObject(hit.getSourceAsString(), VideosInfo.class);

            if (title!= null){
                Text[] fragments = title.fragments();
                StringBuilder newTitle = new StringBuilder();
                for (Text text : fragments) {
                    newTitle.append(text);
                }

                // 高亮字段替换原来字段
                videosInfo.setTitle(newTitle.toString());
            }
            videosInfoArrayList.add(videosInfo);
        }

        return videosInfoArrayList;
    }
}
