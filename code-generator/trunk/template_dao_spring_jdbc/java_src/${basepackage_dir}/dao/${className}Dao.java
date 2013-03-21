<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao;

import cn.org.rapid_framework.page.Page;
import org.springframework.stereotype.Repository;
import ${basepackage}.model.${className};
import com.allinpay.its.framework.dao.jdbc.BaseSpringJdbcDao;
import com.allinpay.its.framework.vo.query.BaseQuery;

@Repository
<#if table.compositeId>
public class ${className}Dao extends BaseSpringJdbcDao<${className}, ${className}>{
<#else>
public class ${className}Dao extends BaseSpringJdbcDao<${className}, ${table.idColumn.javaType}>{
</#if>
	
	//注意: getSqlGenerator()可以生成基本的：增删改查sql语句, 通过这个父类已经封装了增删改查操作
    // sqlgenerator参考: http://code.google.com/p/rapid-framework/wiki/rapid_sqlgenerator
    
	public Class<${className}> getEntityClass() {
		return ${className}.class;
	}
	
	public ${className} save(${className} entity) {
		String sql = getSqlGenerator().getInsertSql();
		
		//insertWithAssigned(entity,sql); //手工分配
		
		//其它主键生成策略
		insertWithOracleSequence(entity,"seq_${table.sqlName}",sql); //oracle sequence:
		//insertWithDB2Sequence(entity,"seq${table.sqlName}",sql); //db2 sequence:
		//insertWithIdentity(entity,sql); //for sqlserver and mysql
		//insertWithUUID(entity,sql); //uuid
		
		return entity;
	}
	<#list table.columns as column>
	<#if column.unique && !column.pk>
	
	public ${className} getBy${column.columnName}(${column.javaType} v) {
		String sql = "select " + getSqlGenerator().getColumnsSql() + " from ${table.sqlName} where ${column.columnNameLower}=?";
		return (${className})DataAccessUtils.singleResult(getJdbcTemplate().queryForList(sql, ParameterizedBeanPropertyRowMapper.newInstance(getEntityClass()), v));
	}
	</#if>
	</#list>
	
	/**
	 * 根据查询条件进行分页检索
	 * 
	 * @param query
	 * @return
	 */
	public Page<${className}> findPageByQuery(BaseQuery<${className}> query) {
		// XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
		// [column]为字符串拼接, {column}为使用占位符. 如username='[username]',偷懒时可以使用字符串拼接 
		// [sortColumns] 为PageRequest的属性
		String xSql = new StringBuilder(200).append("select ").append(getSqlGenerator().getColumnsSql("t")).append(" from ${table.sqlName} t where 1=1 ")
			<#list table.columns as column>
			  	<#if column.isNotIdOrVersionField>
			  	<#if column.isDateTimeColumn>
			  	.append("/~ and t.${column.sqlName} >= {${column.columnNameLower}Begin} ~/")
			  	.append("/~ and t.${column.sqlName} <= {${column.columnNameLower}End} ~/")
				<#else>
				.append("/~ and t.${column.sqlName} = {${column.columnNameLower}} ~/")
			  	</#if>
				</#if>
			</#list>
				.append("/~ order by [sortColumns] ~/").toString();
		
		return findPageByQuery(xSql, query);
	}

}
