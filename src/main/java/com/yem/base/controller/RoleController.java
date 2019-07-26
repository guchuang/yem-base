package com.yem.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yem.base.service.impl.YemBaseServiceImpl;
import com.yem.base.service.impl.YemRoleServiceImpl;
import com.yem.constant.ApiConstant;
import com.yem.constant.Constants;
import com.yem.dto.YemRoleDTO;
import com.yem.entity.YemRole;
import com.yem.enums.McCodeTypeEnum;
import com.yem.request.AddYemRoleRequest;
import com.yem.request.ModifyYemRoleRequest;
import com.yem.request.QueryYemRoleListRequest;
import com.yem.request.QueryYemRoleRequest;
import com.yem.response.AddYemRoleResponse;
import com.yem.response.ModifyYemRoleResponse;
import com.yem.response.QueryYemRoleListResponse;
import com.yem.response.QueryYemRoleResponse;
import com.yem.utils.DateUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 角色基础功能
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2019年7月10日 下午1:53:37 <br/>
 *
 * @author <a href="mailto:hbszguchuang@163.com">guchuang</a>
 * @version 
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {
	
	@Autowired
	private YemRoleServiceImpl roleService;
	
	@Autowired
	private YemBaseServiceImpl baseServiceImpl;

    @PostMapping("/query")
    public QueryYemRoleResponse query(HttpServletRequest request) {
    	log.info("开始角色详解查询接口");
    	
    	QueryYemRoleResponse resp = new QueryYemRoleResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	QueryYemRoleRequest reqModel = (QueryYemRoleRequest) JSONObject.parseObject(param, 
    			QueryYemRoleRequest.class);
    	
    	resp.setYemRole(roleService.getRoleByCode(reqModel.getRoleCode()));
    	
    	return resp;
    }

    @PostMapping("/list")
    public QueryYemRoleListResponse list(HttpServletRequest request) {
    	log.info("开始角色列表查询接口");
    	
    	QueryYemRoleListResponse resp = new QueryYemRoleListResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	QueryYemRoleListRequest reqModel = (QueryYemRoleListRequest) JSONObject.parseObject(param, 
    			QueryYemRoleListRequest.class);
    	
    	YemRoleDTO dto = new YemRoleDTO();
    	dto.setRoleCode(reqModel.getRoleCode());
    	dto.setRoleName(reqModel.getRoleName());
    	dto.setValid(reqModel.getValid());
    	dto.setCreateBy(reqModel.getCreateBy());
    	dto.setCreateTime(reqModel.getCreateTime());
    	dto.setUpdateBy(reqModel.getUpdateBy());
    	dto.setUpdateTime(reqModel.getUpdateTime());
    	dto.setStart((reqModel.getPageNo() - 1) * reqModel.getPageSize());
    	dto.setPageSize(reqModel.getPageSize());
    	
    	
    	resp.setYemRoles(roleService.getRoleList(dto));
    	resp.setTotalPage(roleService.getRoleListCount(dto));
    	resp.setPageNo(reqModel.getPageNo());
    	resp.setPageSize(reqModel.getPageNo());
    	
    	return resp;
    }

    @PostMapping("/add")
    public AddYemRoleResponse add(HttpServletRequest request) {
    	log.info("开始角色新增接口");
    	
    	AddYemRoleResponse resp = new AddYemRoleResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	AddYemRoleRequest reqModel = (AddYemRoleRequest) JSONObject.parseObject(param, 
    			AddYemRoleRequest.class);
    	
    	YemRole role = new YemRole();
    	role.setRoleCode(baseServiceImpl.getMcCode(McCodeTypeEnum.ROLE.toString()));
    	role.setRoleName(reqModel.getRoleName());
    	role.setValid(reqModel.getValid());
    	role.setCreateTime(DateUtil.getDate());
    	role.setUpdateTime(DateUtil.getDate());
    	
    	if (roleService.addYemRole(role)) {
    		
        	resp.setRoleCode(role.getRoleCode());
    	} else {
    		
    		resp.setRespCode(Constants.RESP_BIZ_ERR_CODE);
    		resp.setRespCode(Constants.RoleMsg.ROLE_ADD_ERROR);
    	}
    	
    	return resp;
    }

    @PostMapping("/modify")
    public ModifyYemRoleResponse modify(HttpServletRequest request) {
    	log.info("开始角色修改接口");
    	
    	ModifyYemRoleResponse resp = new ModifyYemRoleResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	ModifyYemRoleRequest reqModel = (ModifyYemRoleRequest) JSONObject.parseObject(param, 
    			ModifyYemRoleRequest.class);
    	
    	YemRole role = new YemRole();
    	role.setRoleCode(reqModel.getRoleCode());
    	role.setRoleName(reqModel.getRoleName());
    	role.setValid(reqModel.getValid());
    	role.setUpdateTime(DateUtil.getDate());
    	
    	if (!roleService.modifyYemRole(role)) {
    		
    		resp.setRespCode(Constants.RESP_BIZ_ERR_CODE);
    		resp.setRespCode(Constants.RoleMsg.ROLE_MODIFY_ERROR);
    	}
    	
    	return resp;
    }
}
