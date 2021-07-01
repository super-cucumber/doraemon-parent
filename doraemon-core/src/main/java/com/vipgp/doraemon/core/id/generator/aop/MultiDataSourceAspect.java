package com.vipgp.doraemon.core.id.generator.aop;

import com.vipgp.doraemon.core.id.generator.common.DbNameThreadLocal;
import com.vipgp.doraemon.core.id.generator.db.balance.algorithm.DbSelector;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.hint.HintManager;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: linshangdou@gmail.com
 * @date: 2021/1/21 10:12
 */
@Slf4j
@Aspect
@Component
public class MultiDataSourceAspect {

    @Autowired
    DbSelector selector;

    ThreadLocal<String> currentDb=new ThreadLocal();

    @Pointcut("execution(* com.vipgp.doraemon.core.id.generator.manager.impl..*(..))")
    public void switchDataSource() {
        log.info("switchDataSource method body");
    }

    @Before("switchDataSource()")
    public void switchDataSourceBefore(){
        HintManager.clear();
        HintManager instance=HintManager.getInstance();
        String dbName=selector.getDbName();
        instance.setDatabaseShardingValue(dbName);
        // 本地设置
        DbNameThreadLocal.getSinglton().clear();
        DbNameThreadLocal.getSinglton().set(dbName);
        log.info("before switchDataSource method");
    }

    @After("switchDataSource()")
    public void switchDataSourceAfter(){
        HintManager.clear();
        // 本地清除
        DbNameThreadLocal.getSinglton().clear();
        log.info("after switchDataSource method");

    }
}
