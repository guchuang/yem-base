package com.yem.base.dao;

import com.yem.entity.YemRolePermission;

public interface YemRolePermissionMapper {
    int deleteByPrimaryKey(String rolePermissionId);

    int insert(YemRolePermission record);

    int insertSelective(YemRolePermission record);

    YemRolePermission selectByPrimaryKey(String rolePermissionId);

    int updateByPrimaryKeySelective(YemRolePermission record);

    int updateByPrimaryKey(YemRolePermission record);
}