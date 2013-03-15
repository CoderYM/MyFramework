package com.allinpay.its.boss.system.auth.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.allinpay.its.boss.framework.utils.SessionUtil;
import com.allinpay.its.boss.framework.utils.WebConstant;
import com.allinpay.its.boss.system.BaseAction;
import com.allinpay.its.boss.system.menu.model.FrameworkSysMenu;
import com.allinpay.its.boss.system.menu.service.FrameworkSysMenuServiceImpl;
import com.allinpay.its.boss.system.permission.model.FrameworkUserInf;
import com.allinpay.its.boss.system.permission.model.FrameworkUserRole;
import com.allinpay.its.boss.system.permission.service.FrameworkSysPermissionServiceImpl;
import com.allinpay.its.boss.system.permission.service.FrameworkUserInfServiceImpl;
import com.allinpay.its.boss.system.permission.service.FrameworkUserRoleServiceImpl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 这个是主要的根目录的控制器
 * @author Administrator
 *
 */
@Controller
public class IndexAction extends BaseAction{
	
	@Autowired
	private FrameworkSysMenuServiceImpl menuService;
	
	@Autowired
	private FrameworkUserRoleServiceImpl userRoleService;
	@Autowired
	private FrameworkUserInfServiceImpl userInfService;
	@Autowired
	private FrameworkSysPermissionServiceImpl sysPermissionService;

	@RequestMapping("")
	public String initLogin(){
		return "system/login";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/index")
	public String index(Model model,FrameworkUserInf userModel,HttpServletRequest request){
		//登录验证
		boolean isAuth = true;
		FrameworkUserInf userInf = userInfService.queryUserInfoByCondition(userModel.getUserName(), userModel.getUserPassword());
		if (userInf == null) {
			isAuth = false;
			model.addAttribute("errMessage", "用户名,密码为空或错误,请重试!");
			return "system/login";
		} else {
			String state = userInf.getState();
			if (state != null && state.equals("1")) {
				isAuth = false;
				model.addAttribute("errMessage", "用户名当前不能使用,请重试!");
				return "system/login";
			}
		}
		if(isAuth){
			
			//获取用户角色
			FrameworkUserRole userRole = new FrameworkUserRole();
			userRole.setUserId(userInf.getId());
			userRole.setState(WebConstant.DATA_EXIST);
			List<FrameworkUserRole> userRoles = userRoleService.getFrameworkUserRoleListByObj(userRole);
			String userRoldIds="";
			for(FrameworkUserRole role: userRoles){
				userRoldIds += role.getSysRoleId()+",";
			}
			if(StringUtils.isNotBlank(userRoldIds)){
				userRoldIds = userRoldIds.replace(userRoldIds.charAt(userRoldIds.length()-1), ',').trim();
				userRoldIds = userRoldIds.substring(0, userRoldIds.length()-1);
//				for(String r: userRoldIds.split(",")){
//					userRoleNames += (String) DynSelect.getAllRoleForManyMap().get(r)+",";
//				}
//				userRoleNames = userRoleNames.replace(userRoleNames.charAt(userRoleNames.length()-1), ',').trim();
			}
			Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute(SessionUtil.USER_MAP);
			
			if (map == null) {
				map = new HashMap<String, Object>();
			}
			String ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}

			map.put(SessionUtil.USER_NAME, userInf.getUserName());
			map.put(SessionUtil.USER_ID, userInf.getId());
			map.put(SessionUtil.USER_ROEL, userRoldIds);
//			map.put(SessionUtil.USER_ROEL_NAME, userRoleNames);
			map.put(SessionUtil.USER_REAL_NAME, userInf.getRealName());
//			map.put(SessionUtil.USER_FUNCATIONS, getFunction(userinfo.getInnerUserId()));
			map.put(SessionUtil.USER_IP, ip);
			//将用户的权限编码放到Session中,后面需要优化放到缓存框架中
			map.put(SessionUtil.USER_PERM_CODE_LIST, sysPermissionService.getPermissionCodeByUserId(userInf.getId()));

			request.getSession().setAttribute(SessionUtil.USER_MAP, map);
			
		}
		
		List<FrameworkSysMenu> level3List = menuService.getFrameworkSysMenuListByLevelId(3,userInf);
		List<FrameworkSysMenu> level2List = menuService.getFrameworkSysMenuListBySubMenuList(level3List);
		List<FrameworkSysMenu> level1List = menuService.getFrameworkSysMenuListBySubMenuList(level2List);
		
		model.addAttribute("menuLevel1", level1List);
		model.addAttribute("menuLevel2", level2List);
		model.addAttribute("menuLevel3", level3List);
		return "system/index";
	}
	
	@RequestMapping("/logOut")
	public String userLogOut(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute(SessionUtil.USER_MAP) != null){
			session.removeAttribute(SessionUtil.USER_MAP);
		}
		session.invalidate();
		return "system/login";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getPermissionByUser")
	@ResponseBody
	public String getPermissionByUser(HttpServletRequest request){
		Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute(SessionUtil.USER_MAP);
		if(map != null){
			return (String)map.get(SessionUtil.USER_PERM_CODE_LIST);
		}else{
			return null;
		}
		
	}
	
}
