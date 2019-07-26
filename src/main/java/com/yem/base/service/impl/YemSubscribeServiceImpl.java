package com.yem.base.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yem.base.dao.YemSubscribeMapper;
import com.yem.dto.YemSubscribeDTO;
import com.yem.entity.YemSubscribe;

@Service
public class YemSubscribeServiceImpl {

	@Autowired
	private YemSubscribeMapper yemSubscribeMapper;
	
	public YemSubscribe getSubscribeByCode(Long SubscribeCode) {
		return yemSubscribeMapper.selectBySubscribeCode(SubscribeCode);
	}
	
	public List<YemSubscribe> getSubscribeList(YemSubscribeDTO yemSubscribeDTO) {
		return yemSubscribeMapper.selectSubscribeList(yemSubscribeDTO);
	}
	
	public int getSubscribeListCount(YemSubscribeDTO yemSubscribeDTO) {
		return yemSubscribeMapper.selectSubscribeListCount(yemSubscribeDTO);
	} 
	
	public boolean addYemSubscribe(YemSubscribe Subscribe) {
		return yemSubscribeMapper.insertSelective(Subscribe) > 0 ? true : false;
	}
	
	public boolean modifyYemSubscribe(YemSubscribe yemSubscribe) {
		return yemSubscribeMapper.updateBySubscribeCodeSelective(yemSubscribe) > 0 ? true : false;
	}
	
}
