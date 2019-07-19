package com.yem.base.dao;

import com.yem.entity.YemSubscribe;

public interface YemSubscribeMapper {
    int deleteByPrimaryKey(String subscribeId);

    int insert(YemSubscribe record);

    int insertSelective(YemSubscribe record);

    YemSubscribe selectByPrimaryKey(String subscribeId);

    int updateByPrimaryKeySelective(YemSubscribe record);

    int updateByPrimaryKey(YemSubscribe record);
}