package com.vipgp.doraemon.core.id.generator.sharding;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: linshangdou@gmail.com
 * @date: 2021/1/21 11:34
 */
@Slf4j
public class CustomHintShardingAlgorithm implements HintShardingAlgorithm<String> {

    /**
     * Sharding.
     * <p>
     * <p>sharding value injected by hint, not in SQL.</p>
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding result for data sources or tables's names
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<String> shardingValue) {
        log.info("availableTargetNames="+availableTargetNames);
        log.info("shardingValue="+shardingValue);
        List<String> shardingResult = new ArrayList<>();
        for (String value : shardingValue.getValues()) {
            if (availableTargetNames.contains(value)) {
                shardingResult.add(value);
            }
        }
        return shardingResult;
    }
}
