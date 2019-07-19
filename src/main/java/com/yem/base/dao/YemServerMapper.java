package com.yem.base.dao;

import com.yem.entity.YemServer;

public interface YemServerMapper {
    int deleteByPrimaryKey(String serverId);

    int insert(YemServer record);

    int insertSelective(YemServer record);

    YemServer selectByPrimaryKey(String serverId);

    int updateByPrimaryKeySelective(YemServer record);

    int updateByPrimaryKey(YemServer record);
}