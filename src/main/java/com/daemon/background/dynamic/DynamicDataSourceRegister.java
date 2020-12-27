package com.daemon.background.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 注册动态数据源
 * 初始化数据源和提供了执行动态切换数据源的工具类
 * EnvironmentAware（获取配置文件配置的属性值）
 * @author dell
 * Created on 2019-07-25 17:19
 **/
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    /**
     * 指定默认数据源
     */
    private static final String DATASOURCE_TYPE_DEFAULT = "com.alibaba.druid.pool.DruidDataSource";
    /**
     * 默认数据源
     */
    private DataSource defaultDataSource;
    /**
     * 用户自定义数据源
     */
    private Map<String, DataSource> slaveDataSources = new HashMap<>();

    @Override
    public void setEnvironment(Environment environment) {
        initDefaultDataSource(environment);
        initSlaveDataSources(environment);
    }

    /**
     * 初始化默认数据源：master
     */
    private void initDefaultDataSource(Environment env) {
        Map<String, String> map = new HashMap<>();
        map.put("driver", env.getProperty("dynamic.datasource.master.driver-class-name"));
        map.put("url", env.getProperty("dynamic.datasource.master.url"));
        map.put("username", env.getProperty("dynamic.datasource.master.username"));
        map.put("password", env.getProperty("dynamic.datasource.master.password"));
        defaultDataSource = buildDataSource(map);
        slaveDataSources.put("master", defaultDataSource);//默认数据源放到动态数据源里
    }

    /**
     * 初始化多个从数据源
     */
    private void initSlaveDataSources(Environment env) {
        String name = env.getProperty("dynamic.datasource.names");
        if (!StringUtils.isEmpty(name)) {
            String[] names = name.split(",");
            for (String id : names) {
                Map<String, String> map = new HashMap<>();
                map.put("driver", env.getProperty("dynamic.datasource." + id + ".driver-class-name"));
                map.put("url", env.getProperty("dynamic.datasource." + id + ".url"));
                map.put("username", env.getProperty("dynamic.datasource." + id + ".username"));
                map.put("password", env.getProperty("dynamic.datasource." + id + ".password"));
                DataSource ds = buildDataSource(map);
                slaveDataSources.put(id, ds);
            }
        }
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        //添加默认数据源
        targetDataSources.put("dataSource", defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        //添加其他数据源
        targetDataSources.putAll(slaveDataSources);
        for (String key : slaveDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIds.add(key);
        }

        //创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        //注册 - BeanDefinitionRegistry
        beanDefinitionRegistry.registerBeanDefinition("dataSource", beanDefinition);

        logger.info("Dynamic DataSource Registry");
    }

    /**
     * 建立数据源
     */
    public DataSource buildDataSource(Map<String, String> dataSourceMap) {
        String type = "";
        try {
            type = dataSourceMap.get("type");
            if (StringUtils.isEmpty(type)) {
                // 默认DataSource
                type = DATASOURCE_TYPE_DEFAULT;
            }
            Class<? extends DataSource> dataSourceType = (Class<? extends DataSource>) Class.forName(type);
            String driverClassName = dataSourceMap.get("driver");
            String url = dataSourceMap.get("url");
            String username = dataSourceMap.get("username");
            String password = dataSourceMap.get("password");
            // 自定义DataSource配置
            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                    .username(username).password(password).type(dataSourceType);
            return factory.build();
        } catch (ClassNotFoundException e) {
            logger.debug("{} is not found.", type);
        }
        return null;
    }


}
