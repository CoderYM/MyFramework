package com.allinpay.its.boss.system.parseenum.model;


public class EnumModel {
	
	/**
	 * 大类代码
	 */
	private String cvalue;
	
	/**
	 * 获取小类字典集合枚举类对应方法名
	 */
	private String method;
	
	/**
	 * 包含过滤,表示只返回include中key的Select数据
	 * 格式为："key1,key2,key3"
	 */
	private String include;
	/**
	 * 剔除过滤,标识exclude中的key不包含在Select数据中
	 * 格式为："key1,key2,key3"
	 */
	private String exclude;
	
	private String isSelect;
	

	public String getCvalue() {
		return cvalue;
	}

	public String getInclude() {
		return include;
	}

	public String getExclude() {
		return exclude;
	}

	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}

	/**
	 * @param include the include to set
	 */
	public void setInclude(String include) {
		this.include = include;
	}

	/**
	 * @param exclude the exclude to set
	 */
	public void setExclude(String exclude) {
		this.exclude = exclude;
	}

}
