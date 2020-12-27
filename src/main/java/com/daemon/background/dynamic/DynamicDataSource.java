package com.daemon.background.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * 继承AbstractRoutingDataSource(每执行一次数据库，动态获取DataSource)
 * @author dell
 * Created on 2019-07-25 17:18
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("当前数据源为{}", DynamicDataSourceContextHolder.getDataSourceType());

        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
