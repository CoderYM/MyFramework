package com.allinpay.its.boss.framework.repository.mybatis.pagination.dialect;

import com.allinpay.its.boss.framework.repository.mybatis.pagination.DataBaseDialect;

public class DB2Dialect extends DataBaseDialect {

	@Override
	public String getPageSelectSQL(String sql, int start, int limit) {
		
		
		sql = sql.trim();  
        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);  
          
        pagingSelect.append("select * from ( select row_.*,rownumber() over() as row_id from ( ");  
          
        pagingSelect.append(sql);  
          
        pagingSelect.append("  ) row_ ) rownum_ where rownum_.row_id > ").append(start).append(" and rownum_.row_id <= ").append(start + limit);  
          
        return pagingSelect.toString(); 
	}

}
