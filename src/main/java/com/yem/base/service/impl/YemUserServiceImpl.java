package com.yem.base.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yem.base.dao.YemUserMapper;
import com.yem.dto.YemUserDTO;
import com.yem.entity.YemUser;

@Service
public class YemUserServiceImpl {

	@Autowired
	private YemUserMapper yemUserMapper;
	
	public YemUser getUserByCode(Long userCode) {
		return yemUserMapper.selectByUserCode(userCode);
	}
	
	public List<YemUser> getUserList(YemUserDTO yemUserDTO) {
		return yemUserMapper.selectUserList(yemUserDTO);
	}
	
	public int getUserListCount(YemUserDTO yemUserDTO) {
		return yemUserMapper.selectUserListCount(yemUserDTO);
	} 
	
	public boolean addYemUser(YemUser user) {
		return yemUserMapper.insertSelective(user) > 0 ? true : false;
	}
	
	public boolean modifyYemUser(YemUser yemUser) {
		return yemUserMapper.updateByUserCodeSelective(yemUser) > 0 ? true : false;
	}
	
}
