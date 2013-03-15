package com.allinpay.its.boss.system.permission.model;

import com.allinpay.its.boss.framework.annotation.Description;

public class PermissionCode {
	@Description(value="权限编码")
	private String permCode;
	@Description(value="权限名称")
	private String permName;
	
	
	public PermissionCode(){}
	
	public PermissionCode(String permCode, String permName) {
		this.permCode = permCode;
		this.permName = permName;
	}

	public String getPermCode() {
		return permCode;
	}
	public void setPermCode(String permCode) {
		this.permCode = permCode;
	}
	public String getPermName() {
		return permName;
	}
	public void setPermName(String permName) {
		this.permName = permName;
	}
	

}
