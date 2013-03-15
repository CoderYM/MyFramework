package com.allinpay.its.boss.system.permission.model;

import com.allinpay.its.boss.framework.annotation.Description;

/**
 * @ClassName: ActionCheck
 * @Description: TODO
 * @author yangmin
 * @date 2012-9-26 下午04:46:51
 */
public class ActionCheck {
	@Description(value="是否通过")
	private boolean isAccess;
	@Description(value="描述")
	private String actionDesc;
	
	public ActionCheck(){}
	
	public ActionCheck(boolean isAccess, String actionDesc) {
		this.isAccess = isAccess;
		this.actionDesc = actionDesc;
	}

	public boolean isAccess() {
		return isAccess;
	}
	public void setAccess(boolean isAccess) {
		this.isAccess = isAccess;
	}
	public String getActionDesc() {
		return actionDesc;
	}
	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}
	

}
