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

@Table(name="FRAMEWORK_ACTION_LOG")
@Alias(value="FrameworkActionLog")
public class FrameworkActionLog extends  MyBatisBaseModel {
	private static final long serialVersionUID = 13352179L;
	
	/** 变量 LOG_TIME . */
	@Description(value="操作时间开始")
	private String logTimeBegin;
	@Description(value="操作时间结束")
	private String logTimeEnd;
	
	//columns START
	/** 变量 id . */
	@Description(value="ID<seq>  ")
	private Long id;
	/** 变量 logTime . */
	@Description(value="操作时间")
	private java.util.Date logTime;
	/** 变量 logUser . */
	@Description(value="操作人员")
	private java.lang.String logUser;
	/** 变量 logOperate . */
	@Description(value="操作类型")
	private java.lang.String logOperate;
	/** 变量 logContent . */
	@Description(value="操作内容")
	private java.lang.String logContent;
	/** 变量 remark . */
	@Description(value="备注    ")
	private java.lang.String remark;
	/** 变量 version . */
	@Description(value="版本号  ")
	private Long version;
	/** 变量 logOperateClass . */
	@Description(value="操作对象名")
	private java.lang.String logOperateClass;
	/** 变量 logOperateMethod . */
	@Description(value="操作对象方法")
	private java.lang.String logOperateMethod;
	/** 变量 logOperateResult . */
	@Description(value="操作结果")
	private java.lang.String logOperateResult;
	/** 变量 logType . */
	@Description(value="日志类型")
	private java.lang.String logType;
	/** 变量 isAuthed . */
	@Description(value="授权是否通过:true通过；false未通过")
	private java.lang.String isAuthed;
	/** 变量 logOperateActionName . */
	@Description(value="操作action名字")
	private java.lang.String logOperateActionName;
	/** 变量 changeTableInfo . */
	@Description(value="捕捉到的参数信息")
	private java.lang.String changeTableInfo;
	//columns END

	/**
	* FrameworkActionLog 的构造函数
	*/
	public FrameworkActionLog() {
	}
	/**
	* FrameworkActionLog 的构造函数
	*/
	public FrameworkActionLog(
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
		public void setLogTimeBegin(String value) {
			this.logTimeBegin = value;
		}
		
		public String getLogTimeBegin() {
			return this.logTimeBegin;
		}
		public void setLogTimeEnd(String value) {
			this.logTimeEnd = value;
		}
		
		public String getLogTimeEnd() {
			return this.logTimeEnd;
		}

	public void setLogTime(java.util.Date value) {
		this.logTime = value;
	}
	
	public java.util.Date getLogTime() {
		return this.logTime;
	}
	

	public void setLogUser(java.lang.String value) {
		this.logUser = value;
	}
	
	public java.lang.String getLogUser() {
		return this.logUser;
	}
	

	public void setLogOperate(java.lang.String value) {
		this.logOperate = value;
	}
	
	public java.lang.String getLogOperate() {
		return this.logOperate;
	}
	

	public void setLogContent(java.lang.String value) {
		this.logContent = value;
	}
	
	public java.lang.String getLogContent() {
		return this.logContent;
	}
	

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	

	public void setVersion(Long value) {
		this.version = value;
	}
	
	public Long getVersion() {
		return this.version;
	}
	

	public void setLogOperateClass(java.lang.String value) {
		this.logOperateClass = value;
	}
	
	public java.lang.String getLogOperateClass() {
		return this.logOperateClass;
	}
	

	public void setLogOperateMethod(java.lang.String value) {
		this.logOperateMethod = value;
	}
	
	public java.lang.String getLogOperateMethod() {
		return this.logOperateMethod;
	}
	

	public void setLogOperateResult(java.lang.String value) {
		this.logOperateResult = value;
	}
	
	public java.lang.String getLogOperateResult() {
		return this.logOperateResult;
	}
	

	public void setLogType(java.lang.String value) {
		this.logType = value;
	}
	
	public java.lang.String getLogType() {
		return this.logType;
	}
	

	public void setIsAuthed(java.lang.String value) {
		this.isAuthed = value;
	}
	
	public java.lang.String getIsAuthed() {
		return this.isAuthed;
	}
	

	public void setLogOperateActionName(java.lang.String value) {
		this.logOperateActionName = value;
	}
	
	public java.lang.String getLogOperateActionName() {
		return this.logOperateActionName;
	}
	

	public void setChangeTableInfo(java.lang.String value) {
		this.changeTableInfo = value;
	}
	
	public java.lang.String getChangeTableInfo() {
		return this.changeTableInfo;
	}
	

}

