package com.allinpay.its.boss.framework.repository.mybatis.pagination;

/**
 * 抽象类,用来抽象数据库方言
 * @author YM
 *
 */
public abstract class DataBaseDialect {
	
	public static enum Type{  
        MYSQL,  
        ORACLE,
        DB2
    }  
      /**
       * 返回分页查询语句,多数据库支持
       * @param sql
       * @param start
       * @param limit
       * @return
       */
    public abstract String getPageSelectSQL(String sql, int start, int limit);

}
