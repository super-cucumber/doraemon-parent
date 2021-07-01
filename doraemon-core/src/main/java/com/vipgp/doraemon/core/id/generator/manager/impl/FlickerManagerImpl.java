package com.vipgp.doraemon.core.id.generator.manager.impl;

import com.vipgp.doraemon.core.id.generator.common.DbNameThreadLocal;
import com.vipgp.doraemon.core.id.generator.dao.FlickerIdDao;
import com.vipgp.doraemon.core.id.generator.domain.FlickerIdDO;
import com.vipgp.doraemon.core.id.generator.manager.FlickerManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: linshangdou@gmail.com
 * @date: 2021/1/21 10:29
 */
@Slf4j
@Component
public class FlickerManagerImpl implements FlickerManager {

    @Resource
    FlickerIdDao flickerIdDao;

    /**
     * 每个数据库自增步长的初始化状态
     */
    Map<String,Boolean> incrementFlagMap=new HashMap<>();

    /**
     * 获取自增ID
     *
     * @return
     */
    @Override
    public Long getId() {
        //重新自动步长, session级别，所以每次新连接需要重新设置下
        String dbName = DbNameThreadLocal.getSinglton().get();
        if(!isAlreadyInit(dbName)){
            // 设置步长
            flickerIdDao.setAutoIncrementIncrement();
            incrementFlagMap.put(dbName,true);
            log.info("reset auto increment increment");
        }

        FlickerIdDO entity=new FlickerIdDO();
        entity.setGmtCreate(new Date());
        flickerIdDao.add(entity);

        return entity.getId();
    }

    /**
     * 判断是否已初始化
     * @return
     */
    private Boolean isAlreadyInit(String dbName) {
        Boolean flag = incrementFlagMap.get(dbName);
        if (flag != null && flag) {
            return true;
        }

        return false;
    }
}
