package org.think2.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.think2.jdbc.JdbcModelFactory;

/**
 * Created by zhoubin on 15/11/27.
 */
public class Think2Context implements ApplicationContextAware {

    private static ApplicationContext springContext;  //spring配置

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        springContext = applicationContext;
        // 加载spring配置后自动加载resources目录下的配置文件
        // String resourcesPath = this.getClass().getClassLoader().getResource("").getPath();
        // init(null, resourcesPath);
        JdbcModelFactory.build(springContext);
    }
}
