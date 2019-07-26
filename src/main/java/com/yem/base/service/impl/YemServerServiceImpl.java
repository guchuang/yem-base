package com.yem.base.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yem.base.dao.YemServerMapper;
import com.yem.dto.YemServerDTO;
import com.yem.entity.YemServer;

@Service
public class YemServerServiceImpl {

	@Autowired
	private YemServerMapper yemServerMapper;
	
	public YemServer getServerByCode(Long serverCode) {
		return yemServerMapper.selectByServerCode(serverCode);
	}
	
	public List<YemServer> getServerList(YemServerDTO yemServerDTO) {
		return yemServerMapper.selectServerList(yemServerDTO);
	}
	
	public int getServerListCount(YemServerDTO yemServerDTO) {
		return yemServerMapper.selectServerListCount(yemServerDTO);
	} 
	
	public boolean addYemServer(YemServer Server) {
		return yemServerMapper.insertSelective(Server) > 0 ? true : false;
	}
	
	public boolean modifyYemServer(YemServer yemServer) {
		return yemServerMapper.updateByServerCodeSelective(yemServer) > 0 ? true : false;
	}
	
}
