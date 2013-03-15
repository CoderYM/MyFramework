package com.allinpay.its.boss.framework.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.allinpay.its.boss.framework.repository.mybatis.utils.CRUDTemplate;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.dialect.DB2Dialect;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.Oracle10gDialect;
/**
 * 从SpringSide框架中所提取的,增加了对DB2的支持,用来配合Spring中针对Hibernate基于JPA的良好支持
 * @author YM
 *
 */
public class PersistenceUtil {

	public static final String DATETIME_TYPE = "org.jadira.usertype.dateandtime.joda.PersistentDateTime";

	/**
	 * Initialize the lazy property value.
	 * 
	 * eg.
	 * Hibernates.initLazyProperty(user.getGroups()); 
	 */
	public static void initLazyProperty(Object proxyedPropertyValue) {
		Hibernate.initialize(proxyedPropertyValue);
	}

	/**
	 * 从DataSoure中取出connection, 根据connection的metadata中的jdbcUrl判断Dialect类型.
	 */
	public static String getDialect(DataSource dataSource) {
		// 从DataSource中取出jdbcUrl.
		String jdbcUrl = null;
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			if (connection == null) {
				throw new IllegalStateException("Connection returned by DataSource [" + dataSource + "] was null");
			}
			jdbcUrl = connection.getMetaData().getURL();
		} catch (SQLException e) {
			throw new RuntimeException("Could not get database url", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		// 根据jdbc url判断dialect,这里可以根据自己的数据库进行添加
		if (StringUtils.contains(jdbcUrl, ":h2:")) {
			CRUDTemplate.DB_NAME="H2";
			return H2Dialect.class.getName();
		} else if (StringUtils.contains(jdbcUrl, ":mysql:")) {
			CRUDTemplate.DB_NAME="MYSQL";
			return MySQL5InnoDBDialect.class.getName();
		} else if (StringUtils.contains(jdbcUrl, ":oracle:")) {
			CRUDTemplate.DB_NAME="ORACLE";
			return Oracle10gDialect.class.getName();
		}else if (StringUtils.contains(jdbcUrl, ":db2:")) {
			CRUDTemplate.DB_NAME="DB2";
			return DB2Dialect.class.getName();
		} else {
			throw new IllegalArgumentException("Unknown Database of " + jdbcUrl);
		}
	}
}
