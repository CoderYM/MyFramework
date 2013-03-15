package com.allinpay.its.boss.system.menu.service;

import java.util.ArrayList;
import java.util.List;

import com.allinpay.its.boss.framework.utils.Page;
import com.allinpay.its.boss.framework.utils.WebConstant;
import com.allinpay.its.boss.system.menu.dao.IFrameworkSysMenuDao;
import com.allinpay.its.boss.system.menu.dao.impl.FrameworkSysMenuDaoImpl;
import com.allinpay.its.boss.system.menu.model.FrameworkSysMenu;
import com.allinpay.its.boss.system.permission.model.FrameworkUserInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FrameworkSysMenuServiceImpl {

	@Autowired 
	private IFrameworkSysMenuDao iFrameworkSysMenuDao;
	
	@Autowired
	private FrameworkSysMenuDaoImpl frameworkSysMenuDaoImpl;

	public Page getFrameworkSysMenuList(FrameworkSysMenu obj) {
		Page page = frameworkSysMenuDaoImpl.pageBy(null, null, obj, obj.getPageNum(), obj.getNumPerPage());
		return page;
	}

	public void insertMenu(FrameworkSysMenu obj, int levelId) {
		if(levelId == 1){
			obj.setPid(new Long(0));
			obj.setIs_leaf(null);
		}else if (levelId == 2){
			obj.setIs_leaf("0");
		}else{
			obj.setIs_leaf("1");
		}
		
		frameworkSysMenuDaoImpl.save(obj);
	}

	public void update(FrameworkSysMenu obj) {
		frameworkSysMenuDaoImpl.update(obj);
	}

	public void delete(int menuId) {
		frameworkSysMenuDaoImpl.deleteById(menuId);
	}

	public FrameworkSysMenu getMenu(int menuId) {
		FrameworkSysMenu menu = iFrameworkSysMenuDao.getById(menuId);
		if(menu.getPid() != null && menu.getPid().intValue()>0){
			menu.setPmenu(iFrameworkSysMenuDao.getById(menu.getPid().intValue()));
		}
		return menu;
	}
	
	public List<FrameworkSysMenu> getMenuList(){
		return iFrameworkSysMenuDao.getMenuList();
	}
	public List<FrameworkSysMenu> getMenuList(FrameworkSysMenu menu){
		return iFrameworkSysMenuDao.getMenuList(menu);
	}

	public Page getFrameworkSysMenuListByLevelId(FrameworkSysMenu menu, int levelId) {
		Page page = null;
		if(levelId == 1){
			page = frameworkSysMenuDaoImpl.pageBy("selectmenulevel1", "selectmenulevel1Count", menu, menu.getPageNum(), menu.getNumPerPage());
		}else if(levelId == 2){
			page = frameworkSysMenuDaoImpl.pageBy("selectmenulevel2", "selectmenulevel2Count", menu, menu.getPageNum(), menu.getNumPerPage());
			if(page.getResult() != null && page.getResult().size()>0){
				for(Object object:page.getResult()){
					FrameworkSysMenu result = (FrameworkSysMenu)object;
					if(result.getPid() != null && result.getPid().intValue()>0){
						result.setPmenu(iFrameworkSysMenuDao.getById(result.getPid().intValue()));
					}
				}
			}
		}else{
			page = frameworkSysMenuDaoImpl.pageBy("selectmenulevel3", "selectmenulevel3Count", menu, menu.getPageNum(), menu.getNumPerPage());
			if(page.getResult() != null && page.getResult().size()>0){
				for(Object object:page.getResult()){
					FrameworkSysMenu result = (FrameworkSysMenu)object;
					if(result.getPid() != null && result.getPid().intValue()>0){
						result.setPmenu(iFrameworkSysMenuDao.getById(result.getPid().intValue()));
					}
					
					if(result.getPmenu() != null && result.getPmenu().getPid() != null && result.getPmenu().getPid().intValue()>0){
						result.setPpmenu(iFrameworkSysMenuDao.getById(result.getPmenu().getPid().intValue()));
					}
				}
			}
		}
		return page;
	}

	public List<FrameworkSysMenu> getFrameworkSysMenuListByLevelId(int levelId,FrameworkUserInf userInf) {
		List<FrameworkSysMenu> allLists = null;
		List<FrameworkSysMenu> userMenuList= getSysMenuByUserRole(userInf.getId());
		
		if(levelId == 1){
			allLists = frameworkSysMenuDaoImpl.findBy("selectmenulevel1", null);
		}else if(levelId == 2){
			allLists = frameworkSysMenuDaoImpl.findBy("selectmenulevel2", null);
		}else{
			allLists = frameworkSysMenuDaoImpl.findBy("selectmenulevel3", null);
		}
		//权限过滤,方法很不好以后在优化,请见谅
		List<FrameworkSysMenu>  resultList = new ArrayList<FrameworkSysMenu>();
		if(userInf.getUserName().equalsIgnoreCase(WebConstant.SUPPER_MANAGER_USER)){ //如果管理员
			return allLists;
		}else{ //非管理员在这里进行过滤
			if(userMenuList != null && userMenuList.size()>0 ){
				for(FrameworkSysMenu allList:allLists){
					Long allId = allList.getId();
					for(FrameworkSysMenu userMenu:userMenuList){
						if(allId.equals(userMenu.getId())){
							resultList.add(allList);
						}
					}
				}
			}
		}
		return resultList;
	}
	
	public List<Integer> getUserPermSysMenuIdByUserId(int userId){
		List<FrameworkSysMenu> menuList = iFrameworkSysMenuDao.getUserPermSysMenuIdByUserId(userId);
		List<Integer> idList = new ArrayList<Integer>();
		for(FrameworkSysMenu menu: menuList){
			idList.add(menu.getId().intValue());
		}
		return idList;
	}
	public List<FrameworkSysMenu> getUserPermSysMenuIdByPid(List<Integer> pidList){
		return iFrameworkSysMenuDao.getUserPermSysMenuIdByPid(pidList);
	}
	
	/**
	 *  获得用户ID对应的菜单列表
	 * @param userId
	 * @return
	 */
	public List<FrameworkSysMenu> getSysMenuByUserRole(Long userId){
		List<FrameworkSysMenu>  menuList = new ArrayList<FrameworkSysMenu>();
		List<Integer> idList = getUserPermSysMenuIdByUserId(userId.intValue());
		if(idList == null || idList.size() == 0){
			return menuList;
		}
		menuList = getUserPermSysMenuIdByPid(idList);
		return menuList;
	}

	public List<FrameworkSysMenu> getFrameworkSysMenuListBySubMenuList(List<FrameworkSysMenu> subMenuList) {
		List<FrameworkSysMenu> returnList = new ArrayList<FrameworkSysMenu>();
		if(subMenuList != null && subMenuList.size()>0){
			for(FrameworkSysMenu menu:subMenuList){
				if(menu.getPid() != null && menu.getPid().intValue()>0){
					FrameworkSysMenu pmenu = iFrameworkSysMenuDao.getById(menu.getPid().intValue());
					boolean isAt = false;
					for(FrameworkSysMenu cmenu:returnList){
						if(cmenu.getId().intValue() == pmenu.getId().intValue()){
							isAt = true;
						}
					}
					if(!isAt){
						returnList.add(pmenu);
					}
				}
			}
		}
		return returnList;
	}

}
