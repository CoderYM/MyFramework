package com.allinpay.its.boss.framework.repository.mybatis.utils;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.util.List;

import com.allinpay.its.boss.framework.repository.mybatis.model.MyBatisBaseModel;
import com.allinpay.its.boss.framework.repository.mybatis.model.WhereColumnModel;
import com.allinpay.its.boss.framework.repository.mybatis.pagination.DataBaseDialect;
import com.allinpay.its.boss.framework.repository.mybatis.pagination.dialect.DB2Dialect;
import com.allinpay.its.boss.framework.repository.mybatis.pagination.dialect.MySqlDialect;
import com.allinpay.its.boss.framework.repository.mybatis.pagination.dialect.OracleDialect;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 包含增加,更新,删除方法的Mybatis模版工具类
 * 
 * @author YM
 * 
 * @param <T>
 */
@Transactional
public class CRUDTemplate<T extends MyBatisBaseModel>  {

	protected static Logger log = LoggerFactory.getLogger(CRUDTemplate.class);
	public static String DB_NAME = "";
	public SqlSessionTemplate sqlSession;
	
	/**
	 * 增加对象方法
	 * 
	 * @param obj
	 * @return
	 */
	public String insert(T obj) {
		BEGIN();

//		INSERT_INTO(obj.getTableName());
		obj.caculationColumnList();
		// 如果有主键则需要指定序列自动增加主键值
		if (StringUtils.isNotBlank(obj.getPrimaryKey())
				&& StringUtils.isNotBlank(obj.getSequenceName())) {
			VALUES(obj.getPrimaryKey(), obj.getSequenceName() + ".nextval");
		}
		VALUES(obj.returnInsertColumnsName(), obj.returnInsertColumnsDefine());

		return SQL();
	}

	/**
	 * 根据主键更新对象方法
	 * 
	 * @param obj
	 * @return
	 */
	public String update(T obj) {
		String idname = obj.getPrimaryKey();

		BEGIN();

//		UPDATE(obj.getTableName());
		obj.caculationColumnList();
		SET(obj.returnUpdateSet());
		WHERE(idname + "=#{" + idname + "}");

		return SQL();
	}

	/**
	 * 根据主键删除对象的方法
	 * 
	 * @param obj
	 * @return
	 */
	public String delete(T obj) {
		String idname = obj.getPrimaryKey();

		BEGIN();

//		DELETE_FROM(obj.getTableName());
		WHERE(idname + "=#{" + idname + "}");

		return SQL();
	}

	/**
	 * 通用查询语句
	 * 
	 * @param obj
	 * @return
	 */
	public String select(T obj) {
		BEGIN();
		SELECT(" * ");
//		FROM(obj.getTableName());
		String whereSQLInfo = getWhereInfos(obj);
		if (StringUtils.isNotBlank(whereSQLInfo)) {

			WHERE(whereSQLInfo);
		}
		return SQL();
	}

	/**
	 * 统计行数
	 * 
	 * @param obj
	 * @return
	 */
	public String count(T obj) {
		BEGIN();
		SELECT("count(*)");
//		FROM(obj.getTableName());
		String whereSQLInfo = getWhereInfos(obj);
		if (StringUtils.isNotBlank(whereSQLInfo)) {

			WHERE(whereSQLInfo);
		}

		return SQL();
	}

	/**
	 * 获取where条件语句
	 * 
	 * @param obj
	 * @return
	 */
	public String getWhereInfos(T obj) {
		StringBuffer sb = null;
		List<WhereColumnModel> whereColumns = obj
				.getWhereColumnsNameValueType();
		if (whereColumns != null && whereColumns.size() > 0) {
			sb = new StringBuffer("");
			for (int i = 0; i < whereColumns.size(); i++) {
				String v_name = whereColumns.get(i).getName();
				sb.append(v_name + "=#{" + v_name + "}");
				if (i != whereColumns.size() - 1) {
					sb.append(" and "); // 这里先用and条件后续可以拓展通过注解方式灵活配置
				}
			}
			return sb.toString();
		} else {
			return null;
		}
	}

	/**
	 * 这是对Mybatis注解查询的一个简单扩展,目前只支持简单类型的属性,建议直接通过xml进行配置sql
	 * @param obj
	 * @return
	 */
	public String pageSelect(T obj) {

//		String sql = select(obj).trim();
//		SqlSession sqlSession;
		String sql="";
		try {
			/**
			 * 这里直接取spring管理的sqlSession有很多方式取,其他方式我都用了.老取得为空,因此这里暂时这样来取,后续待优化
			 * 这里是取xml中对应id的最终执行sql语句
			 */
			sqlSession=(SqlSessionTemplate) WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext()).getBean("sqlSession");
//			sql = MyBatisSQLUtil.getMyBatisSql4POJO(obj.getPageSqlIdName(), obj, sqlSession.getSqlSessionFactory()).toString();
//			sql = sqlSession.getConfiguration().getMappedStatement(obj.getPageSqlIdName()).getBoundSql(obj).getSql();
		} catch (Exception e) {
			System.out.println("----Mybatis配置文件中获取sql语句出错---");
			if(log.isDebugEnabled()){
				log.debug(e.getMessage());
			}
			e.printStackTrace();
		}
		
		DataBaseDialect dataBaseDialect = null;
		DataBaseDialect.Type databaseType = null;
		databaseType = DataBaseDialect.Type.valueOf(DB_NAME);
		String page_sql = "";
		int limit = obj.getNumPerPage();
		int start = obj.getPageNum();
		// 默认情况下每页显示15条
		if (limit == 0) {
			limit = 15;
		}
		switch (databaseType) {
		case ORACLE:
			dataBaseDialect = new OracleDialect();
			page_sql = dataBaseDialect.getPageSelectSQL(sql, start, limit);
			break;
		case MYSQL:// 需要实现MySQL的分页逻辑
			dataBaseDialect = new MySqlDialect();
			page_sql = dataBaseDialect.getPageSelectSQL(sql, start, limit);
			break;
		case DB2:// 需要实现DB2的分页逻辑
			dataBaseDialect = new DB2Dialect();
			page_sql = dataBaseDialect.getPageSelectSQL(sql, start, limit);
			break;
		}

		return page_sql;
	}

}
