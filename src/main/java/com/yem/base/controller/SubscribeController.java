package com.yem.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yem.base.service.impl.YemBaseServiceImpl;
import com.yem.base.service.impl.YemSubscribeServiceImpl;
import com.yem.constant.ApiConstant;
import com.yem.constant.Constants;
import com.yem.dto.YemSubscribeDTO;
import com.yem.entity.YemSubscribe;
import com.yem.enums.McCodeTypeEnum;
import com.yem.request.AddYemSubscribeRequest;
import com.yem.request.ModifyYemSubscribeRequest;
import com.yem.request.QueryYemSubscribeListRequest;
import com.yem.request.QueryYemSubscribeRequest;
import com.yem.response.AddYemSubscribeResponse;
import com.yem.response.ModifyYemSubscribeResponse;
import com.yem.response.QueryYemSubscribeListResponse;
import com.yem.response.QueryYemSubscribeResponse;
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
@RequestMapping("/subscribe")
@Slf4j
public class SubscribeController {
	
	@Autowired
	private YemSubscribeServiceImpl subscribeService;
	
	@Autowired
	private YemBaseServiceImpl baseServiceImpl;

    @PostMapping("/query")
    public QueryYemSubscribeResponse query(HttpServletRequest request) {
    	log.info("开始权限详解查询接口");
    	
    	QueryYemSubscribeResponse resp = new QueryYemSubscribeResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	QueryYemSubscribeRequest reqModel = (QueryYemSubscribeRequest) JSONObject.parseObject(param, 
    			QueryYemSubscribeRequest.class);
    	
    	resp.setYemSubscribe(subscribeService.getSubscribeByCode(reqModel.getSubscribeCode()));
    	
    	return resp;
    }

    @PostMapping("/list")
    public QueryYemSubscribeListResponse list(HttpServletRequest request) {
    	log.info("开始权限列表查询接口");
    	
    	QueryYemSubscribeListResponse resp = new QueryYemSubscribeListResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	QueryYemSubscribeListRequest reqModel = (QueryYemSubscribeListRequest) JSONObject.parseObject(param, 
    			QueryYemSubscribeListRequest.class);
    	
    	YemSubscribeDTO dto = new YemSubscribeDTO();
    	dto.setSubscribeCode(reqModel.getSubscribeCode());
    	dto.setMemberCode(reqModel.getMemberCode());
    	dto.setServerCode(reqModel.getServerCode());
    	dto.setShopCode(reqModel.getShopCode());
    	dto.setSubscribeTime(DateUtil.formatStrToDate(reqModel.getSubscribeTime(), DateUtil.STD_DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
    	dto.setValid(reqModel.getValid());
    	dto.setCreateBy(reqModel.getCreateBy());
    	dto.setCreateTime(reqModel.getCreateTime());
    	dto.setUpdateBy(reqModel.getUpdateBy());
    	dto.setUpdateTime(reqModel.getUpdateTime());
    	dto.setStart((reqModel.getPageNo() - 1) * reqModel.getPageSize());
    	dto.setPageSize(reqModel.getPageSize());
    	
    	
    	resp.setYemSubscribes(subscribeService.getSubscribeList(dto));
    	resp.setTotalPage(subscribeService.getSubscribeListCount(dto));
    	resp.setPageNo(reqModel.getPageNo());
    	resp.setPageSize(reqModel.getPageNo());
    	
    	return resp;
    }

    @PostMapping("/add")
    public AddYemSubscribeResponse add(HttpServletRequest request) {
    	log.info("开始权限新增接口");
    	
    	AddYemSubscribeResponse resp = new AddYemSubscribeResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	AddYemSubscribeRequest reqModel = (AddYemSubscribeRequest) JSONObject.parseObject(param, 
    			AddYemSubscribeRequest.class);
    	
    	YemSubscribe dto = new YemSubscribe();
    	dto.setSubscribeCode(baseServiceImpl.getMcCode(McCodeTypeEnum.SUBSCRIBE.toString()));
    	dto.setSubscribeCode(reqModel.getSubscribeCode());
    	dto.setMemberCode(reqModel.getMemberCode());
    	dto.setServerCode(reqModel.getServerCode());
    	dto.setShopCode(reqModel.getShopCode());
    	dto.setSubscribeTime(DateUtil.formatStrToDate(reqModel.getSubscribeTime(), DateUtil.STD_DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
    	dto.setValid(reqModel.getValid());
    	dto.setCreateTime(DateUtil.getDate());
    	dto.setUpdateTime(DateUtil.getDate());
    	
    	if (subscribeService.addYemSubscribe(dto)) {
    		
        	resp.setSubscribeCode(dto.getSubscribeCode());
    	} else {
    		
    		resp.setRespCode(Constants.RESP_BIZ_ERR_CODE);
    		resp.setRespCode(Constants.SubscribeMsg.SUBSCRIBE_ADD_ERROR);
    	}
    	
    	return resp;
    }

    @PostMapping("/modify")
    public ModifyYemSubscribeResponse modify(HttpServletRequest request) {
    	log.info("开始权限修改接口");
    	
    	ModifyYemSubscribeResponse resp = new ModifyYemSubscribeResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	ModifyYemSubscribeRequest reqModel = (ModifyYemSubscribeRequest) JSONObject.parseObject(param, 
    			ModifyYemSubscribeRequest.class);
    	
    	YemSubscribe dto = new YemSubscribe();

    	dto.setSubscribeCode(reqModel.getSubscribeCode());
    	dto.setMemberCode(reqModel.getMemberCode());
    	dto.setServerCode(reqModel.getServerCode());
    	dto.setShopCode(reqModel.getShopCode());
    	dto.setSubscribeTime(DateUtil.formatStrToDate(reqModel.getSubscribeTime(), DateUtil.STD_DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
    	dto.setValid(reqModel.getValid());
    	dto.setUpdateTime(DateUtil.getDate());
    	
    	if (!subscribeService.modifyYemSubscribe(dto)) {
    		
    		resp.setRespCode(Constants.RESP_BIZ_ERR_CODE);
    		resp.setRespCode(Constants.SubscribeMsg.SUBSCRIBE_MODIFY_ERROR);
    	}
    	
    	return resp;
    }
}
