/*
 * 版权声明 .
 * 此文档的版权归通联支付网络服务有限公司所有
 * Powered By [Allinpay-Boss-framework]
 */

package com.allinpay.its.boss.system.permission.model;

import javax.persistence.Table;

import com.allinpay.its.boss.framework.annotation.Description;
import com.allinpay.its.boss.framework.repository.mybatis.model.MyBatisBaseModel;

import org.apache.ibatis.type.Alias;

@Table(name="FRAMEWORK_SYS_ACTION")
@Alias(value="FrameworkSysAction")
public class FrameworkSysAction extends  MyBatisBaseModel {
	private static final long serialVersionUID = -45811662L;
	
	
	//columns START
	/** 变量 id . */
	@Description(value="编号")
	private Long id;
	/** 变量 sysMenuId . */
	@Description(value="菜单ID")
	private Long sysMenuId;
	/** 变量 permissionCode . */
	@Description(value="权限编码")
	private java.lang.String permissionCode;
	/** 变量 actionName . */
	@Description(value="访问的action名字")
	private java.lang.String actionName;
	/** 变量 methodName . */
	@Description(value="访问的method名字")
	private java.lang.String methodName;
	/** 变量 simpleClassName . */
	@Description(value="访问的class名字")
	private java.lang.String simpleClassName;
	/** 变量 actionType . */
	@Description(value="类型")
	private java.lang.String actionType;
	/** 变量 actionDes . */
	@Description(value="操作描述")
	private java.lang.String actionDes;
	//columns END

	/**
	* FrameworkSysAction 的构造函数
	*/
	public FrameworkSysAction() {
	}
	/**
	* FrameworkSysAction 的构造函数
	*/
	public FrameworkSysAction(
		Long id
	) {
		this.id = id;
	}


	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public Long getPk() {
		return this.id;
	}

	public void setSysMenuId(Long value) {
		this.sysMenuId = value;
	}
	
	public Long getSysMenuId() {
		return this.sysMenuId;
	}
	

	public void setPermissionCode(java.lang.String value) {
		this.permissionCode = value;
	}
	
	public java.lang.String getPermissionCode() {
		return this.permissionCode;
	}
	

	public void setActionName(java.lang.String value) {
		this.actionName = value;
	}
	
	public java.lang.String getActionName() {
		return this.actionName;
	}
	

	public void setMethodName(java.lang.String value) {
		this.methodName = value;
	}
	
	public java.lang.String getMethodName() {
		return this.methodName;
	}
	

	public void setSimpleClassName(java.lang.String value) {
		this.simpleClassName = value;
	}
	
	public java.lang.String getSimpleClassName() {
		return this.simpleClassName;
	}
	

	public void setActionType(java.lang.String value) {
		this.actionType = value;
	}
	
	public java.lang.String getActionType() {
		return this.actionType;
	}
	

	public void setActionDes(java.lang.String value) {
		this.actionDes = value;
	}
	
	public java.lang.String getActionDes() {
		return this.actionDes;
	}
	

}

