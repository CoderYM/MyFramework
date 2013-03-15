package com.allinpay.its.boss.framework.repository.mybatis.dao;

import java.io.Serializable;
import java.util.Map;

import com.allinpay.its.boss.framework.repository.mybatis.model.MyBatisBaseModel;
import com.allinpay.its.boss.framework.utils.Page;

public interface IMyBatisBaseDao<T extends MyBatisBaseModel> {
	
	public Page pageBy(String querySqlId, String queryCountSqlId, T value, int pageIndex, int pageSize);
	 

	/**
	 * 增加方法
	 * @param entity
	 * @return
	 */
	public Object save(T entity);
	
	/**
	 * 更新方法
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	/**
	 * 删除
	 * @param sqlId
	 * @param values
	 * @return
	 */
	public int delete(String sqlId, Map<String, Object> values);
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	public int deleteById(Serializable id);
}
