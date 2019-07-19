package com.yem.base.dao;

import com.yem.entity.YemShop;

public interface YemShopMapper {
    int deleteByPrimaryKey(String shopId);

    int insert(YemShop record);

    int insertSelective(YemShop record);

    YemShop selectByPrimaryKey(String shopId);

    int updateByPrimaryKeySelective(YemShop record);

    int updateByPrimaryKey(YemShop record);
}