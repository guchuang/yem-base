package com.yem.base.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yem.base.dao.YemRoleMapper;
import com.yem.dto.YemRoleDTO;
import com.yem.entity.YemRole;

@Service
public class YemRoleServiceImpl {

	@Autowired
	private YemRoleMapper yemRoleMapper;
	
	public YemRole getUserByCode(Long userCode) {
		return yemRoleMapper.selectByRoleCode(userCode);
	}
	
	public List<YemRole> getUserList(YemRoleDTO yemRoleDTO) {
		return yemRoleMapper.selectRoleList(yemRoleDTO);
	}
	
	public int getUserListCount(YemRoleDTO yemRoleDTO) {
		return yemRoleMapper.selectRoleListCount(yemRoleDTO);
	} 
	
	public boolean addYemRole(YemRole role) {
		return yemRoleMapper.insertSelective(role) > 0 ? true : false;
	}
	
	public boolean modifyYemRole(YemRole yemRole) {
		return yemRoleMapper.updateByRoleCodeSelective(yemRole) > 0 ? true : false;
	}
	
}
