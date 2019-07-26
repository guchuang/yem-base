package com.yem.base.dao;

import java.util.List;

import com.yem.dto.YemPermissionDTO;
import com.yem.entity.YemPermission;

public interface YemPermissionMapper {

    int insertSelective(YemPermission record);

    YemPermission selectByPermissionCode(Long permissionCode);

    List<YemPermission> selectPermissionList(YemPermissionDTO record);

    int selectPermissionListCount(YemPermissionDTO record);

    int updateByPermissionCodeSelective(YemPermission record);

}