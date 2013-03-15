package com.allinpay.its.boss.framework.repository.mybatis.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.allinpay.its.boss.framework.repository.mybatis.model.MyBatisBaseModel;
import com.allinpay.its.boss.framework.utils.Page;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * Mybatis基本操作数据库的CRUD方法,包含支持多数据库的物理分页实现
 * @author YM
 *
 * @param <T>
 */
public abstract class AbstractBaseMyBatisDao <T extends MyBatisBaseModel> extends SqlSessionDaoSupport implements IMyBatisBaseDao<T>{
	
	protected Class<T> modelClass;

	@SuppressWarnings("unchecked")
	public AbstractBaseMyBatisDao() {
		modelClass = guessEntityClass(getClass());
	}
	
	/**
	 * 根据条件查询对象
	 * @param sqlId xml配置的id
	 * @param values
	 * @return
	 */
	public List<T> findBy(String sqlId, T values) {
		return getSqlSession().selectList(sqlId, values);
	}
	
	/**
	 * <pre>
	 * 根据单个入参，获取指定类型对象的数据集，并将其自动转型。
	 * @param value
	 * @return List<T>
	 */
	public List<T> findBy(String sqlId, Serializable value) {
		return getSqlSession().selectList(sqlId, value);
	}
	
	/**
	 * 支出多数据库的物理分页
	 */
	public Page pageBy(String querySqlId, String queryCountSqlId, T value, int pageIndex, int pageSize) {
		if(StringUtils.isBlank(querySqlId)){
			querySqlId = "selectPage_"+modelClass.getSimpleName()+"s";
		}
		if(StringUtils.isBlank(queryCountSqlId)){
			queryCountSqlId = "select_"+modelClass.getSimpleName()+"sCount";
		}
		
		List<T> result = this.getSqlSession().selectList(querySqlId, value, new RowBounds(pageIndex * pageSize - pageSize,pageSize));
		Long totalRecord = (Long)this.getSqlSession().selectOne(queryCountSqlId, value);
		return new Page(totalRecord.intValue(), pageIndex, pageSize, result);
	}
	
	/**
	 * 增加方法
	 * @param entity
	 * @return
	 */
	public Object save(T entity) {
		String sqlId = "insert_"+modelClass.getSimpleName();
		return this.getSqlSession().insert(sqlId, entity);
	}
	/**
	 * 更新方法
	 * @param entity
	 * @return
	 */
	public int update(T entity) {
		// String sqlId = "insert" + entity.getClass().getSimpleName();
		String sqlId = "updateById_" + modelClass.getSimpleName();

		//doSaveOrUpdateOperateLog(entity, "更新");

		return this.getSqlSession().update(sqlId, entity);
	}
	
	/**
	 * 删除
	 * @param sqlId
	 * @param values
	 * @return
	 */
	public int delete(String sqlId, Map<String, Object> values) {
		return getSqlSession().delete(sqlId, values);
	}
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	public int deleteById(Serializable id) {
		String sqlId = "deleteById_" + modelClass.getSimpleName();


		return getSqlSession().delete(sqlId, id);
	}
	
	/**
	 * 根据对象删除
	 * @param entity
	 * @return
	 */
	public int deleteByObj(T entity) {
		String sqlId = "deleteByObj_" + modelClass.getSimpleName();


		return getSqlSession().delete(sqlId, entity);
	}
	
	
	@SuppressWarnings("rawtypes")
	public static Class guessEntityClass(Class clazz) {
		Type type = clazz.getGenericSuperclass();
		Class retval = null;
		if (type instanceof ParameterizedType) {
			Type[] params = ((ParameterizedType) type).getActualTypeArguments();
			retval = (Class) params[0];
		}
		return retval;
	}

}
