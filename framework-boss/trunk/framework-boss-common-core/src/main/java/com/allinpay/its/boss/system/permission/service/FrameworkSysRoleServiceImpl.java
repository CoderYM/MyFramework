/*
 * 版权声明 .
 * 此文档的版权归通联支付网络服务有限公司所有
 * Powered By [Allinpay-Boss-framework]
 */

package com.allinpay.its.boss.system.permission.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.allinpay.its.boss.framework.utils.Page;
import com.allinpay.its.boss.framework.utils.SessionUtil;
import com.allinpay.its.boss.framework.utils.WebConstant;
import com.allinpay.its.boss.system.menu.model.FrameworkSysMenu;
import com.allinpay.its.boss.system.menu.service.FrameworkSysMenuServiceImpl;
import com.allinpay.its.boss.system.permission.dao.IFrameworkSysRoleDao;
import com.allinpay.its.boss.system.permission.dao.impl.FrameworkSysRoleDaoImpl;
import com.allinpay.its.boss.system.permission.model.FrameworkPermAssign;
import com.allinpay.its.boss.system.permission.model.FrameworkSysPermission;
import com.allinpay.its.boss.system.permission.model.FrameworkSysRole;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class FrameworkSysRoleServiceImpl {
	@Autowired
	private IFrameworkSysRoleDao frameworkSysRoleDao;

	@Resource
	private FrameworkSysRoleDaoImpl myBatisDao;

	@Resource
	private FrameworkPermAssignServiceImpl frameworkPermAssignService;

	@Resource
	private FrameworkSysMenuServiceImpl menuService;

	@Autowired
	private FrameworkSysPermissionServiceImpl frameworkSysPermissionService;

	/**
	 * 新增
	 * 
	 * @param POJO对象
	 * @return String
	 */
	public String add(FrameworkSysRole frameworkSysRole) {
		// 保存申请信息
		myBatisDao.save(frameworkSysRole);
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
	// public String saveOrUpdate(FrameworkSysRole frameworkSysRole) {
	//
	// // 保存申请信息
	// if(frameworkSysRole.getPk() != null)
	// frameworkSysRoleDao.update(frameworkSysRole);
	// else
	// frameworkSysRoleDao.save(frameworkSysRole);
	//
	// return null;
	// }

	/**
	 * 新增修改
	 * 
	 * @param POJO对象
	 * @return String
	 */
	public String update(FrameworkSysRole frameworkSysRole) {
		// 保存申请信息
		myBatisDao.update(frameworkSysRole);

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
	public Page findFrameworkSysRoles(FrameworkSysRole frameworkSysRole,
			int pageIndex, int pageSize) {
		frameworkSysRole.setState(WebConstant.DATA_EXIST);
		return myBatisDao.pageBy(null, null, frameworkSysRole, pageIndex,
				pageSize);
	}

	/**
	 * 根据主键对象获取信息
	 * 
	 * @param POJO对象
	 * @return FrameworkSysRole
	 */
	public List<FrameworkSysRole> getFrameworkSysRoleListByObj(
			FrameworkSysRole frameworkSysRole) {
		return frameworkSysRoleDao.findListByObj(frameworkSysRole);
	}

	/**
	 * 根据主键获取信息
	 * 
	 * @param POJO对象
	 * @return FrameworkSysRole
	 */
	public FrameworkSysRole getFrameworkSysRoleByPk(int pk_Id) {
		return frameworkSysRoleDao.findByPKId(pk_Id);
	}

	/**
	 * 根据条件获取信息
	 * 
	 * @param POJO对象
	 * @return FrameworkSysRole返回第一个符合条件的对象，适合条件能唯一定位记录的应用场景
	 */
	public List<FrameworkSysRole> getFrameworkSysRoleListBySql(
			FrameworkSysRole frameworkSysRole) {
		return frameworkSysRoleDao.findListBySqlId("selectFrameworkSysRoles",
				frameworkSysRole);
	}

	/**
	 * 保存系统角色操作
	 * 
	 * @param model
	 */
	public void saveSysRole(FrameworkSysRole model) {
		model.setState(WebConstant.DATA_EXIST);
		// model.setCreateUserId();
		// model.setModifyUserId("");
		add(model);
		String[] permits = null;
		String pmcheckStrs = model.getPmcheckStrs();
		if(StringUtils.isNotBlank(pmcheckStrs)){
			
			permits = pmcheckStrs.split(",");// 权限集合
		}
		if (permits != null && permits.length > 0) {// 添加角色权限
			FrameworkPermAssign permAssign = null;
			for (String permitId : permits) {
				permAssign = new FrameworkPermAssign();
				permAssign.setUserDepId(model.getId());
				permAssign.setUserDepType(WebConstant.PERMASSIGN_ROLE);
				permAssign.setPermId(Long.valueOf(permitId));
				permAssign.setState(WebConstant.DATA_EXIST);
				permAssign.setCreateTime(new Date());
				// permAssign.setCreateUserId(getCurrentUserId());
				permAssign.setModifyTime(new Date());
				// permAssign.setModifyUserId(getCurrentUserId());

				// FrameworkSysPermission permit = frameworkSysPermissionService
				// .getFrameworkSysPermissionByPk(permAssign.getPermId()
				// .intValue());
				frameworkPermAssignService.add(permAssign);
				permAssign = null;
			}
		}

	}

	public void modifySysRole(FrameworkSysRole model) {
		model.setState(WebConstant.DATA_EXIST);
		model.setModifyTime(new Date());
		// model.setModifyUserId(this.getCurrentUserId());

		myBatisDao.update(model);
		String[] permits = null;
		String pmcheckStrs = model.getPmcheckStrs();
		if(StringUtils.isNotBlank(pmcheckStrs)){
			permits = pmcheckStrs.split(",");// 权限集合
		}
		if (permits != null && permits.length > 0) {// 修改角色权限
			// 先进行删除操作
			frameworkPermAssignService.delPermAssign(model.getId());
			for (String permitId : permits) {
				FrameworkPermAssign permAssign = frameworkPermAssignService
						.findPermAssign(model.getId(), Long.parseLong(permitId));
				if (permAssign == null || permAssign.getId() == null) {
					permAssign = new FrameworkPermAssign();
				}
				permAssign.setUserDepId(model.getId()); // 角色ID
				permAssign.setUserDepType(WebConstant.PERMASSIGN_ROLE);
				permAssign.setPermId(Long.valueOf(permitId));
				permAssign.setState(WebConstant.DATA_EXIST);
				permAssign.setModifyTime(new Date());
				// permAssign.setModifyUserId(this.getCurrentUserId());

				if (permAssign == null || permAssign.getId() == null) {
					permAssign.setCreateTime(new Date());
					// permAssign.setCreateUserId(this.getCurrentUserId());
					frameworkPermAssignService.add(permAssign);
				} else {
					frameworkPermAssignService.update(permAssign);
				}
			}
		}

	}

	public void initModifySysRole(int pk_Id, Model springModel,Map<String, Object> userInfMap) {
		//当前登录用户信息
		String userName = SessionUtil.getLoginUserName(userInfMap);
		Long userId = SessionUtil.getLoginUserId(userInfMap);
		FrameworkSysRole frameworkSysRole = getFrameworkSysRoleByPk(pk_Id);

		List<FrameworkPermAssign> listPermAssign = frameworkPermAssignService
				.getFrameworkPermAssignListByObj(new FrameworkPermAssign(
						frameworkSysRole.getId(), WebConstant.PERMASSIGN_ROLE,
						WebConstant.DATA_EXIST));

		StringBuffer rolePermitsBuffer = new StringBuffer();
		for (FrameworkPermAssign permAssign : listPermAssign) {
			rolePermitsBuffer.append(permAssign.getPermId() + ",");
		}

		springModel.addAttribute("rolePermits", rolePermitsBuffer.toString());

		// 权限列表查询
		List<FrameworkSysMenu> listMenu = menuService.getMenuList();
		List<FrameworkSysMenu> parentListMenu = menuService
				.getMenuList(new FrameworkSysMenu(Long.parseLong("0")));// 一级菜单

		// List<FrameworkSysMenu> nlistMenu = new ArrayList<FrameworkSysMenu>();
		HashMap<Long, List<FrameworkSysMenu>> hashMap = new HashMap<Long, List<FrameworkSysMenu>>();
		if(StringUtils.isNotBlank(userName) && userId != null){
		FrameworkSysPermission permission = null;
		List<FrameworkSysPermission> listPermission = null;
		for (FrameworkSysMenu menu : listMenu) {
			if (!StringUtils.equals("0", String.valueOf(menu.getPid()))) {// 除了一级菜单
				//针对超级管理员不过滤
				if(StringUtils.endsWithIgnoreCase(userName, SessionUtil.SUPPERUSERS)){
					
					permission = new FrameworkSysPermission(null, menu.getId());
					listPermission = frameworkSysPermissionService
							.getFrameworkSysPermissionListByObj(permission);
				}else{
					listPermission = frameworkSysPermissionService
							.getPermListByUserIdOrMenuId(userId.intValue(), menu.getId().intValue());
				}
				if (listPermission != null && listPermission.size() > 0) {
					menu.setListPermission(listPermission);
				}
				List<FrameworkSysMenu> childList = new ArrayList<FrameworkSysMenu>();
				if (hashMap.get(menu.getPid()) != null) {
					childList = (List<FrameworkSysMenu>) hashMap.get(menu
							.getPid());
				}
				childList.add(menu);
				hashMap.put(menu.getPid(), childList);
				permission = null;
			}
			// nlistMenu.add(menu);
		}
		}
		springModel.addAttribute("parentListMenu", parentListMenu);// 一级菜单
		springModel.addAttribute("hashMap", hashMap);// 三级菜单

		springModel.addAttribute("infos", frameworkSysRole);

	}

	/**
	 * 新增界面初始化数据
	 * 
	 * @param model
	 */
	public void addFrameworkSysRoleToPage(Model model,Map<String, Object> userInfMap) {
		//当前登录用户信息
		String userName = SessionUtil.getLoginUserName(userInfMap);
		Long userId = SessionUtil.getLoginUserId(userInfMap);
		List<FrameworkSysMenu> listMenu = null;
		List<FrameworkSysMenu> parentListMenu = menuService
				.getMenuList(new FrameworkSysMenu(Long.parseLong("0")));// 一级菜单
		HashMap<Long, List<FrameworkSysMenu>> hashMap = new HashMap<Long, List<FrameworkSysMenu>>();
		if(StringUtils.isNotBlank(userName) && userId != null){
			listMenu = menuService.getMenuList();
		// List<FrameworkSysMenu> nlistMenu = new ArrayList<FrameworkSysMenu>();
		FrameworkSysPermission permission = null;
		List<FrameworkSysPermission> listPermission = null;
		//
		for (FrameworkSysMenu menu : listMenu) {
			if (!StringUtils.equals("0", String.valueOf(menu.getPid()))) {// 除了一级菜单
				//针对超级管理员不过滤
					if(StringUtils.endsWithIgnoreCase(userName, SessionUtil.SUPPERUSERS)){
						
						permission = new FrameworkSysPermission(null, menu.getId());
						listPermission = frameworkSysPermissionService
								.getFrameworkSysPermissionListByObj(permission);
					}else{
						listPermission = frameworkSysPermissionService
						.getPermListByUserIdOrMenuId(userId.intValue(), menu.getId().intValue());
					}
				}
				if (listPermission != null && listPermission.size() > 0) {
					menu.setListPermission(listPermission);
				}
				List<FrameworkSysMenu> childList = new ArrayList<FrameworkSysMenu>();
				if (hashMap.get(menu.getPid()) != null) {
					childList = (List<FrameworkSysMenu>) hashMap.get(menu
							.getPid());
				}
				childList.add(menu);
				hashMap.put(menu.getPid(), childList);
				permission = null;
			}
			// nlistMenu.add(menu);
		}

		model.addAttribute("parentListMenu", parentListMenu);// 一级菜单
		model.addAttribute("hashMap", hashMap);// 三级菜单

	}

	/**
	 * 删除角色
	 * @param pk_Id
	 */
	public void deleteSysRole(int pk_Id) {
		// 先进行删除操作
		frameworkPermAssignService.delPermAssign(Long.parseLong(String
				.valueOf(pk_Id)));
		deleteSysrole(pk_Id);

	}
	
	/**
	 * 逻辑删除角色
	 * @param pk_Id
	 */
	public void deleteSysrole(int pk_Id){
		FrameworkSysRole sysRole = new FrameworkSysRole();
		sysRole.setId(Long.parseLong(String.valueOf(pk_Id)));
		sysRole.setState(WebConstant.DATA_DEL);
		update(sysRole);
	}
}
