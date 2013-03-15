package com.allinpay.its.boss.framework.repository.mybatis.pagination.dialect;

import com.allinpay.its.boss.framework.repository.mybatis.pagination.DataBaseDialect;

/**
 * 针对Oracle的物理分页查询实现方式
 * @author YM
 *
 */
public class OracleDialect extends DataBaseDialect {

	/**
	 * 返回Oracle的分页查询语句
	 */
	@Override
	public String getPageSelectSQL(String sql, int start, int limit) {
		
		sql = sql.trim();  
        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);  
          
        pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");  
          
        pagingSelect.append(sql);  
          
        pagingSelect.append(" ) row_ ) where rownum_ > ").append(start).append(" and rownum_ <= ").append(start + limit);  
          
        return pagingSelect.toString();  
	}

}
