package com.vipgp.doraemon.service.dubbo;

import java.io.Serializable;

/**
 * @author: linshangdou@gmail.com
 * @date: 2021/4/22 0:41
 */
public class LeafSegment implements Serializable {
    private String key;
    private long maxId;
    private int step;
    private String updateTime;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getMaxId() {
        return maxId;
    }

    public void setMaxId(long maxId) {
        this.maxId = maxId;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder("LeafSegment(");
        sb.append("key=").append(key);
        sb.append(",maxId=").append(maxId);
        sb.append(",step=").append(step);
        sb.append(",updateTime=").append(updateTime);
        sb.append(")");
        return sb.toString();
    }
}
