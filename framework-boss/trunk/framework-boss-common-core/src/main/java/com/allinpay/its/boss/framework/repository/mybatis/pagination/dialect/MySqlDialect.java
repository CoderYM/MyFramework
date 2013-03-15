package com.allinpay.its.boss.framework.repository.mybatis.pagination.dialect;

import com.allinpay.its.boss.framework.repository.mybatis.pagination.DataBaseDialect;

/**
 * 针对MySql的物理分页查询实现方式
 * @author YM
 *
 */
public class MySqlDialect extends DataBaseDialect {

	@Override
	public String getPageSelectSQL(String sql, int start, int limit) {


		return sql + " limit "+start+"," +limit;
	}

}
