/*
 * 版权声明 .
 * 此文档的版权归通联支付网络服务有限公司所有
 * Powered By [Allinpay-Boss-framework]
 */

package com.allinpay.its.boss.system.permission.service;


import javax.annotation.Resource;
import java.util.List;

import com.allinpay.its.boss.framework.utils.Page;
import com.allinpay.its.boss.framework.utils.WebConstant;
import com.allinpay.its.boss.system.permission.dao.IFrameworkUserRoleDao;
import com.allinpay.its.boss.system.permission.dao.impl.FrameworkUserRoleDaoImpl;
import com.allinpay.its.boss.system.permission.model.FrameworkUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class FrameworkUserRoleServiceImpl {
	@Autowired
	private IFrameworkUserRoleDao frameworkUserRoleDao;
	@Resource
	private FrameworkUserRoleDaoImpl myBatisDao;
	
	
	/**
	 * 新增
	 * 
	 * @param  POJO对象
	 * @return String
	 */
	public String add(FrameworkUserRole frameworkUserRole) {
		// 保存申请信息
		myBatisDao.save(frameworkUserRole);
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
//	public String saveOrUpdate(FrameworkUserRole frameworkUserRole) {
//		
//    	// 保存申请信息
//		if(frameworkUserRole.getPk() != null)
//			frameworkUserRoleDao.update(frameworkUserRole);
//		else 		
//			frameworkUserRoleDao.save(frameworkUserRole);
//		
//		return null;
//	}
	
	/**
	 * 新增修改
	 * 
	 * @param  POJO对象
	 * @return String
	 */
	public String update(FrameworkUserRole frameworkUserRole) {
    	// 保存申请信息
		myBatisDao.update(frameworkUserRole);
		
		return null;
	}
	
	/**
	 * 分页查询
	 * @param  POJO对象
	 * @param  pageIndex          当前页页数
	 * @param  pageSize           每页记录数
	 * @return Page
	 */
	public Page findFrameworkUserRoles(FrameworkUserRole frameworkUserRole,
			int pageIndex,
			int pageSize) {
		return myBatisDao.pageBy(null, null, frameworkUserRole, pageIndex, pageSize);
	}
	
	/**
	 * 根据主键对象获取信息
	 * 
	 * @param  POJO对象
	 * @return FrameworkUserRole
	 */
	public List<FrameworkUserRole> getFrameworkUserRoleListByObj(FrameworkUserRole frameworkUserRole) {
		return frameworkUserRoleDao.findListByObj(frameworkUserRole);
	}
	
	/**
	 * 根据主键获取信息
	 * 
	 * @param  POJO对象
	 * @return FrameworkUserRole
	 */
	public FrameworkUserRole getFrameworkUserRoleByPk(int pk_Id) {
		return frameworkUserRoleDao.findByPKId(pk_Id);
	}
	
	/**
	 * 根据条件获取信息
	 * 
	 * @param  POJO对象
	 * @return FrameworkUserRole返回第一个符合条件的对象，适合条件能唯一定位记录的应用场景
	 */
	public List<FrameworkUserRole> getFrameworkUserRoleListBySql(FrameworkUserRole frameworkUserRole) {
		return frameworkUserRoleDao.findListBySqlId("selectFrameworkUserRoles",frameworkUserRole);
	}	
	
	/**
	 * 逻辑删除用户角色
	 * @param userId
	 */
	public void delUserRole(Long userId){
		FrameworkUserRole frameworkUserRole = new FrameworkUserRole();
		frameworkUserRole.setState(WebConstant.DATA_DEL);
		frameworkUserRole.setUserId(userId);
		frameworkUserRoleDao.updateFrameworkUserRoleBySql(frameworkUserRole);
	}
	
	public FrameworkUserRole findUserRole(Long userId,Long roleId){
		FrameworkUserRole frameworkUserRole = new FrameworkUserRole();
		frameworkUserRole.setState(WebConstant.DATA_EXIST);
		frameworkUserRole.setUserId(userId);
		frameworkUserRole.setSysRoleId(roleId);
		List<FrameworkUserRole> list = getFrameworkUserRoleListByObj(frameworkUserRole);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
