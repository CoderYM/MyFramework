/*
 * 版权声明 .
 * 此文档的版权归通联支付网络服务有限公司所有
 * Powered By [Allinpay-Boss-framework]
 */

package com.allinpay.its.boss.system.permission.dao;

import com.allinpay.its.boss.framework.repository.mybatis.MyBatisRepository;
import com.allinpay.its.boss.system.permission.model.FrameworkPermAssign;

import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 其中@MyBatisRepository是我们创建的新的注解。
 * Dao中的方法名和TestMapper.xml中的id值必须一致。
 * 从而实现省略不必要的代码
 * @return
 */
@MyBatisRepository 
public interface IFrameworkPermAssignDao  {

	/**根据自定义的对象查询,返回对象集合
	 * 该方法尚未在Mybatis的Mapper文件中进行配置,请自行实现
	 */
	public List<FrameworkPermAssign> findListByObj(FrameworkPermAssign frameworkPermAssign);
	/**
	 * 根据自定义的sql查询对象返回对象集合
	 * 该方法尚未在Mybatis的Mapper文件中进行配置,请自行实现
	 */
	public List<FrameworkPermAssign> findListBySqlId(String sqlId,FrameworkPermAssign frameworkPermAssign);
	public  FrameworkPermAssign findByPKId(@Param("pk_Id") int pk_Id);
	
	public void updateFrameworkPermAssignBySql(FrameworkPermAssign frameworkPermAssign);
}

