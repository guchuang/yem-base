package com.yem.base.dao;

import java.util.List;

import com.yem.dto.YemRoleDTO;
import com.yem.entity.YemRole;

public interface YemRoleMapper {
	
    int insertSelective(YemRole record);

    YemRole selectByRoleCode(Long roleCode);

    List<YemRole> selectRoleList(YemRoleDTO roleCode);

    int selectRoleListCount(YemRoleDTO roleCode);

    int updateByRoleCodeSelective(YemRole record);

}