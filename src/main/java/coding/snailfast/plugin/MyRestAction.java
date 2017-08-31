package coding.snailfast.plugin;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.BytesRestResponse;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestStatus;

/**
 * @project 插件的业务处理类
 * @file MyRestAction.java 创建时间:2017年8月31日下午4:21:22
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @author dzn
 * @version 1.0
 *
 */
public class MyRestAction extends BaseRestHandler {
    private final static Logger LOGGER = LogManager.getLogger(MyRestAction.class); 
    
    /**
     *@constructor 构造方法
     *@param settings
     *@param controller
     */
    @Inject  
    public MyRestAction(Settings settings, RestController controller) {  
        super(settings);  
        controller.registerHandler(RestRequest.Method.GET, "_mytest", this);  
        
        //插件可以接受一个参数，这个参数在程序里面必须要消费，不然会报错
        //访问形式形如: http://host:port/_mytest/abc 其中abc会被映射成请求参数action=abc
        controller.registerHandler(RestRequest.Method.GET, "_mytest/{action}", this);
    }  
    
    
    /**
     *@description 插件的业务处理方法
     *@time 创建时间:2017年8月31日下午4:21:52
     *@param request
     *@param client
     *@return
     *@throws IOException
     *@author dzn
     */
    @Override
    protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client)
            throws IOException {
        LOGGER.info("My Rest Action Handler");  
        Map<String, String> params = request.params();
        LOGGER.info("Param : {}", params);
        String actionParam = request.param("action");
        LOGGER.info("actionParam : {}", actionParam);
        RestChannelConsumer res = new RestChannelConsumer(){
            @Override
            public void accept(RestChannel channel) throws Exception {
                XContentBuilder builder = channel.newBuilder();
                builder.startObject();  
                //返回给页面的信息
                builder.field("message", "This is my first plugin");
                builder.endObject();  
                channel.sendResponse(new BytesRestResponse(RestStatus.OK, builder));
            }
        };
        return res;
    }
    
    
}
