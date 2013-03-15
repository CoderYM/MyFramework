/* @(#) SessionUtil.java
 * Copyright(c) 2008 ALLINPAY. All Right Reserver.
 */
package com.allinpay.its.boss.framework.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;


/**
 * session控制类
 * 
 */
public class SessionUtil {
	
	//超级用户
	
	/**
	 * 用户MAP
	 */
	public static final String USER_MAP = "user_map";

	public static final String USER_FORM = "user_form";

	public static final String USER_FORM_NAME = "user_form_name";

	public static final String USER_URL = "user_url";
	/**
	 * 登录名
	 */
	public static final String USER_NAME = "user_name";
	/**
	 * 角色
	 */
	public static final String USER_ROEL = "user_role";
	/**
	 * 用户权限
	 */
	public static final String USER_FUNCATIONS = "user_functions";
	/**
	 * 真实姓名
	 */
	public static final String USER_REAL_NAME = "real_name";
	/**
	 * 用户内部唯一标示ID
	 */
	public static final String USER_ID = "user_id";
	/**
	 * 浏览器类型
	 */
	public static final String BROWSER_TYPE = "browser_type";

	/**
	 * 客户端IP地址
	 */
	public static final String USER_IP = "user_ip";
	
	/**
	 * 超级用户列表
	 */
	public static final String SUPPERUSERS = "admin";
	
	public static final String USER_PERM_CODE_LIST="user_perm_code_list";
	
	//获得当前登录用户名
	public static String getLoginUserName(Map<String, Object> userInfMap){
		String loginUserName = null;
		if(userInfMap != null){
			loginUserName = (String) userInfMap.get(USER_NAME);
			if(StringUtils.isNotBlank(loginUserName)){
				return loginUserName;
			}
		}
		return loginUserName;
	}
	//获得当前登录用户ID
	public static Long getLoginUserId(Map<String, Object> userInfMap){
		Long loginUserId = null;
		if(userInfMap != null){
			loginUserId = (Long) userInfMap.get(USER_ID);
			if(loginUserId != null){
				return loginUserId;
			}
		}
		return loginUserId;
	}
	
}