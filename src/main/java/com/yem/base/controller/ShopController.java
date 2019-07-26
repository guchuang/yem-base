package com.yem.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yem.base.service.impl.YemBaseServiceImpl;
import com.yem.base.service.impl.YemShopServiceImpl;
import com.yem.constant.ApiConstant;
import com.yem.constant.Constants;
import com.yem.dto.YemShopDTO;
import com.yem.entity.YemShop;
import com.yem.enums.McCodeTypeEnum;
import com.yem.request.AddYemShopRequest;
import com.yem.request.ModifyYemShopRequest;
import com.yem.request.QueryYemShopListRequest;
import com.yem.request.QueryYemShopRequest;
import com.yem.response.AddYemShopResponse;
import com.yem.response.ModifyYemShopResponse;
import com.yem.response.QueryYemShopListResponse;
import com.yem.response.QueryYemShopResponse;
import com.yem.utils.DateUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 权限基础功能
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2019年7月10日 下午1:53:37 <br/>
 *
 * @author <a href="mailto:hbszguchuang@163.com">guchuang</a>
 * @version 
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/shop")
@Slf4j
public class ShopController {
	
	@Autowired
	private YemShopServiceImpl shopService;
	
	@Autowired
	private YemBaseServiceImpl baseServiceImpl;

    @PostMapping("/query")
    public QueryYemShopResponse query(HttpServletRequest request) {
    	log.info("开始权限详解查询接口");
    	
    	QueryYemShopResponse resp = new QueryYemShopResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	QueryYemShopRequest reqModel = (QueryYemShopRequest) JSONObject.parseObject(param, 
    			QueryYemShopRequest.class);
    	
    	resp.setYemShop(shopService.getShopByCode(reqModel.getShopCode()));
    	
    	return resp;
    }

    @PostMapping("/list")
    public QueryYemShopListResponse list(HttpServletRequest request) {
    	log.info("开始权限列表查询接口");
    	
    	QueryYemShopListResponse resp = new QueryYemShopListResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	QueryYemShopListRequest reqModel = (QueryYemShopListRequest) JSONObject.parseObject(param, 
    			QueryYemShopListRequest.class);
    	
    	YemShopDTO dto = new YemShopDTO();
    	dto.setShopCode(reqModel.getShopCode());
    	dto.setShopName(reqModel.getShopName());
    	dto.setValid(reqModel.getValid());
    	dto.setCreateBy(reqModel.getCreateBy());
    	dto.setCreateTime(reqModel.getCreateTime());
    	dto.setUpdateBy(reqModel.getUpdateBy());
    	dto.setUpdateTime(reqModel.getUpdateTime());
    	dto.setStart((reqModel.getPageNo() - 1) * reqModel.getPageSize());
    	dto.setPageSize(reqModel.getPageSize());
    	
    	
    	resp.setYemShops(shopService.getShopList(dto));
    	resp.setTotalPage(shopService.getShopListCount(dto));
    	resp.setPageNo(reqModel.getPageNo());
    	resp.setPageSize(reqModel.getPageNo());
    	
    	return resp;
    }

    @PostMapping("/add")
    public AddYemShopResponse add(HttpServletRequest request) {
    	log.info("开始权限新增接口");
    	
    	AddYemShopResponse resp = new AddYemShopResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	AddYemShopRequest reqModel = (AddYemShopRequest) JSONObject.parseObject(param, 
    			AddYemShopRequest.class);
    	
    	YemShop shop = new YemShop();
    	shop.setShopCode(baseServiceImpl.getMcCode(McCodeTypeEnum.SHOP.toString()));
    	shop.setShopName(reqModel.getShopName());
    	shop.setValid(reqModel.getValid());
    	shop.setCreateTime(DateUtil.getDate());
    	shop.setUpdateTime(DateUtil.getDate());
    	
    	if (shopService.addYemShop(shop)) {
    		
        	resp.setShopCode(shop.getShopCode());
    	} else {
    		
    		resp.setRespCode(Constants.RESP_BIZ_ERR_CODE);
    		resp.setRespCode(Constants.ShopMsg.SHOP_ADD_ERROR);
    	}
    	
    	return resp;
    }

    @PostMapping("/modify")
    public ModifyYemShopResponse modify(HttpServletRequest request) {
    	log.info("开始权限修改接口");
    	
    	ModifyYemShopResponse resp = new ModifyYemShopResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	ModifyYemShopRequest reqModel = (ModifyYemShopRequest) JSONObject.parseObject(param, 
    			ModifyYemShopRequest.class);
    	
    	YemShop shop = new YemShop();
    	shop.setShopCode(reqModel.getShopCode());
    	shop.setShopName(reqModel.getShopName());
    	shop.setValid(reqModel.getValid());
    	shop.setUpdateTime(DateUtil.getDate());
    	
    	if (!shopService.modifyYemShop(shop)) {
    		
    		resp.setRespCode(Constants.RESP_BIZ_ERR_CODE);
    		resp.setRespCode(Constants.ShopMsg.SHOP_MODIFY_ERROR);
    	}
    	
    	return resp;
    }
}
