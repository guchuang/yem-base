package com.yem.base.dao;

import com.yem.entity.YemPermission;

public interface YemPermissionMapper {
    int deleteByPrimaryKey(String permissionId);

    int insert(YemPermission record);

    int insertSelective(YemPermission record);

    YemPermission selectByPrimaryKey(String permissionId);

    int updateByPrimaryKeySelective(YemPermission record);

    int updateByPrimaryKey(YemPermission record);
}