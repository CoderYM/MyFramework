package com.allinpay.its.boss.framework.repository.mybatis.model;

/**
 * Where条件信息
 * @author YM
 *
 */
public class WhereColumnModel {
	private String name;
	private String value;
	private Object fieldType;

	public WhereColumnModel() {
	}

	public WhereColumnModel(String name, String value, Object fieldType) {
		this.name = name;
		this.value = value;
		this.fieldType = fieldType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Object getFieldType() {
		return fieldType;
	}

	public void setFieldType(Object fieldType) {
		this.fieldType = fieldType;
	}

}