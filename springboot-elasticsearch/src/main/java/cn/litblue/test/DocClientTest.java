package cn.litblue.test;

import cn.litblue.module.entity.User;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.index.IndexReader;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.DeleteAliasRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.directory.SearchResult;
import java.io.IOException;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author: zhoucm
 * @time: 2021/3/31  18:06
 * @description:
 */

@Slf4j
@SpringBootTest
public class DocClientTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    private final static String INDEX = "litblue_index";

    /**
     * 创建文档
     */
    @Test
    public void createDoc() throws IOException {
        User user = new User();
        user.setName("litblue");
        user.setAge(21);
        user.setCreateTime(LocalDateTime.of(1998, 12, 10, 6, 0, 0));

        IndexRequest request = new IndexRequest(INDEX);
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));

        // 将数据放入请求
        IndexRequest source = request.source(JSON.toJSONString(user), XContentType.JSON);

        // 客户端发送请求
        IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);

        log.info(index.toString());
        // 对应命令返回的状态
        log.info("index---{}", index.status());
    }

    @Test
    public void query() {
        String content = "小mi";

    }

    @SneakyThrows
    @Test
    public void testIsExists(){
        GetRequest request = new GetRequest(INDEX, "1");

        // 不获取返回的 _source 的上下文
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");

        boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);

        log.info("is_exsits-{}", exists);
    }

    @SneakyThrows
    @Test
    public void testGetDocument(){
        GetRequest request = new GetRequest(INDEX, "1");
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);

        log.info("文档内容:---{}", response.getSourceAsString());
        log.info("文档信息--{}", response);
    }


    @SneakyThrows
    @Test
    public void testUpdateDocument(){
        UpdateRequest request = new UpdateRequest(INDEX, "1");
        request.timeout(TimeValue.timeValueSeconds(1));

        User user = new User("zhoucm", 23, LocalDateTime.now());
        request.doc(JSON.toJSONString(user), XContentType.JSON);

        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);

        log.info("status-----{}", response.status());

    }


    @SneakyThrows
    @Test
    public void testDeleteDocument(){
        DeleteRequest request = new DeleteRequest(INDEX, "1");
        request.timeout(TimeValue.timeValueSeconds(1));

        DeleteResponse deleteResponse = restHighLevelClient.delete(request, RequestOptions.DEFAULT);

        log.info("status----{}", deleteResponse.status());
    }


    /**
     * 测试批量导入数据
     */
    @SneakyThrows
    @Test
    public void testBulkRequest(){
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(TimeValue.timeValueSeconds(10));

        ArrayList<User> list = new ArrayList<>(30);
        for(int i=0; i<30; i++){
            User user = new User("litblue_"+i, 10+i, LocalDateTime.now());
            list.add(user);
        }

        for (int i = 0; i<list.size(); i++){
            bulkRequest.add(
                    new IndexRequest(INDEX)
                    .id(""+(i+1))
                    .source(JSON.toJSONString(list.get(i)), XContentType.JSON)
            );
        }

        BulkResponse bulkItemResponses = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        log.info("是否失败：----{}", bulkItemResponses.hasFailures());
    }


    @SneakyThrows
    @Test
    public void testSearch(){
        SearchRequest searchRequest = new SearchRequest(INDEX);

        //构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 查询条件， 可以使用 QueryBuilders 工具来实现
        // QueryBuilders.termQuery()  // 精确查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name.keyword", "litblue_2");

        // 匹配所有
//        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();

        // 构建高亮
        searchSourceBuilder.highlighter();
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(6));

        searchRequest.source(searchSourceBuilder);

        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        log.info("hits---{}", JSON.toJSONString(search.getHits()));

        SearchHit[] hits = search.getHits().getHits();
        for (SearchHit hit : hits) {
            log.info("fileds----{}", hit);
        }

    }

}
