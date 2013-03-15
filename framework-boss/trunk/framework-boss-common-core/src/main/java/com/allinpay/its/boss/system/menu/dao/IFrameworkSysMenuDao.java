package com.allinpay.its.boss.system.menu.dao;

import java.util.List;

import com.allinpay.its.boss.framework.repository.mybatis.MyBatisRepository;
import com.allinpay.its.boss.system.menu.model.FrameworkSysMenu;

@MyBatisRepository 
public interface IFrameworkSysMenuDao {

	public FrameworkSysMenu getById(int menuId);
	public List<FrameworkSysMenu> getMenuList();
	public List<FrameworkSysMenu> getMenuList(FrameworkSysMenu menu);
	
	/**
	 * 根据用户ID拿到有权限的菜单PID
	 * @param userId
	 * @return
	 */
	public List<FrameworkSysMenu> getUserPermSysMenuIdByUserId(int userId);
	public List<FrameworkSysMenu> getUserPermSysMenuIdByPid(List<Integer> pid);
	
	
}
