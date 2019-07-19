package com.yem.base.dao;

import com.yem.entity.YemUserRole;

public interface YemUserRoleMapper {
    int deleteByPrimaryKey(String userRoleId);

    int insert(YemUserRole record);

    int insertSelective(YemUserRole record);

    YemUserRole selectByPrimaryKey(String userRoleId);

    int updateByPrimaryKeySelective(YemUserRole record);

    int updateByPrimaryKey(YemUserRole record);
}