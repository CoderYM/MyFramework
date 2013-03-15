/*
 * 版权声明 .
 * 此文档的版权归通联支付网络服务有限公司所有
 * Powered By [Allinpay-Boss-framework]
 */

package com.allinpay.its.boss.system.permission.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.allinpay.its.boss.framework.enums.EUserInfState;
import com.allinpay.its.boss.framework.utils.Page;
import com.allinpay.its.boss.framework.utils.SessionUtil;
import com.allinpay.its.boss.framework.utils.StringUtil;
import com.allinpay.its.boss.framework.utils.WebConstant;
import com.allinpay.its.boss.system.menu.service.FrameworkSysMenuServiceImpl;
import com.allinpay.its.boss.system.permission.dao.IFrameworkUserInfDao;
import com.allinpay.its.boss.system.permission.dao.impl.FrameworkUserInfDaoImpl;
import com.allinpay.its.boss.system.permission.model.FrameworkSysRole;
import com.allinpay.its.boss.system.permission.model.FrameworkUserInf;
import com.allinpay.its.boss.system.permission.model.FrameworkUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class FrameworkUserInfServiceImpl {
	@Autowired
	private IFrameworkUserInfDao frameworkUserInfDao;
	@Resource
	private FrameworkUserInfDaoImpl myBatisDao;
	
	@Resource
	private FrameworkSysRoleServiceImpl frameworkSysRoleService;
	@Resource
	private FrameworkUserRoleServiceImpl frameworkUserRoleService;
	@Resource
	private FrameworkSysMenuServiceImpl sysMenuService;
	
	/**
	 * 新增
	 * 
	 * @param  POJO对象
	 * @return String
	 */
	public String add(FrameworkUserInf frameworkUserInf) {
		
		// 保存申请信息
		frameworkUserInf.setUserPassword(StringUtil.encodePassword(frameworkUserInf.getUserPassword()));
		frameworkUserInf.setState(EUserInfState.NORMAL.getValue()+""); //操作员状态正常
		frameworkUserInf.setRecUpdUsr("test");
		frameworkUserInf.setRowCrtTs(new Date()); //创建时间
		frameworkUserInf.setRecUpdTs(new Date()); //最后更新时间
		myBatisDao.save(frameworkUserInf);
		String[] roleIds = frameworkUserInf.getRoleIds();
		//添加角色
				if(roleIds!=null && roleIds.length>0){
					for(String roleId : roleIds){
						FrameworkUserRole urole = new FrameworkUserRole();
						urole.setUserId(frameworkUserInf.getId());
						urole.setSysRoleId(Long.valueOf(roleId));
						urole.setState(WebConstant.DATA_EXIST);
						urole.setCreateTime(new Date());
//						urole.setCreateUserId(Long.parseLong(SessionUtil.getLoginInnerUserId()));
						urole.setModifyTime(new Date());
//						urole.setModifyUserId(Long.parseLong(SessionUtil.getLoginInnerUserId()));
						
//						FrameworkSysRole role = frameworkSysRoleService.getFrameworkSysRoleByPk(Integer.parseInt(roleId));
						//roleNames += role.getSysRoleName() + ",";
						frameworkUserRoleService.add(urole);
					}
				}
		
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
//	public String saveOrUpdate(FrameworkUserInf frameworkUserInf) {
//		
//    	// 保存申请信息
//		if(frameworkUserInf.getPk() != null)
//			frameworkUserInfDao.update(frameworkUserInf);
//		else 		
//			frameworkUserInfDao.save(frameworkUserInf);
//		
//		return null;
//	}
	
	/**
	 * 新增修改
	 * 
	 * @param  POJO对象
	 * @return String
	 */
	public String update(FrameworkUserInf frameworkUserInf) {
    	// 修改申请信息
		
		myBatisDao.update(frameworkUserInf);
		//修改角色
		String[] roleIds = frameworkUserInf.getRoleIds();
		if(roleIds!=null && roleIds.length>0){
			//先删除角色
			frameworkUserRoleService.delUserRole(frameworkUserInf.getId());
			for(String roleId : roleIds){
				FrameworkUserRole urole = frameworkUserRoleService.findUserRole(frameworkUserInf.getId(), Long.valueOf(roleId));
				if(urole==null || urole.getId()==null){
					urole = new FrameworkUserRole();
				}
				urole.setUserId(frameworkUserInf.getId());
				urole.setSysRoleId(Long.valueOf(roleId));
				urole.setState(WebConstant.DATA_EXIST);
				urole.setModifyTime(new Date());
				urole.setModifyUserId(Long.parseLong("11"));
				
//				SysRole role = bossUserRoleService.getGmacRoleById(Long.valueOf(roleId));
//				roleNames += role.getSysRoleName() + ",";
				if(urole==null || urole.getId()==null){
					urole.setCreateTime(new Date());
					urole.setCreateUserId(Long.parseLong("11"));
					frameworkUserRoleService.add(urole);
				}else{
					frameworkUserRoleService.update(urole);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 分页查询
	 * @param  POJO对象
	 * @param  pageIndex          当前页页数
	 * @param  pageSize           每页记录数
	 * @return Page
	 */
	public Page findFrameworkUserInfs(FrameworkUserInf frameworkUserInf,
			int pageIndex,
			int pageSize) {
		return myBatisDao.pageBy(null, null, frameworkUserInf, pageIndex, pageSize);
	}
	
	/**
	 * 根据主键对象获取信息
	 * 
	 * @param  POJO对象
	 * @return FrameworkUserInf
	 */
	public List<FrameworkUserInf> getFrameworkUserInfListByObj(FrameworkUserInf frameworkUserInf) {
		return frameworkUserInfDao.findListByObj(frameworkUserInf);
	}
	
	/**
	 * 根据主键获取信息
	 * 
	 * @param  POJO对象
	 * @return FrameworkUserInf
	 */
	public FrameworkUserInf getFrameworkUserInfByPk(int pk_Id) {
		return frameworkUserInfDao.findByPKId(pk_Id);
	}
	
	/**
	 * 根据条件获取信息
	 * 
	 * @param  POJO对象
	 * @return FrameworkUserInf返回第一个符合条件的对象，适合条件能唯一定位记录的应用场景
	 */
	public List<FrameworkUserInf> getFrameworkUserInfListBySql(FrameworkUserInf frameworkUserInf) {
		return frameworkUserInfDao.findListBySqlId("selectFrameworkUserInfs",frameworkUserInf);
	}	
	
	public void toAddUserInf(Model model){
		FrameworkSysRole sysRole = new FrameworkSysRole();
		sysRole.setState(WebConstant.DATA_EXIST);
		List<FrameworkSysRole> sysRoleList = frameworkSysRoleService.getFrameworkSysRoleListByObj(sysRole);
		model.addAttribute("sysRoleList", sysRoleList);
		
	}
	
	public void initModifyUserInf(int pk_Id,Model springModel){
		FrameworkUserInf frameworkUserInf = getFrameworkUserInfByPk(pk_Id);
		FrameworkUserRole userRole = new FrameworkUserRole();
		userRole.setState(WebConstant.DATA_EXIST);
		userRole.setUserId(Long.parseLong(String.valueOf(pk_Id)));
		//角色信息
		List<FrameworkUserRole> listUserRole = frameworkUserRoleService.getFrameworkUserRoleListByObj(userRole);
		  StringBuffer userRoleBuffer = new StringBuffer();
	        for(FrameworkUserRole uRole : listUserRole){
	        	userRoleBuffer.append(uRole.getSysRoleId()+",");
	        }
        FrameworkSysRole sysRole = new FrameworkSysRole();
		sysRole.setState(WebConstant.DATA_EXIST);
		List<FrameworkSysRole> sysRoleList = frameworkSysRoleService.getFrameworkSysRoleListByObj(sysRole);
		springModel.addAttribute("sysRoleList", sysRoleList);
	    springModel.addAttribute("userRoles",userRoleBuffer.toString());
		springModel.addAttribute("infos",frameworkUserInf);
	}
	/**
	 * 功能:用户登陆信息查询. 参数:String userName,String password 返回值:FrameworkUserInf
	 * @param userName
	 * @param password
	 * @return
	 */
	public FrameworkUserInf queryUserInfoByCondition(String userName, String password) {

		FrameworkUserInf userInf = new FrameworkUserInf(userName,StringUtil.encodePassword(password));
		List<FrameworkUserInf> list = getFrameworkUserInfListByObj(userInf);
		if (list != null && list.size() > 0) {
			FrameworkUserInf userinfo = (FrameworkUserInf) list.get(0);
			return userinfo;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void initModifyMyPassword(HttpServletRequest request,Model springModel){
		Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute(SessionUtil.USER_MAP);
		if(map != null){
			Long userId = (Long) map.get(SessionUtil.USER_ID);
			String userName = (String) map.get(SessionUtil.USER_NAME);
			springModel.addAttribute("v_userId", userId);
			springModel.addAttribute("v_userName", userName);
		}
		
	}
	/**
	 * 修改密码
	 * @param model
	 */
	public void modifyMyPassword(FrameworkUserInf model){
		
		model.setUserPassword(StringUtil.encodePassword(model.getUserPassword()));
		myBatisDao.update(model);
	}
	
}
