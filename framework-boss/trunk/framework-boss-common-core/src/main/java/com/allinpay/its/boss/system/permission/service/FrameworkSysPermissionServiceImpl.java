/*
 * 版权声明 .
 * 此文档的版权归通联支付网络服务有限公司所有
 * Powered By [Allinpay-Boss-framework]
 */

package com.allinpay.its.boss.system.permission.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.allinpay.its.boss.framework.utils.JsonUtil;
import com.allinpay.its.boss.framework.utils.Page;
import com.allinpay.its.boss.framework.utils.WebConstant;
import com.allinpay.its.boss.system.permission.dao.IFrameworkSysPermissionDao;
import com.allinpay.its.boss.system.permission.dao.impl.FrameworkSysPermissionDaoImpl;
import com.allinpay.its.boss.system.permission.model.ActionCheck;
import com.allinpay.its.boss.system.permission.model.FrameworkSysAction;
import com.allinpay.its.boss.system.permission.model.FrameworkSysPermission;
import com.allinpay.its.boss.system.permission.model.PermissionCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class FrameworkSysPermissionServiceImpl {
	@Autowired
	private IFrameworkSysPermissionDao frameworkSysPermissionDao;
	@Resource
	private FrameworkSysPermissionDaoImpl myBatisDao;

	@Resource
	private FrameworkSysActionServiceImpl sysActionService;

	/**
	 * 新增
	 * 
	 * @param POJO对象
	 * @return String
	 */
	public String add(FrameworkSysPermission frameworkSysPermission) {
		// 保存申请信息
		frameworkSysPermission.setState(WebConstant.DATA_EXIST);
		myBatisDao.save(frameworkSysPermission);
		return null;
	}

	/**
	 * 删除
	 * 
	 * @param POJO对象
	 * @return String
	 */
	public String delete(int pk_id) {
		myBatisDao.deleteById(pk_id);
		return null;
	}

	/**
	 * 新增修改 有唯一主键，且主键自动生成不可编辑时
	 * 
	 * @param POJO对象
	 * @return String
	 */
	// public String saveOrUpdate(FrameworkSysPermission frameworkSysPermission)
	// {
	//
	// // 保存申请信息
	// if(frameworkSysPermission.getPk() != null)
	// frameworkSysPermissionDao.update(frameworkSysPermission);
	// else
	// frameworkSysPermissionDao.save(frameworkSysPermission);
	//
	// return null;
	// }

	/**
	 * 新增修改
	 * 
	 * @param POJO对象
	 * @return String
	 */
	public String update(FrameworkSysPermission frameworkSysPermission) {
		// 保存申请信息
		myBatisDao.update(frameworkSysPermission);

		return null;
	}

	/**
	 * 分页查询
	 * 
	 * @param POJO对象
	 * @param pageIndex
	 *            当前页页数
	 * @param pageSize
	 *            每页记录数
	 * @return Page
	 */
	public Page findFrameworkSysPermissions(
			FrameworkSysPermission frameworkSysPermission, int pageIndex,
			int pageSize) {
		return myBatisDao.pageBy(null, null, frameworkSysPermission, pageIndex,
				pageSize);
	}

	/**
	 * 根据主键对象获取信息
	 * 
	 * @param POJO对象
	 * @return FrameworkSysPermission
	 */
	public List<FrameworkSysPermission> getFrameworkSysPermissionListByObj(
			FrameworkSysPermission frameworkSysPermission) {
		return frameworkSysPermissionDao.findListByObj(frameworkSysPermission);
	}

	/**
	 * 根据主键获取信息
	 * 
	 * @param POJO对象
	 * @return FrameworkSysPermission
	 */
	public FrameworkSysPermission getFrameworkSysPermissionByPk(int pk_Id) {
		return frameworkSysPermissionDao.findByPKId(pk_Id);
	}

	/**
	 * 根据条件获取信息
	 * 
	 * @param POJO对象
	 * @return FrameworkSysPermission返回第一个符合条件的对象，适合条件能唯一定位记录的应用场景
	 */
	public List<FrameworkSysPermission> getFrameworkSysPermissionListBySql(
			FrameworkSysPermission frameworkSysPermission) {
		return frameworkSysPermissionDao.findListBySqlId(
				"selectFrameworkSysPermissions", frameworkSysPermission);
	}

	/**
	 * 
	 * Description: 类似白名单,如果Action表中没有相应配置则默认通过
	 * 
	 * @return
	 * @param reqActionName
	 * @param methodname
	 * @param clsName
	 * @return
	 */
	public ActionCheck checkActionAccess(String reqActionName,
			String methodname, String clsName) {
		ActionCheck actionCheck = new ActionCheck(false, "");
		// 在从缓存中取所有Action信息
		List<FrameworkSysAction> bossSysActionLists = (List<FrameworkSysAction>) sysActionService
				.getFrameworkSysActionListByObj(null);
		if (bossSysActionLists != null && !bossSysActionLists.isEmpty()) {
			boolean b = false;
			for (FrameworkSysAction action : bossSysActionLists) {
				if (StringUtils.equals(reqActionName, action.getActionName())
						&& StringUtils.equals(methodname,
								action.getMethodName())
						&& StringUtils.equals(clsName,
								action.getSimpleClassName())) {
					b = true;// 这个请求action存在Action配置表中
					actionCheck.setActionDesc(action.getActionDes());
				}
			}
			if (!b) { // 如果不存在配置表中直接允许通过
				actionCheck.setAccess(true);
			}
		}
		return actionCheck;
	}

	/**
	 * 
	 * Description: 查询该用户是否有访问Action的权限
	 * 
	 * @return
	 * @param userId
	 * @param reqActionName
	 * @param methodname
	 * @param clsName
	 * @return
	 */
	public ActionCheck checkActionAccessByUserId(String userId,
			String reqActionName, String methodname, String clsName) {
		ActionCheck actionCheck = new ActionCheck(false, "");
		// 先直接从缓存中取该用户的所有权限编码
		// List<String> pCodeList = MbMchtConfig.getPCodeByUserId(userId);
		List<FrameworkSysPermission> permitList = frameworkSysPermissionDao
				.getPermListByUserIdOrMenuId(Integer.parseInt(userId), 0);
		List<String> pCodeList = new ArrayList<String>();
		if (permitList != null && permitList.size() > 0) {
			for (FrameworkSysPermission per : permitList) {
				pCodeList.add(per.getPermissionCode());
			}
		}
		if (pCodeList != null && !pCodeList.isEmpty()) {
			// 在从缓存中取所有Action信息
			// List<BossSysAction> bossSysActionLists =
			// MbMchtConfig.getAllBossSysAction();
			List<FrameworkSysAction> bossSysActionLists = (List<FrameworkSysAction>) sysActionService
					.getFrameworkSysActionListByObj(null);
			if (bossSysActionLists != null && !bossSysActionLists.isEmpty()) {
				for (FrameworkSysAction action : bossSysActionLists) {
					for (String pCode : pCodeList) {
						if (StringUtils.equals(pCode,
								action.getPermissionCode())
								&& StringUtils.equals(reqActionName,
										action.getActionName())
								&& StringUtils.equals(methodname,
										action.getMethodName())
								&& StringUtils.equals(clsName,
										action.getSimpleClassName())) {

							actionCheck.setAccess(true);
							actionCheck.setActionDesc(action.getActionDes());
						}
					}

				}
			}
		}
		return actionCheck;
	}

	/**
	 * 通过当前用户获得该用户的所有权限编码到前台进行页面元素过滤
	 * 
	 * @param request
	 */
	public String getPermissionCodeByUserId(Long userId) {
		List<PermissionCode> permList = null;
		// 取得当前用户所拥有的权限编码
		List<FrameworkSysPermission> userPermitList = frameworkSysPermissionDao
				.getPermListByUserIdOrMenuId(userId.intValue(), 0);
		if (userPermitList != null && !userPermitList.isEmpty()) {
			permList = new ArrayList<PermissionCode>();
			for (FrameworkSysPermission permission : userPermitList) {
				permList.add(new PermissionCode(permission.getPermissionCode(),
						permission.getPermissionName()));
			}
		}
			return JsonUtil.toJSONStrByObject(permList);
		
	}
	
	public List<FrameworkSysPermission> getPermListByUserIdOrMenuId(int userId,int menuId){
		return frameworkSysPermissionDao
				.getPermListByUserIdOrMenuId(userId, menuId);
	}

}
