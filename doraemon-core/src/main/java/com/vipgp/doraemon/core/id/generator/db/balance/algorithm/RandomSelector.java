package com.vipgp.doraemon.core.id.generator.db.balance.algorithm;

import org.apache.shardingsphere.core.rule.ShardingRule;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.Random;

/**
 * @author: linshangdou@gmail.com
 * @date: 2021/1/21 11:16
 */
@Component
@Primary
public class RandomSelector implements DbSelector{

    /**
     * 数据源s
     */
    private String[] dbs=null;

    @Resource
    private ShardingDataSource shardingDataSource;

    @PostConstruct
    public void init(){
        // get all databases
        ShardingRule rule=this.shardingDataSource.getRuntimeContext().getRule();
        Collection<String> dataSourceNames =rule.getShardingDataSourceNames().getDataSourceNames();
        dbs=new String[dataSourceNames.size()];
        dataSourceNames.toArray(dbs);
    }


    /**
     * 获取DB
     *
     * @return
     */
    @Override
    public String getDbName() {
        Random random=new Random();
        int luckyNumber= random.nextInt(100);
        int index=luckyNumber % dbs.length;

        return dbs[index];
    }
}
