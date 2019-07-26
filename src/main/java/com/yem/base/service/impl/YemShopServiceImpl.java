package com.yem.base.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yem.base.dao.YemShopMapper;
import com.yem.dto.YemShopDTO;
import com.yem.entity.YemShop;

@Service
public class YemShopServiceImpl {

	@Autowired
	private YemShopMapper yemShopMapper;
	
	public YemShop getShopByCode(Long shopCode) {
		return yemShopMapper.selectByShopCode(shopCode);
	}
	
	public List<YemShop> getShopList(YemShopDTO yemShopDTO) {
		return yemShopMapper.selectShopList(yemShopDTO);
	}
	
	public int getShopListCount(YemShopDTO yemShopDTO) {
		return yemShopMapper.selectShopListCount(yemShopDTO);
	} 
	
	public boolean addYemShop(YemShop Shop) {
		return yemShopMapper.insertSelective(Shop) > 0 ? true : false;
	}
	
	public boolean modifyYemShop(YemShop yemShop) {
		return yemShopMapper.updateByShopCodeSelective(yemShop) > 0 ? true : false;
	}
	
}
