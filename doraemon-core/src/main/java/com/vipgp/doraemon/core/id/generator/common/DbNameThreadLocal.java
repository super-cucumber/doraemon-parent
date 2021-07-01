package com.vipgp.doraemon.core.id.generator.common;

/**
 * @author: linshangdou@gmail.com
 * @date: 2021/1/22 16:03
 */
public class DbNameThreadLocal {

    private final static ThreadLocal<String> currentDb=new ThreadLocal<>();

    private DbNameThreadLocal(){}

    private static class DbNameHolder{
        private static DbNameThreadLocal instance=new DbNameThreadLocal();
    }

    public final static DbNameThreadLocal getSinglton(){
        return DbNameHolder.instance;
    }

    public void clear(){
        currentDb.remove();
    }

    public void set(String value){
        currentDb.set(value);
    }

    public String get(){
        return currentDb.get();
    }
}
