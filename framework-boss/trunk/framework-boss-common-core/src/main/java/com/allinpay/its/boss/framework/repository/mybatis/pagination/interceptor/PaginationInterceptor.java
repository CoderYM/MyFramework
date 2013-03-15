package com.allinpay.its.boss.framework.repository.mybatis.pagination.interceptor;

import java.sql.Connection;
import java.util.Properties;

import com.allinpay.its.boss.framework.repository.mybatis.pagination.DataBaseDialect;
import com.allinpay.its.boss.framework.repository.mybatis.pagination.dialect.DB2Dialect;
import com.allinpay.its.boss.framework.repository.mybatis.pagination.dialect.MySqlDialect;
import com.allinpay.its.boss.framework.repository.mybatis.pagination.dialect.OracleDialect;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 这是从网上参考的,感觉不灵活,并且影响性能,因为每次查询都会到这里来兜一圈
 * 因此将功能封装到CRUDTemplate类中通过注解方式更方便,实现我要就用不要就不用
 * @author YM
 *
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {
	 //日志对象  
    protected static Logger log = LoggerFactory.getLogger(PaginationInterceptor.class);
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation
				.getTarget();
		MetaObject metaStatementHandler = MetaObject
				.forObject(statementHandler);

		RowBounds rowBounds = (RowBounds) metaStatementHandler
				.getValue("delegate.rowBounds");
		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			return invocation.proceed();
		}

//		DefaultParameterHandler defaultParameterHandler = (DefaultParameterHandler) metaStatementHandler
//				.getValue("delegate.parameterHandler");
//		Map parameterMap = (Map) defaultParameterHandler.getParameterObject();
//		Object sidx = parameterMap.get("_sidx");
//		Object sord = parameterMap.get("_sord");
//
		String originalSql = (String) metaStatementHandler
				.getValue("delegate.boundSql.sql");
//
//		if (sidx != null && sord != null) {
//			originalSql = originalSql + " order by " + sidx + " " + sord;
//		}

		Configuration configuration = (Configuration) metaStatementHandler
				.getValue("delegate.configuration");

		DataBaseDialect.Type databaseType = null;
		try {
			databaseType = DataBaseDialect.Type.valueOf(configuration
					.getVariables().getProperty("dialect").toUpperCase());
		} catch (Exception e) {
			// ignore
		}
		if (databaseType == null) {
			throw new RuntimeException(
					"the value of the dialect property in configuration.xml is not defined : "
							+ configuration.getVariables().getProperty(
									"dialect"));
		}
		DataBaseDialect dialect = null;
		switch (databaseType) {
		case ORACLE:
			dialect = new OracleDialect();
			break;
		case MYSQL:// 需要实现MySQL的分页逻辑
			dialect = new MySqlDialect();
			break;
		case DB2:// 需要实现DB2的分页逻辑
			dialect = new DB2Dialect();
			break;

		}

		metaStatementHandler.setValue("delegate.boundSql.sql", dialect
				.getPageSelectSQL(originalSql, rowBounds.getOffset(),
						rowBounds.getLimit()));
		metaStatementHandler.setValue("delegate.rowBounds.offset",
				RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit",
				RowBounds.NO_ROW_LIMIT);
		if (log.isDebugEnabled()) {
			BoundSql boundSql = statementHandler.getBoundSql();
			log.debug("生成分页SQL : " + boundSql.getSql());
		}
		//System.out.println("生成的分页SQL ----> "+statementHandler.getBoundSql().getSql());
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {

		return Plugin.wrap(target, this); 
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
