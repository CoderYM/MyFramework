/*
 * 版权声明 .
 * 此文档的版权归通联支付网络服务有限公司所有
 * Powered By [Allinpay-Boss-framework]
 */

package com.allinpay.its.boss.system.permission.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.allinpay.its.boss.framework.utils.Page;
import com.allinpay.its.boss.system.BaseAction;
import com.allinpay.its.boss.system.menu.model.FrameworkSysMenu;
import com.allinpay.its.boss.system.menu.service.FrameworkSysMenuServiceImpl;
import com.allinpay.its.boss.system.permission.model.FrameworkSysPermission;
import com.allinpay.its.boss.system.permission.service.FrameworkSysPermissionServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/permission")
public class FrameworkSysPermissionAction  extends BaseAction{
	
	//private static final long serialVersionUID = -183590174L;
	
	@Autowired
	private FrameworkSysPermissionServiceImpl frameworkSysPermissionService;
	
	@Autowired
	private FrameworkSysMenuServiceImpl menuService;
	
	/**
	 * 列表信息查询
	 * 
	 * @return String
	 */
	@RequestMapping("")
	public String findFrameworkSysPermissions(Model springModel,FrameworkSysPermission model)  {
		Page page = frameworkSysPermissionService.findFrameworkSysPermissions(model, model.getPageNum(),  model.getNumPerPage());
		springModel.addAttribute(page);
		springModel.addAttribute("syspermModel", model);
		return "system/permission/FrameworkSysPermission/FrameworkSysPermissionsQuery";
	}
	@RequestMapping("/menuList")
	@ResponseBody
	public String getFrameworkSysMenuList(){
		List<FrameworkSysMenu> menuList= menuService.getMenuList();;
		return toJSONStrByObject(menuList);
	}
	
	@RequestMapping("/menuTree")
	public String findFrameworkSysMenuTree(Model springModel,FrameworkSysPermission model)  {
		return "system/permission/FrameworkSysPermission/FrameworkSysPermissionsMenuTree";
	}
	
	/**
	 * 新增页面
	 * 
	 * @return String
	 */
	@RequestMapping("/addFrameworkSysPermissionToPage")
	public String addFrameworkSysPermission(HttpServletRequest request, Model model)  {
		String sysMenuId = request.getParameter("sysMenuId");
		model.addAttribute("sysMenuId", sysMenuId);
		return "system/permission/FrameworkSysPermission/FrameworkSysPermissionAdd";
	}
	/**
	 * 新增保存
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping(value="/saveFrameworkSysPermissionAction",method = RequestMethod.POST)
	public ModelAndView saveFrameworkSysPermission(FrameworkSysPermission model)  {
		
		frameworkSysPermissionService.add(model);
		return ajaxDoneSuccess("成功");
	}
	/**
	 * 删除
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping("/delete/{pk_Id}")
	public ModelAndView deleteFrameworkSysPermission(@PathVariable("pk_Id") int pk_Id)  {
		frameworkSysPermissionService.delete(pk_Id);
		return ajaxDoneSuccess("成功");
	}
	/**
	 * 批量删除
	 * @param orderIndexs
	 * @return
	 */
	@RequestMapping("/deleteAll")
	public ModelAndView deleteAll(@RequestParam("orderIndexs") String orderIndexs) {
		if (orderIndexs != null && orderIndexs.length()> 0 ){
			String[] ids= orderIndexs.split(",");
			for( int i=0; i<ids.length; i++){
				if (ids[i].length()>0){
					frameworkSysPermissionService.delete(Integer.parseInt(ids[i]));
				}
			}
		}
		return ajaxDoneSuccess("成功");
	}
	/**
	 * 修改初始化
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping("/modify/{pk_Id}")
	public String initModifyFrameworkSysPermission(@PathVariable("pk_Id") int pk_Id,Model springModel) {
		FrameworkSysPermission frameworkSysPermission = frameworkSysPermissionService.getFrameworkSysPermissionByPk(pk_Id);
		springModel.addAttribute("infos",frameworkSysPermission);
		return "system/permission/FrameworkSysPermission/FrameworkSysPermissionModify";
	}
	
	/**
	 * 修改
	 * 
	 * @return String
	 * @author code gen
	 */
	@RequestMapping(value="/modifyFrameworkSysPermissionAction",method = RequestMethod.POST)
	public ModelAndView modifyFrameworkSysPermission(FrameworkSysPermission model)  {

		frameworkSysPermissionService.update(model);
		return ajaxDoneSuccess("成功");
	}
	

	
		
		
	/**
	 * 明细信息查找
	 * 
	 * @return String
	 * @author code gen
	 */
	@RequestMapping("/showinfo/{pk_Id}")
	public String findFrameworkSysPermission(@PathVariable("pk_Id") int pk_Id,Model springModel)  {
		FrameworkSysPermission frameworkSysPermission = frameworkSysPermissionService.getFrameworkSysPermissionByPk(pk_Id);
		springModel.addAttribute("infos",frameworkSysPermission);
		return "system/permission/FrameworkSysPermission/FrameworkSysPermissionDetail";
	}
	
	/**
	 * 将对象属性信息赋值给表单对象
	 * 
	 * @param  FrameworkSysPermission      POJO对象
	 * @return FrameworkSysPermissionForm  表单信息POJO对象
	 * @author code gen
	 */
//	private void changeToFrameworkSysPermissionForm(FrameworkSysPermission frameworkSysPermission) {
//		
//		
//		frameworkSysPermission.setId(frameworkSysPermission.getId());
//	    
//		frameworkSysPermission.setSysMenuId(frameworkSysPermission.getSysMenuId());
//	    
//		frameworkSysPermission.setPermissionCode(frameworkSysPermission.getPermissionCode());
//	    
//		frameworkSysPermission.setPermissionName(frameworkSysPermission.getPermissionName());
//	    
//		frameworkSysPermission.setPermissionDescription(frameworkSysPermission.getPermissionDescription());
//	    
//		frameworkSysPermission.setState(frameworkSysPermission.getState());
//	    
//		frameworkSysPermission.setRemark(frameworkSysPermission.getRemark());
//	    
//		frameworkSysPermission.setCreateUserId(frameworkSysPermission.getCreateUserId());
//	    
//		frameworkSysPermission.setCreateTime(frameworkSysPermission.getCreateTime());
//	    
//		frameworkSysPermission.setModifyUserId(frameworkSysPermission.getModifyUserId());
//	    
//		frameworkSysPermission.setModifyTime(frameworkSysPermission.getModifyTime());
//	    
//		frameworkSysPermission.setVersion(frameworkSysPermission.getVersion());
//	    
//	}
}
