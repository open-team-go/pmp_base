package com.arz.pmp.base.framework.mysql;

import java.util.Properties;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * description 数据库事务
 * 
 * @author chen wei
 * @date 2019/11/11
 */
@Configuration
public class TransactionConfig {

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("get*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
        properties.setProperty("query*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
        properties.setProperty("select*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
        properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("insert*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("del*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("edit*", "PROPAGATION_REQUIRED,-Exception");
        return properties;
    }

    @Bean(name = "txAdvice")
    public TransactionInterceptor getAdvisor(DataSourceTransactionManager transactionManager) throws Exception {
        TransactionInterceptor tsi = new TransactionInterceptor(transactionManager, getProperties());
        return tsi;
    }

    @Bean
    public BeanNameAutoProxyCreator txProxy() {
        return getCreator("txAdvice");
    }

    private BeanNameAutoProxyCreator getCreator(String interceptorNames) {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setInterceptorNames(interceptorNames);
        creator.setBeanNames("*ServiceImpl", "*ServiceImpl");
        creator.setProxyTargetClass(true);
        return creator;
    }

}