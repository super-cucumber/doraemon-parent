package com.vipgp.doraemon.service.dubbo;

/**
 * @author: linshangdou@gmail.com
 * @date: 2021/1/14
 */
public interface IDGenerateService {

    /**
     * 美团Leaf算法
     * @param category
     * @return
     */
    Long getLeafSegmentId(String category);

    /**
     * 根据自定义step直接获取一段号码
     * @param params
     * @return
     */
    LeafSegment getSegment(LeafSegment params);

    /**
     * 根据默认step直接获取一段号码
     * @param key
     * @return
     */
    LeafSegment getSegment(String key);

    /**
     * 美团snowflake算法
     * @return
     */
    Long getLeafSnowflakeId();

    /**
     * 自建Flicker方案分布式自增主键ID
     * @return
     */
    Long getFlickerId();
}
