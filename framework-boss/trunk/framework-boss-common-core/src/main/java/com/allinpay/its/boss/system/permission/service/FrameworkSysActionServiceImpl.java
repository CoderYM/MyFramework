/*
 * 版权声明 .
 * 此文档的版权归通联支付网络服务有限公司所有
 * Powered By [Allinpay-Boss-framework]
 */

package com.allinpay.its.boss.system.permission.service;


import javax.annotation.Resource;
import java.util.List;

import com.allinpay.its.boss.framework.utils.Page;
import com.allinpay.its.boss.system.permission.dao.IFrameworkSysActionDao;
import com.allinpay.its.boss.system.permission.dao.impl.FrameworkSysActionDaoImpl;
import com.allinpay.its.boss.system.permission.model.FrameworkSysAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class FrameworkSysActionServiceImpl {
	@Autowired
	private IFrameworkSysActionDao frameworkSysActionDao;
	@Resource
	private FrameworkSysActionDaoImpl myBatisDao;
	
	
	/**
	 * 新增
	 * 
	 * @param  POJO对象
	 * @return String
	 */
	public String add(FrameworkSysAction frameworkSysAction) {
		// 保存申请信息
		myBatisDao.save(frameworkSysAction);
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
//	public String saveOrUpdate(FrameworkSysAction frameworkSysAction) {
//		
//    	// 保存申请信息
//		if(frameworkSysAction.getPk() != null)
//			frameworkSysActionDao.update(frameworkSysAction);
//		else 		
//			frameworkSysActionDao.save(frameworkSysAction);
//		
//		return null;
//	}
	
	/**
	 * 新增修改
	 * 
	 * @param  POJO对象
	 * @return String
	 */
	public String update(FrameworkSysAction frameworkSysAction) {
    	// 保存申请信息
		myBatisDao.update(frameworkSysAction);
		
		return null;
	}
	
	/**
	 * 分页查询
	 * @param  POJO对象
	 * @param  pageIndex          当前页页数
	 * @param  pageSize           每页记录数
	 * @return Page
	 */
	public Page findFrameworkSysActions(FrameworkSysAction frameworkSysAction,
			int pageIndex,
			int pageSize) {
		return myBatisDao.pageBy(null, null, frameworkSysAction, pageIndex, pageSize);
	}
	
	/**
	 * 根据主键对象获取信息
	 * 
	 * @param  POJO对象
	 * @return FrameworkSysAction
	 */
	public List<FrameworkSysAction> getFrameworkSysActionListByObj(FrameworkSysAction frameworkSysAction) {
		return frameworkSysActionDao.findListByObj(frameworkSysAction);
	}
	
	/**
	 * 根据主键获取信息
	 * 
	 * @param  POJO对象
	 * @return FrameworkSysAction
	 */
	public FrameworkSysAction getFrameworkSysActionByPk(int pk_Id) {
		return frameworkSysActionDao.findByPKId(pk_Id);
	}
	
	/**
	 * 根据条件获取信息
	 * 
	 * @param  POJO对象
	 * @return FrameworkSysAction返回第一个符合条件的对象，适合条件能唯一定位记录的应用场景
	 */
	public List<FrameworkSysAction> getFrameworkSysActionListBySql(FrameworkSysAction frameworkSysAction) {
		return frameworkSysActionDao.findListBySqlId("selectFrameworkSysActions",frameworkSysAction);
	}	
}
