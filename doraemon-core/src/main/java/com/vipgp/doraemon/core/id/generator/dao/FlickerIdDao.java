package com.vipgp.doraemon.core.id.generator.dao;

import com.vipgp.doraemon.core.id.generator.domain.FlickerIdDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: linshangdou@gmail.com
 * @date: 2021/1/21 10:36
 */
@Mapper
public interface FlickerIdDao {

    /**
     * 插入记录获取主键ID
     * @param entity
     */
    void add(FlickerIdDO entity);

    /**
     * 更新自动步长, session级别，所以每次新连接需要重新设置下
     */
    void setAutoIncrementIncrement();

    /**
     * 设置起始值
     * @param offset
     */
    void setAutoIncrementOffset(Integer offset);
}
