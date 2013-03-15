package com.allinpay.its.boss.system.menu.model;

import java.util.List;

import com.allinpay.its.boss.framework.repository.mybatis.model.MyBatisBaseModel;
import com.allinpay.its.boss.system.permission.model.FrameworkSysPermission;

import org.apache.ibatis.type.Alias;

@Alias(value = "sysMenu")
public class FrameworkSysMenu extends MyBatisBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 516200946295293781L;
	
	private Long id;
	private Long pid;
	private java.lang.String sys_menu_code;
	private java.lang.String sys_menu_name;
	private java.lang.String sys_menu_url;
	private java.lang.String is_leaf;
	private java.lang.String sys_menu_description;
	private java.lang.String state;
	private java.lang.String remark;
	private Long create_user_id;
	private java.util.Date create_time;
	private Long modify_user_id;
	private java.util.Date modify_time;
	private Long version;
	private Long order_index;
	
	List<FrameworkSysPermission> listPermission;
	
	public FrameworkSysMenu() {}
	
	public FrameworkSysMenu(Long pid) {
		this.pid = pid;
	}
	private FrameworkSysMenu pmenu;
	
	private FrameworkSysMenu ppmenu;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public java.lang.String getSys_menu_code() {
		return sys_menu_code;
	}
	public void setSys_menu_code(java.lang.String sys_menu_code) {
		this.sys_menu_code = sys_menu_code;
	}
	public java.lang.String getSys_menu_name() {
		return sys_menu_name;
	}
	public void setSys_menu_name(java.lang.String sys_menu_name) {
		this.sys_menu_name = sys_menu_name;
	}
	public java.lang.String getSys_menu_url() {
		return sys_menu_url;
	}
	public void setSys_menu_url(java.lang.String sys_menu_url) {
		this.sys_menu_url = sys_menu_url;
	}
	public java.lang.String getIs_leaf() {
		return is_leaf;
	}
	public void setIs_leaf(java.lang.String is_leaf) {
		this.is_leaf = is_leaf;
	}
	public java.lang.String getSys_menu_description() {
		return sys_menu_description;
	}
	public void setSys_menu_description(java.lang.String sys_menu_description) {
		this.sys_menu_description = sys_menu_description;
	}
	public java.lang.String getState() {
		return state;
	}
	public void setState(java.lang.String state) {
		this.state = state;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public Long getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(Long create_user_id) {
		this.create_user_id = create_user_id;
	}
	public java.util.Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(java.util.Date create_time) {
		this.create_time = create_time;
	}
	public Long getModify_user_id() {
		return modify_user_id;
	}
	public void setModify_user_id(Long modify_user_id) {
		this.modify_user_id = modify_user_id;
	}
	public java.util.Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(java.util.Date modify_time) {
		this.modify_time = modify_time;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Long getOrder_index() {
		return order_index;
	}
	public void setOrder_index(Long order_index) {
		this.order_index = order_index;
	}
	public FrameworkSysMenu getPmenu() {
		return pmenu;
	}
	public void setPmenu(FrameworkSysMenu pmenu) {
		this.pmenu = pmenu;
	}
	public FrameworkSysMenu getPpmenu() {
		return ppmenu;
	}
	public void setPpmenu(FrameworkSysMenu ppmenu) {
		this.ppmenu = ppmenu;
	}

	public List<FrameworkSysPermission> getListPermission() {
		return listPermission;
	}

	public void setListPermission(List<FrameworkSysPermission> listPermission) {
		this.listPermission = listPermission;
	}

}
