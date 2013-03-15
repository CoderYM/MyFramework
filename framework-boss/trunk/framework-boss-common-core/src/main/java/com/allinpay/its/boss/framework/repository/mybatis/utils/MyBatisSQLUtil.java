package com.allinpay.its.boss.framework.repository.mybatis.utils;

import java.util.List;
import java.util.Map;

import com.allinpay.its.boss.framework.repository.mybatis.model.MyBatisSql;

import org.apache.ibatis.builder.xml.dynamic.ForEachSqlNode;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;

/**
 * 运行期获取MyBatis执行的SQL及参数
 * 
 * @param id
 *            Mapper xml 文件里的select Id
 * @param parameterMap
 *            参数
 * @param sqlSessionFactory
 * @return
 */
public class MyBatisSQLUtil {
	public static MyBatisSql getMyBatisSql(String id,
			Map<String, Object> parameterMap,
			SqlSessionFactory sqlSessionFactory) {
		MyBatisSql ibatisSql = new MyBatisSql();
		MappedStatement ms = sqlSessionFactory.getConfiguration()
				.getMappedStatement(id);
		
		BoundSql boundSql = ms.getBoundSql(parameterMap);
		ibatisSql.setSql(boundSql.getSql());
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		if (parameterMappings != null) {
			Object[] parameterArray = new Object[parameterMappings.size()];
			ParameterMapping parameterMapping = null;
			Object value = null;
			Object parameterObject = null;
			MetaObject metaObject = null;
			PropertyTokenizer prop = null;
			String propertyName = null;
			String[] names = null;
			for (int i = 0; i < parameterMappings.size(); i++) {
				parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					propertyName = parameterMapping.getProperty();
					names = propertyName.split("\\.");
					if (propertyName.indexOf(".") != -1 && names.length == 2) {
						parameterObject = parameterMap.get(names[0]);
						propertyName = names[1];
					} else if (propertyName.indexOf(".") != -1
							&& names.length == 3) {
						parameterObject = parameterMap.get(names[0]); // map
						if (parameterObject instanceof Map) {
							parameterObject = ((Map) parameterObject)
									.get(names[1]);
						}
						propertyName = names[2];
					} else {
						parameterObject = parameterMap.get(propertyName);
					}
					metaObject = parameterMap == null ? null : MetaObject
							.forObject(parameterObject);
					prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (ms.getConfiguration().getTypeHandlerRegistry()
							.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName
							.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = MetaObject.forObject(value).getValue(
									propertyName.substring(prop.getName()
											.length()));
						}
					} else {
						value = metaObject == null ? null : metaObject
								.getValue(propertyName);
					}
					parameterArray[i] = value;
				}
			}
			ibatisSql.setParameters(parameterArray);
		}
		return ibatisSql;
	}
	public static MyBatisSql getMyBatisSql4POJO(String id,
			Object parameterMap,
			SqlSessionFactory sqlSessionFactory) {
		MyBatisSql ibatisSql = new MyBatisSql();
		MappedStatement ms = sqlSessionFactory.getConfiguration()
				.getMappedStatement(id);
		
		BoundSql boundSql = ms.getBoundSql(parameterMap);
		ibatisSql.setSql(boundSql.getSql());
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		if (parameterMappings != null) {
			Object[] parameterArray = new Object[parameterMappings.size()];
			ParameterMapping parameterMapping = null;
			Object value = null;
			Object parameterObject = null;
			MetaObject metaObject = null;
			PropertyTokenizer prop = null;
			String propertyName = null;
			String propertyType = null;
			String[] names = null;
			for (int i = 0; i < parameterMappings.size(); i++) {
				parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					propertyName = parameterMapping.getProperty();
					propertyType = parameterMapping.getJavaType().getName();
					names = propertyName.split("\\.");
//					if (propertyName.indexOf(".") != -1 && names.length == 2) {
//						parameterObject = parameterMap.get(names[0]);
//						propertyName = names[1];
//					} else if (propertyName.indexOf(".") != -1
//							&& names.length == 3) {
//						parameterObject = parameterMap.get(names[0]); // map
//						if (parameterObject instanceof Map) {
//							parameterObject = ((Map) parameterObject)
//									.get(names[1]);
//						}
//						propertyName = names[2];
//					} else {
//						parameterObject = parameterMap.get(propertyName);
//					}
					parameterObject = parameterMap;
					metaObject = parameterMap == null ? null : MetaObject
							.forObject(parameterObject);
					prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (ms.getConfiguration().getTypeHandlerRegistry()
							.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName
							.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = MetaObject.forObject(value).getValue(
									propertyName.substring(prop.getName()
											.length()));
						}
					} else {
						value = metaObject == null ? null : metaObject
								.getValue(propertyName);
					}
					if(propertyType != null && propertyType.equals("java.lang.String") ){
						parameterArray[i]="'"+value+"'";
					}else{
						parameterArray[i] = value;
					}
				}
			}
			ibatisSql.setParameters(parameterArray);
		}
		return ibatisSql;
	}
}
