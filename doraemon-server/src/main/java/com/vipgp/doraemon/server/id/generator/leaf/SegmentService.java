package com.vipgp.doraemon.server.id.generator.leaf;

import com.sankuai.inf.leaf.IDGenSegment;
import com.sankuai.inf.leaf.common.PropertyFactory;
import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.common.ZeroIDGenSegment;
import com.sankuai.inf.leaf.segment.SegmentIDGenImpl;
import com.sankuai.inf.leaf.segment.dao.IDAllocDao;
import com.sankuai.inf.leaf.segment.dao.impl.IDAllocDaoImpl;
import com.sankuai.inf.leaf.segment.model.LeafAlloc;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: linshangdou@gmail.com
 * @date: 2021/1/14
 */
@Slf4j
@Service
public class SegmentService {

    private String defaultDatabaseName="ds0";

    @Resource
    private ShardingDataSource shardingDataSource;

    private IDGenSegment idGen;

    @PostConstruct
    public void init() throws SQLException, InitException {
        DataSource dataSource= shardingDataSource.getDataSourceMap().get(defaultDatabaseName);
        Properties properties = PropertyFactory.getProperties();
        boolean flag = Boolean.parseBoolean(properties.getProperty(Constants.LEAF_SEGMENT_ENABLE, "true"));
        if (flag) {
            // Config Dao
            IDAllocDao dao = new IDAllocDaoImpl(dataSource);

            // Config ID Gen
            idGen = new SegmentIDGenImpl();
            ((SegmentIDGenImpl) idGen).setDao(dao);
            if (idGen.init()) {
                log.info("Segment Service Init Successfully");
            } else {
                log.error("Segment Service Init Fail");
                throw new InitException("Segment Service Init Fail");
            }
        } else {
            idGen = new ZeroIDGenSegment();
            log.info("Zero ID Gen Service Init Successfully");
        }
    }

    public Result getId(String key) {
        return idGen.get(key);
    }

    public LeafAlloc getSegment(LeafAlloc params){
        return idGen.getSegment(params);
    }

    public LeafAlloc getSegment(String key){
        return idGen.getSegment(key);
    }

    public SegmentIDGenImpl getIdGen() {
        if (idGen instanceof SegmentIDGenImpl) {
            return (SegmentIDGenImpl) idGen;
        }
        return null;
    }
}
