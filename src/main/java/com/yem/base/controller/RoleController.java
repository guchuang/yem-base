package com.yem.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yem.base.service.impl.YemBaseServiceImpl;
import com.yem.base.service.impl.YemUserServiceImpl;
import com.yem.constant.ApiConstant;
import com.yem.constant.Constants;
import com.yem.dto.YemUserDTO;
import com.yem.entity.YemUser;
import com.yem.enums.McCodeTypeEnum;
import com.yem.request.AddYemUserRequest;
import com.yem.request.ModifyYemUserRequest;
import com.yem.request.QueryYemUserListRequest;
import com.yem.request.QueryYemUserRequest;
import com.yem.response.AddYemUserResponse;
import com.yem.response.ModifyYemUserResponse;
import com.yem.response.QueryYemUserListResponse;
import com.yem.response.QueryYemUserResponse;
import com.yem.utils.DateUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户基础功能
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2019年7月10日 下午1:53:37 <br/>
 *
 * @author <a href="mailto:hbszguchuang@163.com">guchuang</a>
 * @version 
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class RoleController {
	
	@Autowired
	private YemUserServiceImpl userService;
	
	@Autowired
	private YemBaseServiceImpl baseServiceImpl;

    @PostMapping("/query")
    public QueryYemUserResponse query(HttpServletRequest request) {
    	log.info("开始用户详解查询接口");
    	
    	QueryYemUserResponse resp = new QueryYemUserResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	QueryYemUserRequest reqModel = (QueryYemUserRequest) JSONObject.parseObject(param, 
    			QueryYemUserRequest.class);
    	
    	resp.setYemUser(userService.getUserByCode(reqModel.getUserCode()));
    	
    	return resp;
    }

    @PostMapping("/list")
    public QueryYemUserListResponse list(HttpServletRequest request) {
    	log.info("开始用户列表查询接口");
    	
    	QueryYemUserListResponse resp = new QueryYemUserListResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	QueryYemUserListRequest reqModel = (QueryYemUserListRequest) JSONObject.parseObject(param, 
    			QueryYemUserListRequest.class);
    	
    	YemUserDTO dto = new YemUserDTO();
    	dto.setUserCode(reqModel.getUserCode());
    	dto.setUserName(reqModel.getUserName());
    	dto.setEmail(reqModel.getEmail());
    	dto.setMobile(reqModel.getMobile());
    	dto.setSex(reqModel.getSex());
    	dto.setShopCode(reqModel.getShopCode());
    	dto.setValid(reqModel.getValid());
    	dto.setCreateBy(reqModel.getCreateBy());
    	dto.setCreateTime(reqModel.getCreateTime());
    	dto.setUpdateBy(reqModel.getUpdateBy());
    	dto.setUpdateTime(reqModel.getUpdateTime());
    	dto.setStart((reqModel.getPageNo() - 1) * reqModel.getPageSize());
    	dto.setPageSize(reqModel.getPageSize());
    	
    	
    	resp.setYemUsers(userService.getUserList(dto));
    	resp.setTotalPage(userService.getUserListCount(dto));
    	resp.setPageNo(reqModel.getPageNo());
    	resp.setPageSize(reqModel.getPageNo());
    	
    	return resp;
    }

    @PostMapping("/add")
    public AddYemUserResponse add(HttpServletRequest request) {
    	log.info("开始用户新增接口");
    	
    	AddYemUserResponse resp = new AddYemUserResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	AddYemUserRequest reqModel = (AddYemUserRequest) JSONObject.parseObject(param, 
    			AddYemUserRequest.class);
    	
    	YemUser user = new YemUser();
    	user.setUserCode(baseServiceImpl.getMcCode(McCodeTypeEnum.USER.toString()));
    	user.setUserName(reqModel.getUserName());
    	user.setMobile(reqModel.getMobile());
    	user.setEmail(reqModel.getEmail());
    	user.setSex(reqModel.getSex());
    	user.setShopCode(reqModel.getShopCode());
    	user.setValid(reqModel.getValid());
    	user.setCreateTime(DateUtil.getDate());
    	user.setUpdateTime(DateUtil.getDate());
    	
    	if (userService.addYemUser(user)) {
    		
        	resp.setUserCode(user.getUserCode());
    	} else {
    		
    		resp.setRespCode(Constants.RESP_BIZ_ERR_CODE);
    		resp.setRespCode(Constants.UserMsg.USER_ADD_ERROR);
    	}
    	
    	return resp;
    }

    @PostMapping("/modify")
    public ModifyYemUserResponse modify(HttpServletRequest request) {
    	log.info("开始用户修改接口");
    	
    	ModifyYemUserResponse resp = new ModifyYemUserResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	ModifyYemUserRequest reqModel = (ModifyYemUserRequest) JSONObject.parseObject(param, 
    			ModifyYemUserRequest.class);
    	
    	YemUser user = new YemUser();
    	user.setUserCode(baseServiceImpl.getMcCode(McCodeTypeEnum.USER.toString()));
    	user.setUserName(reqModel.getUserName());
    	user.setMobile(reqModel.getMobile());
    	user.setEmail(reqModel.getEmail());
    	user.setSex(reqModel.getSex());
    	user.setShopCode(reqModel.getShopCode());
    	user.setValid(reqModel.getValid());
    	user.setUpdateTime(DateUtil.getDate());
    	
    	if (!userService.modifyYemUser(user)) {
    		
    		resp.setRespCode(Constants.RESP_BIZ_ERR_CODE);
    		resp.setRespCode(Constants.UserMsg.USER_MODIFY_ERROR);
    	}
    	
    	return resp;
    }
}
