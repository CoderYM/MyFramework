/*
 * 版权声明 .
 * 此文档的版权归通联支付网络服务有限公司所有
 * Powered By [Allinpay-Boss-framework]
 */

package com.allinpay.its.boss.system.permission.service;


import javax.annotation.Resource;
import java.util.List;

import com.allinpay.its.boss.framework.utils.Page;
import com.allinpay.its.boss.system.permission.dao.IFrameworkActionLogDao;
import com.allinpay.its.boss.system.permission.dao.impl.FrameworkActionLogDaoImpl;
import com.allinpay.its.boss.system.permission.model.FrameworkActionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class FrameworkActionLogServiceImpl {
	@Autowired
	private IFrameworkActionLogDao frameworkActionLogDao;
	@Resource
	private FrameworkActionLogDaoImpl myBatisDao;
	
	
	/**
	 * 新增
	 * 
	 * @param  POJO对象
	 * @return String
	 */
	public String add(FrameworkActionLog frameworkActionLog) {
		// 保存申请信息
		myBatisDao.save(frameworkActionLog);
		return null;
	}
	
	
	/**
	 * 删除
	 * 
	 * @param  POJO对象
	 * @return String
	 */
	public String delete(int pk_id) {
		myBatisDao.deleteById(pk_id);
		return null;
	}
	
	/**
	 * 新增修改
	 * 有唯一主键，且主键自动生成不可编辑时
	 * @param  POJO对象
	 * @return String
	 */
//	public String saveOrUpdate(FrameworkActionLog frameworkActionLog) {
//		
//    	// 保存申请信息
//		if(frameworkActionLog.getPk() != null)
//			frameworkActionLogDao.update(frameworkActionLog);
//		else 		
//			frameworkActionLogDao.save(frameworkActionLog);
//		
//		return null;
//	}
	
	/**
	 * 新增修改
	 * 
	 * @param  POJO对象
	 * @return String
	 */
	public String update(FrameworkActionLog frameworkActionLog) {
    	// 保存申请信息
		myBatisDao.update(frameworkActionLog);
		
		return null;
	}
	
	/**
	 * 分页查询
	 * @param  POJO对象
	 * @param  pageIndex          当前页页数
	 * @param  pageSize           每页记录数
	 * @return Page
	 */
	public Page findFrameworkActionLogs(FrameworkActionLog frameworkActionLog,
			int pageIndex,
			int pageSize) {
		return myBatisDao.pageBy(null, null, frameworkActionLog, pageIndex, pageSize);
	}
	
	/**
	 * 根据主键对象获取信息
	 * 
	 * @param  POJO对象
	 * @return FrameworkActionLog
	 */
	public List<FrameworkActionLog> getFrameworkActionLogListByObj(FrameworkActionLog frameworkActionLog) {
		return frameworkActionLogDao.findListByObj(frameworkActionLog);
	}
	
	/**
	 * 根据主键获取信息
	 * 
	 * @param  POJO对象
	 * @return FrameworkActionLog
	 */
	public FrameworkActionLog getFrameworkActionLogByPk(int pk_Id) {
		return frameworkActionLogDao.findByPKId(pk_Id);
	}
	
	/**
	 * 根据条件获取信息
	 * 
	 * @param  POJO对象
	 * @return FrameworkActionLog返回第一个符合条件的对象，适合条件能唯一定位记录的应用场景
	 */
	public List<FrameworkActionLog> getFrameworkActionLogListBySql(FrameworkActionLog frameworkActionLog) {
		return frameworkActionLogDao.findListBySqlId("selectFrameworkActionLogs",frameworkActionLog);
	}	
}
