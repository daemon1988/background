package com.daemon.background.aspect;

import com.daemon.background.annotation.DataSourceAnnotation;
import com.daemon.background.dynamic.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据库切面处理
 * @Order(-1) 保证在@Transactional之前执行
 * @author dell
 * Created on 2019-07-24 10:35
 **/

@Component
@Aspect
@Order(-1)  //保证该AOP在@Transactional之前执行
public class DataSourceAspect {

    private Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    /**
     * 切入连接点之前的操作
     * 获取注解中括号的值，然后放到动态线程池中
     */
    @Before("@annotation(dataSourceAnnotation)")
    public void beforeSwitchDataSource(JoinPoint point, DataSourceAnnotation dataSourceAnnotation) {
        //获取注解的值
        String dbName = dataSourceAnnotation.value();
        //判断当前数据库是否已存在
        if (!DynamicDataSourceContextHolder.isExistsDataSource(dbName)) {
            logger.error("数据源【" + dbName + "】不存在，使用默认的数据源 -> " + point.getSignature());
        } else {
            logger.debug("使用数据源：" + dbName);
            DynamicDataSourceContextHolder.setDataSourceType(dbName);
        }
    }

    /**
     * 执行完切面后，将线程共享中的数据源名称清空
     */
    @After("@annotation(dataSourceAnnotation)")
    public void afterSwitchDataSource(JoinPoint point, DataSourceAnnotation dataSourceAnnotation) {
        logger.debug("清除数据源" + dataSourceAnnotation.value());
        DynamicDataSourceContextHolder.removeDataSourceType();
    }
}
