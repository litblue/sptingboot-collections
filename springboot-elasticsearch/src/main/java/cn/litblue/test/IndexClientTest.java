package cn.litblue.test;


import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author: zhoucm
 * @time: 2021/3/31  17:26
 * @description:
 */

@Slf4j
@SpringBootTest
public class IndexClientTest {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 添加索引
     *
     * @throws IOException
     */
    @Test
    public void createIndex() throws IOException {
        // 测试索引的创建
        CreateIndexRequest request = new CreateIndexRequest("litblue_index");

        // 执行创建请求
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

        log.info("{}", createIndexResponse);
    }

    /**
     * 获取索引，只能判断其是否存在
     */
    @Test
    public void getIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("litblue_index");
        boolean flag = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        log.info("result---------------{}", flag);
    }


    /**
     * 删除索引
     * @throws IOException
     */
    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("litblue_index");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);

        getIndex();
    }

}
