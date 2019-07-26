package com.yem.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yem.base.service.impl.YemBaseServiceImpl;
import com.yem.base.service.impl.YemPermissionServiceImpl;
import com.yem.constant.ApiConstant;
import com.yem.constant.Constants;
import com.yem.dto.YemPermissionDTO;
import com.yem.entity.YemPermission;
import com.yem.enums.McCodeTypeEnum;
import com.yem.request.AddYemPermissionRequest;
import com.yem.request.ModifyYemPermissionRequest;
import com.yem.request.QueryYemPermissionListRequest;
import com.yem.request.QueryYemPermissionRequest;
import com.yem.response.AddYemPermissionResponse;
import com.yem.response.ModifyYemPermissionResponse;
import com.yem.response.QueryYemPermissionListResponse;
import com.yem.response.QueryYemPermissionResponse;
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
@RequestMapping("/permission")
@Slf4j
public class PermissionController {
	
	@Autowired
	private YemPermissionServiceImpl permissionService;
	
	@Autowired
	private YemBaseServiceImpl baseServiceImpl;

    @PostMapping("/query")
    public QueryYemPermissionResponse query(HttpServletRequest request) {
    	log.info("开始权限详解查询接口");
    	
    	QueryYemPermissionResponse resp = new QueryYemPermissionResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	QueryYemPermissionRequest reqModel = (QueryYemPermissionRequest) JSONObject.parseObject(param, 
    			QueryYemPermissionRequest.class);
    	
    	resp.setYemPermission(permissionService.getPermissionByCode(reqModel.getPermissionCode()));
    	
    	return resp;
    }

    @PostMapping("/list")
    public QueryYemPermissionListResponse list(HttpServletRequest request) {
    	log.info("开始权限列表查询接口");
    	
    	QueryYemPermissionListResponse resp = new QueryYemPermissionListResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	QueryYemPermissionListRequest reqModel = (QueryYemPermissionListRequest) JSONObject.parseObject(param, 
    			QueryYemPermissionListRequest.class);
    	
    	YemPermissionDTO dto = new YemPermissionDTO();
    	dto.setPermissionCode(reqModel.getPermissionCode());
    	dto.setPermissionName(reqModel.getPermissionName());
    	dto.setValid(reqModel.getValid());
    	dto.setCreateBy(reqModel.getCreateBy());
    	dto.setCreateTime(reqModel.getCreateTime());
    	dto.setUpdateBy(reqModel.getUpdateBy());
    	dto.setUpdateTime(reqModel.getUpdateTime());
    	dto.setStart((reqModel.getPageNo() - 1) * reqModel.getPageSize());
    	dto.setPageSize(reqModel.getPageSize());
    	
    	
    	resp.setYemPermissions(permissionService.getPermissionList(dto));
    	resp.setTotalPage(permissionService.getPermissionListCount(dto));
    	resp.setPageNo(reqModel.getPageNo());
    	resp.setPageSize(reqModel.getPageNo());
    	
    	return resp;
    }

    @PostMapping("/add")
    public AddYemPermissionResponse add(HttpServletRequest request) {
    	log.info("开始权限新增接口");
    	
    	AddYemPermissionResponse resp = new AddYemPermissionResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	AddYemPermissionRequest reqModel = (AddYemPermissionRequest) JSONObject.parseObject(param, 
    			AddYemPermissionRequest.class);
    	
    	YemPermission permission = new YemPermission();
    	permission.setPermissionCode(baseServiceImpl.getMcCode(McCodeTypeEnum.PERMISSION.toString()));
    	permission.setPermissionName(reqModel.getPermissionName());
    	permission.setValid(reqModel.getValid());
    	permission.setCreateTime(DateUtil.getDate());
    	permission.setUpdateTime(DateUtil.getDate());
    	
    	if (permissionService.addYemPermission(permission)) {
    		
        	resp.setPermissionCode(permission.getPermissionCode());
    	} else {
    		
    		resp.setRespCode(Constants.RESP_BIZ_ERR_CODE);
    		resp.setRespCode(Constants.PermissionMsg.PERMISSION_ADD_ERROR);
    	}
    	
    	return resp;
    }

    @PostMapping("/modify")
    public ModifyYemPermissionResponse modify(HttpServletRequest request) {
    	log.info("开始权限修改接口");
    	
    	ModifyYemPermissionResponse resp = new ModifyYemPermissionResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	ModifyYemPermissionRequest reqModel = (ModifyYemPermissionRequest) JSONObject.parseObject(param, 
    			ModifyYemPermissionRequest.class);
    	
    	YemPermission Permission = new YemPermission();
    	Permission.setPermissionCode(reqModel.getPermissionCode());
    	Permission.setPermissionName(reqModel.getPermissionName());
    	Permission.setValid(reqModel.getValid());
    	Permission.setUpdateTime(DateUtil.getDate());
    	
    	if (!permissionService.modifyYemPermission(Permission)) {
    		
    		resp.setRespCode(Constants.RESP_BIZ_ERR_CODE);
    		resp.setRespCode(Constants.PermissionMsg.PERMISSION_MODIFY_ERROR);
    	}
    	
    	return resp;
    }
}
