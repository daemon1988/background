package com.daemon.background.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态数据源上下文管理
 * 用于保存当前线程使用的数据源名
 * @author dell
 * Created on 2019-07-25 17:19
 **/
public class DynamicDataSourceContextHolder {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    /**
     * 存放当前线程使用的数据源类型信息
     */
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<String>();

    /**
     * 存放数据源id
     */
    public static List<String> dataSourceIds = new ArrayList<String>();

    /**
     * 设置数据源
     */
    public static void setDataSourceType(String dataSourceType) {
        logger.debug("切换到{}数据源", dataSourceType);
        THREAD_LOCAL.set(dataSourceType);
    }

    /**
     * 获取数据源
     */
    public static String getDataSourceType() {
        return THREAD_LOCAL.get();
    }

    /**
     * 清除数据源
     */
    public static void removeDataSourceType() {
        THREAD_LOCAL.remove();
    }

    /**
     * 判断当前数据源是否存在
     */
    public static boolean isExistsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }
}
