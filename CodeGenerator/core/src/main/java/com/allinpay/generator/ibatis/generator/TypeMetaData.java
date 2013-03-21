/**
 * Created at 2008-1-23 by pony
 */
package com.allinpay.generator.ibatis.generator;

import com.allinpay.framework.dao.ibatis.IModel;

/**
 * TypeMetaData class.
 * If any author make any changes to this source code, please write change log here.
 * 1.2008-1-23	pony created this class.
 */
public class TypeMetaData implements IModel {
	//Auto Generated Begin
	
	/**
	 * serial Version UID.
	 */
	private static final long serialVersionUID = -938774932L;
	
	/**
	 * type
	 */
	private Integer type;
	
	/**
	 * meta_data_type
	 */
	private Integer metaDataType;
	
	/**
	 * description
	 */
	private String description;
	
	/**
	 * name
	 */
	private String name;
	

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * @return the metaDataType
	 */
	public Integer getMetaDataType() {
		return metaDataType;
	}

	/**
	 * @param meta_data_type the meta_data_type to set
	 */
	public void setMetaDataType(Integer metaDataType) {
		this.metaDataType = metaDataType;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	//Auto Generated End
}
