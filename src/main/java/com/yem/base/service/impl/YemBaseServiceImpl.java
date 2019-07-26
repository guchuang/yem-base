package com.yem.base.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yem.base.dao.YemMcCodeMapper;
import com.yem.entity.YemMcCode;

import lombok.Synchronized;

@Service
public class YemBaseServiceImpl {

	@Autowired
	private YemMcCodeMapper yemMcCodeMapper;
	
	@Synchronized
	public Long getMcCode(String mcCodeType) {
		Long code = null;
		YemMcCode mcCode = yemMcCodeMapper.selectByMcCodeType(mcCodeType);
		
		if (mcCode != null) {
			
			code = mcCode.getCurrentValue() + 1;
    	}
    	
		return code;
	}
	
	public boolean modifyMcCode(YemMcCode yemMcCode) {
		return yemMcCodeMapper.updateByPrimaryKeySelective(yemMcCode) > 0 ? true : false;
	}
	
}
