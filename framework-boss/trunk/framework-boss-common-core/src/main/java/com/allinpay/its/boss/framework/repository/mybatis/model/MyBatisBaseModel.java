package com.allinpay.its.boss.framework.repository.mybatis.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.allinpay.its.boss.framework.annotation.Description;
import com.allinpay.its.boss.framework.repository.mybatis.Exception.MyBatisPojoStructureException;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

/**
 * MyBatis用POJO基类
 * 
 * @author YM
 * 
 */
public class MyBatisBaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	

	/**
	 * 获取POJO对应的表名 需要POJO中的属性定义@Table(name)
	 * 
	 * @return
	 */

//	public String getTableName() {
//		Table table = this.getClass().getAnnotation(Table.class);
//		if (table != null) {
//			return table.name();
//		} else {
//			throw new MyBatisPojoStructureException(
//					"undefine POJO @Table, need Tablename(@Table(name))");
//		}
//	}
	
	/**
	 * 获取POJO对应的字段中文描述
	 * @return
	 */
	public String getModelFieldDescription(String fieldName) {
		for (Field field : this.getClass().getDeclaredFields()) {
			if(fieldName.equals(field.getName())){
				
				if (field.isAnnotationPresent(Description.class)){
					Description description= field.getAnnotation(Description.class);
					return description.value();
				}
			}else{
				return null;
			}
		}
		throw new MyBatisPojoStructureException(
				"undefine POJO @Description, need on Field(@Description(\"description\"))");
			
	}

	/**
	 * 获取POJO中的主键字段名 需要定义@Id
	 * 
	 * @return
	 */
	public String getPrimaryKey() {
		for (Field field : this.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(Id.class))
				return field.getName();
		}

		throw new MyBatisPojoStructureException("undefine POJO @Id");
	}

	/**
	 * 获取POJO中主键的序列名称,需要定义@SequenceGenerator(name="序列名称")
	 * @return
	 */
	public String getSequenceName() {
		Field[] fields = this.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].isAnnotationPresent(SequenceGenerator.class)) {
				SequenceGenerator seq = fields[i]
						.getAnnotation(SequenceGenerator.class);
				return seq.name();
			}
		}

		throw new MyBatisPojoStructureException("undefine POJO @SequenceGenerator(name=\"seqName\")");
	}

	/**
	 * 用于存放POJO的列信息
	 */
	private transient static Map<Class<? extends MyBatisBaseModel>, List<String>> columnMap = new HashMap<Class<? extends MyBatisBaseModel>, List<String>>();

	private boolean isNull(String fieldname) {
		try {
			Field field = this.getClass().getDeclaredField(fieldname);
			return isNull(field);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return false;
	}

	private boolean isNull(Field field) {
		try {
			field.setAccessible(true);
			return field.get(this) == null;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 用于计算类定义 需要POJO中的属性定义@Column(name)
	 */
	public void caculationColumnList() {
		if (columnMap.containsKey(this.getClass()))
			return;

		Field[] fields = this.getClass().getDeclaredFields();
		List<String> columnList = new ArrayList<String>(fields.length);

		for (Field field : fields) {
			if (field.isAnnotationPresent(Column.class))
				columnList.add(field.getName());
		}

		columnMap.put(this.getClass(), columnList);
	}

	/**
	 * 获取用于WHERE的 有值字段详细信息
	 * 
	 * @return
	 */
	public List<WhereColumnModel> getWhereColumnsNameValueType() {
		Field[] fields = this.getClass().getDeclaredFields();
		List<WhereColumnModel> columnList = new ArrayList<WhereColumnModel>(fields.length);

		for (Field field : fields) {
			if (field.isAnnotationPresent(Column.class) && !isNull(field))
				columnList.add(new WhereColumnModel(field.getName(),"#{"+field.getName()+"}", field
						.getGenericType()));
		}

		return columnList;
	}
	



	/**
	 * 用于获取Insert的字段累加
	 * 
	 * @return
	 */
	public String returnInsertColumnsName() {
		StringBuilder sb = new StringBuilder();

		List<String> list = columnMap.get(this.getClass());
		int i = 0;
		for (String column : list) {
			if (isNull(column)){
				continue;
			}

			if (i++ != 0){
				sb.append(',');
			}
			sb.append(column);
		}
		return sb.toString();
	}

	/**
	 * 用于获取Insert的字段映射累加
	 * 
	 * @return
	 */
	public String returnInsertColumnsDefine() {
		StringBuilder sb = new StringBuilder();

		List<String> list = columnMap.get(this.getClass());
		int i = 0;
		for (String column : list) {
			if (isNull(column))
				continue;

			if (i++ != 0)
				sb.append(',');
			sb.append("#{").append(column).append('}');
		}
		return sb.toString();
	}

	/**
	 * 用于获取Update Set的字段累加
	 * 
	 * @return
	 */
	public String returnUpdateSet() {
		StringBuilder sb = new StringBuilder();

		List<String> list = columnMap.get(this.getClass());
		int i = 0;
		for (String column : list) {
			if (isNull(column))
				continue;

			if (i++ != 0)
				sb.append(',');
			sb.append(column).append("=#{").append(column).append('}');
		}
		return sb.toString();
	}


	/**
	 * 转化POJO为JSON格式
	 * 
	 * @return
	 */
	public String toJSONString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	/**
	 * 打印类字段信息
	 */
	@Override
	public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (Field f : fields) {
			if (Modifier.isStatic(f.getModifiers())
					|| Modifier.isFinal(f.getModifiers()))
				continue;
			Object value = null;
			try {
				f.setAccessible(true);
				value = f.get(this);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (value != null)
				sb.append(f.getName()).append('=').append(value).append(',');
		}
		sb.append(']');

		return sb.toString();
	}
	
	/**
	 * 每页显示多少条(每页记录数)
	 */
	private int numPerPage=10;		
	/**
	 * 当前是第几页(页码)
	 */
	private int pageNum=1;	
	
	private int pageNumShown;
	
	
	//分页所要执行的配置文件中对应的id名称
	private String pageSqlIdName;
	private String countPageSqlIdName;


	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getPageNumShown() {
		return pageNumShown;
	}

	public void setPageNumShown(int pageNumShown) {
		this.pageNumShown = pageNumShown;
	}



	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

//	public String getPageSqlIdName() {
//		if(StringUtils.isBlank(pageSqlIdName)){
//			return ("page_"+this.getTableName()+"_id").toUpperCase();
//		}
//		return pageSqlIdName;
//	}

	public void setPageSqlIdName(String pageSqlIdName) {
		this.pageSqlIdName = pageSqlIdName;
	}

//	public String getCountPageSqlIdName() {
//		if(StringUtils.isBlank(countPageSqlIdName)){
//			return ("count_page_"+this.getTableName()+"_id").toUpperCase();
//		}
//		return countPageSqlIdName;
//	}

	public void setCountPageSqlIdName(String countPageSqlIdName) {
		this.countPageSqlIdName = countPageSqlIdName;
	}


}
