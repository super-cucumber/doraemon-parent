package com.vipgp.doraemon.server.id.generator.dubbo.service.exposed;

import com.sankuai.inf.leaf.segment.model.LeafAlloc;
import com.vipgp.doraemon.core.id.generator.manager.FlickerManager;
import com.vipgp.doraemon.server.id.generator.leaf.SegmentService;
import com.vipgp.doraemon.server.id.generator.leaf.SnowflakeService;
import com.vipgp.doraemon.service.dubbo.IDGenerateService;
import com.vipgp.doraemon.service.dubbo.LeafSegment;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: linshangdou@gmail.com
 * @date: 2021/1/14
 */
@Slf4j
@Service(version = "1.0.0",interfaceClass = IDGenerateService.class)
public class IDGenerateServiceImpl implements IDGenerateService {


    @Autowired
    SegmentService segmentService;

    @Autowired
    SnowflakeService snowflakeService;

    @Autowired
    FlickerManager flickerManager;

    /**
     * 美团Leaf算法
     *
     * @param category
     * @return
     */
    @Override
    public Long getLeafSegmentId(String category) {
        log.info("leaf segment category="+category);
        Long id= segmentService.getId(category).getId();
        log.info("leaf segment id="+id);
        return id;
    }

    /**
     * 美团snowflake算法
     *
     * @return
     */
    @Override
    public Long getLeafSnowflakeId() {
        // snowflake算法不需要key
        Long id= snowflakeService.getId("default").getId();
        log.info("snow flake id="+id);
        return id;
    }

    /**
     * 自建Flicker方案分布式自增主键ID
     *
     * @return
     */
    @Override
    public Long getFlickerId() {
        Long id=flickerManager.getId();
        log.info("flicker id="+id);
        return id;
    }

    /**
     * 根据自定义step直接获取一段号码
     *
     * @param params
     * @return
     */
    @Override
    public LeafSegment getSegment(LeafSegment params) {
        log.info("leaf segment params {}",params);
        LeafAlloc target = new LeafAlloc();
        BeanUtils.copyProperties(params, target);
        LeafAlloc result = segmentService.getSegment(target);

        LeafSegment segment=new LeafSegment();
        BeanUtils.copyProperties(result,segment);
        log.info("leaf segment params {} result {}",params, segment);
        return segment;
    }

    /**
     * 根据默认step直接获取一段号码
     *
     * @param key
     * @return
     */
    @Override
    public LeafSegment getSegment(String key) {
        log.info("leaf segment key {}",key);
        LeafAlloc result = segmentService.getSegment(key);

        LeafSegment segment=new LeafSegment();
        BeanUtils.copyProperties(result,segment);
        log.info("leaf segment key {} result {}",key, segment);
        return segment;
    }
}
