package coding.snailfast.plugin;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.rest.RestHandler;

/**
 * @project 插件类
 * @file MyFirstPlugin.java 创建时间:2017年8月31日下午4:13:18
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @author dzn
 * @version 1.0
 *
 */
public class MyFirstPlugin extends Plugin implements ActionPlugin{
    private final static Logger LOGGER = LogManager.getLogger(MyFirstPlugin.class); 
    
    public MyFirstPlugin() {  
        LOGGER.info("Create the Basic Plugin and installed it into elasticsearch");  
    }  
    
    /**
     *@description 给插件类加上业务处理类
     *@time 创建时间:2017年8月31日下午4:13:38
     *@return
     *@author dzn
     */
    @Override  
    public List<Class<? extends RestHandler>> getRestHandlers() {  
        return Collections.singletonList(MyRestAction.class);  
    }  
}
