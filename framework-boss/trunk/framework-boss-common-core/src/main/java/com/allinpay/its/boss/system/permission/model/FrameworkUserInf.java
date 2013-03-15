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

@Table(name="FRAMEWORK_USER_INF")
@Alias(value="FrameworkUserInf")
public class FrameworkUserInf extends  MyBatisBaseModel {
	private static final long serialVersionUID = -1551148691L;
	
	/** 变量 ROW_CRT_TS . */
	@Description(value="创建时间开始")
	private String rowCrtTsBegin;
	@Description(value="创建时间结束")
	private String rowCrtTsEnd;
	/** 变量 REC_UPD_TS . */
	@Description(value="最后更新时间开始")
	private String recUpdTsBegin;
	@Description(value="最后更新时间结束")
	private String recUpdTsEnd;
	
	//columns START
	/** 变量 id . */
	@Description(value="<seq>")
	private Long id;
	/** 变量 userName . */
	@Description(value="操作员名称")
	private java.lang.String userName;
	/** 变量 userPassword . */
	@Description(value="操作员密码")
	private java.lang.String userPassword;
	/** 变量 state . */
	@Description(value="当前状态")
	private java.lang.String state;
	/** 变量 userInstId . */
	@Description(value="USER_INST_ID")
	private java.lang.String userInstId;
	/** 变量 realName . */
	@Description(value="操作员真实姓名")
	private java.lang.String realName;
	/** 变量 mobile . */
	@Description(value="手机号码")
	private java.lang.String mobile;
	/** 变量 contactPhone . */
	@Description(value="座机号码")
	private java.lang.String contactPhone;
	/** 变量 contactEmail . */
	@Description(value="邮件地址")
	private java.lang.String contactEmail;
	/** 变量 attributiveOrg . */
	@Description(value="归属机构")
	private Long attributiveOrg;
	/** 变量 misc1 . */
	@Description(value="扩展1")
	private java.lang.String misc1;
	/** 变量 misc2 . */
	@Description(value="扩展2")
	private java.lang.String misc2;
	/** 变量 userDesc . */
	@Description(value="操作员描述")
	private java.lang.String userDesc;
	/** 变量 recUpdUsr . */
	@Description(value="最后更新者")
	private java.lang.String recUpdUsr;
	/** 变量 rowCrtTs . */
	@Description(value="创建时间")
	private java.util.Date rowCrtTs;
	/** 变量 recUpdTs . */
	@Description(value="最后更新时间")
	private java.util.Date recUpdTs;
	
	private String[] roleIds;
	//columns END

	/**
	* FrameworkUserInf 的构造函数
	*/
	public FrameworkUserInf() {
	}
	/**
	* FrameworkUserInf 的构造函数
	*/
	public FrameworkUserInf(
		Long id
	) {
		this.id = id;
	}


	public FrameworkUserInf(String userName, String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
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

	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	

	public void setUserPassword(java.lang.String value) {
		this.userPassword = value;
	}
	
	public java.lang.String getUserPassword() {
		return this.userPassword;
	}
	

	public void setState(java.lang.String value) {
		this.state = value;
	}
	
	public java.lang.String getState() {
		return this.state;
	}
	

	public void setUserInstId(java.lang.String value) {
		this.userInstId = value;
	}
	
	public java.lang.String getUserInstId() {
		return this.userInstId;
	}
	

	public void setRealName(java.lang.String value) {
		this.realName = value;
	}
	
	public java.lang.String getRealName() {
		return this.realName;
	}
	

	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	

	public void setContactPhone(java.lang.String value) {
		this.contactPhone = value;
	}
	
	public java.lang.String getContactPhone() {
		return this.contactPhone;
	}
	

	public void setContactEmail(java.lang.String value) {
		this.contactEmail = value;
	}
	
	public java.lang.String getContactEmail() {
		return this.contactEmail;
	}
	

	public void setAttributiveOrg(Long value) {
		this.attributiveOrg = value;
	}
	
	public Long getAttributiveOrg() {
		return this.attributiveOrg;
	}
	

	public void setMisc1(java.lang.String value) {
		this.misc1 = value;
	}
	
	public java.lang.String getMisc1() {
		return this.misc1;
	}
	

	public void setMisc2(java.lang.String value) {
		this.misc2 = value;
	}
	
	public java.lang.String getMisc2() {
		return this.misc2;
	}
	

	public void setUserDesc(java.lang.String value) {
		this.userDesc = value;
	}
	
	public java.lang.String getUserDesc() {
		return this.userDesc;
	}
	

	public void setRecUpdUsr(java.lang.String value) {
		this.recUpdUsr = value;
	}
	
	public java.lang.String getRecUpdUsr() {
		return this.recUpdUsr;
	}
	
		public void setRowCrtTsBegin(String value) {
			this.rowCrtTsBegin = value;
		}
		
		public String getRowCrtTsBegin() {
			return this.rowCrtTsBegin;
		}
		public void setRowCrtTsEnd(String value) {
			this.rowCrtTsEnd = value;
		}
		
		public String getRowCrtTsEnd() {
			return this.rowCrtTsEnd;
		}

	public void setRowCrtTs(java.util.Date value) {
		this.rowCrtTs = value;
	}
	
	public java.util.Date getRowCrtTs() {
		return this.rowCrtTs;
	}
	
		public void setRecUpdTsBegin(String value) {
			this.recUpdTsBegin = value;
		}
		
		public String getRecUpdTsBegin() {
			return this.recUpdTsBegin;
		}
		public void setRecUpdTsEnd(String value) {
			this.recUpdTsEnd = value;
		}
		
		public String getRecUpdTsEnd() {
			return this.recUpdTsEnd;
		}

	public void setRecUpdTs(java.util.Date value) {
		this.recUpdTs = value;
	}
	
	public java.util.Date getRecUpdTs() {
		return this.recUpdTs;
	}
	public String[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	

}

