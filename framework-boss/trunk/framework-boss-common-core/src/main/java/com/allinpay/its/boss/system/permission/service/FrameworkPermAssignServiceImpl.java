/*
 * 版权声明 .
 * 此文档的版权归通联支付网络服务有限公司所有
 * Powered By [Allinpay-Boss-framework]
 */

package com.allinpay.its.boss.system.permission.service;


import java.util.List;

import javax.annotation.Resource;

import com.allinpay.its.boss.framework.utils.Page;
import com.allinpay.its.boss.framework.utils.WebConstant;
import com.allinpay.its.boss.system.permission.dao.IFrameworkPermAssignDao;
import com.allinpay.its.boss.system.permission.dao.impl.FrameworkPermAssignDaoImpl;
import com.allinpay.its.boss.system.permission.model.FrameworkPermAssign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class FrameworkPermAssignServiceImpl {
	@Autowired
	private IFrameworkPermAssignDao frameworkPermAssignDao;
	@Resource
	private FrameworkPermAssignDaoImpl myBatisDao;
	
	
	/**
	 * 新增
	 * 
	 * @param  POJO对象
	 * @return String
	 */
	public String add(FrameworkPermAssign frameworkPermAssign) {
		// 保存申请信息
		myBatisDao.save(frameworkPermAssign);
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
//	public String saveOrUpdate(FrameworkPermAssign frameworkPermAssign) {
//		
//    	// 保存申请信息
//		if(frameworkPermAssign.getPk() != null)
//			frameworkPermAssignDao.update(frameworkPermAssign);
//		else 		
//			frameworkPermAssignDao.save(frameworkPermAssign);
//		
//		return null;
//	}
	
	/**
	 * 新增修改
	 * 
	 * @param  POJO对象
	 * @return String
	 */
	public String update(FrameworkPermAssign frameworkPermAssign) {
    	// 保存申请信息
		myBatisDao.update(frameworkPermAssign);
		
		return null;
	}
	
	/**
	 * 分页查询
	 * @param  POJO对象
	 * @param  pageIndex          当前页页数
	 * @param  pageSize           每页记录数
	 * @return Page
	 */
	public Page findFrameworkPermAssigns(FrameworkPermAssign frameworkPermAssign,
			int pageIndex,
			int pageSize) {
		return myBatisDao.pageBy(null, null, frameworkPermAssign, pageIndex, pageSize);
	}
	
	/**
	 * 根据主键对象获取信息
	 * 
	 * @param  POJO对象
	 * @return FrameworkPermAssign
	 */
	public List<FrameworkPermAssign> getFrameworkPermAssignListByObj(FrameworkPermAssign frameworkPermAssign) {
		return frameworkPermAssignDao.findListByObj(frameworkPermAssign);
	}
	
	/**
	 * 根据主键获取信息
	 * 
	 * @param  POJO对象
	 * @return FrameworkPermAssign
	 */
	public FrameworkPermAssign getFrameworkPermAssignByPk(int pk_Id) {
		return frameworkPermAssignDao.findByPKId(pk_Id);
	}
	
	/**
	 * 根据条件获取信息
	 * 
	 * @param  POJO对象
	 * @return FrameworkPermAssign返回第一个符合条件的对象，适合条件能唯一定位记录的应用场景
	 */
	public List<FrameworkPermAssign> getFrameworkPermAssignListBySql(FrameworkPermAssign frameworkPermAssign) {
		return frameworkPermAssignDao.findListBySqlId("selectFrameworkPermAssigns",frameworkPermAssign);
	}	
	
	
	public void delPermAssign(Long roleId){
		FrameworkPermAssign frameworkPermAssign = new FrameworkPermAssign();
		frameworkPermAssign.setUserDepId(roleId);
		frameworkPermAssign.setState(WebConstant.DATA_DEL);
		frameworkPermAssign.setUserDepType(WebConstant.PERMASSIGN_ROLE);
		frameworkPermAssignDao.updateFrameworkPermAssignBySql(frameworkPermAssign);
		
	}
	public FrameworkPermAssign findPermAssign(Long roleId, Long permitId){
		FrameworkPermAssign frameworkPermAssign = new FrameworkPermAssign();
		frameworkPermAssign.setUserDepId(roleId);
		frameworkPermAssign.setPermId(permitId);
		frameworkPermAssign.setState(WebConstant.DATA_EXIST);
		frameworkPermAssign.setUserDepType(WebConstant.PERMASSIGN_ROLE);
		List<FrameworkPermAssign> lists= getFrameworkPermAssignListByObj(frameworkPermAssign);
		if(lists != null && lists.size()>0){
			return lists.get(0);
		}else{
			return null;
		}
		
	}
	
}
