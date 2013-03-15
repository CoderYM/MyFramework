/*
 * 版权声明 .
 * 此文档的版权归通联支付网络服务有限公司所有
 * Powered By [Allinpay-Boss-framework]
 */

package com.allinpay.its.boss.system.permission.action;


import com.allinpay.its.boss.framework.utils.Page;
import com.allinpay.its.boss.system.BaseAction;
import com.allinpay.its.boss.system.permission.model.FrameworkSysAction;
import com.allinpay.its.boss.system.permission.service.FrameworkSysActionServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/sysperm")
public class FrameworkSysActionAction  extends BaseAction{
	
	//private static final long serialVersionUID = -45811662L;
	
	@Autowired
	private FrameworkSysActionServiceImpl frameworkSysActionService;
	
	
	
	/**
	 * 列表信息查询
	 * 
	 * @return String
	 * @author code gen
	 */
	@RequestMapping("")
	public String findFrameworkSysActions(Model springModel,FrameworkSysAction model)  {
		Page page = frameworkSysActionService.findFrameworkSysActions(model, model.getPageNum(),  model.getNumPerPage());
		springModel.addAttribute(page);
		return "system/permission/FrameworkSysAction/FrameworkSysActionsQuery";
	}
	
	
	/**
	 * 新增页面
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping("/addFrameworkSysActionToPage")
	public String addFrameworkSysAction(Model model)  {

		return "system/permission/FrameworkSysAction/FrameworkSysActionAdd";
	}
	/**
	 * 新增保存
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping(value="/saveFrameworkSysActionAction",method = RequestMethod.POST)
	public ModelAndView saveFrameworkSysAction(FrameworkSysAction model)  {
		
		frameworkSysActionService.add(model);
		return ajaxDoneSuccess("成功");
	}
	/**
	 * 删除
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping("/delete/{pk_Id}")
	public ModelAndView deleteFrameworkSysAction(@PathVariable("pk_Id") int pk_Id)  {
		frameworkSysActionService.delete(pk_Id);
		return ajaxDoneSuccess("成功");
	}
	/**
	 * 修改初始化
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping("/modify/{pk_Id}")
	public String initModifyFrameworkSysAction(@PathVariable("pk_Id") int pk_Id,Model springModel) {
		FrameworkSysAction frameworkSysAction = frameworkSysActionService.getFrameworkSysActionByPk(pk_Id);
		springModel.addAttribute("infos",frameworkSysAction);
		return "system/permission/FrameworkSysAction/FrameworkSysActionModify";
	}
	
	/**
	 * 修改
	 * 
	 * @return String
	 * @author code gen
	 */
	@RequestMapping(value="/modifyFrameworkSysActionAction",method = RequestMethod.POST)
	public ModelAndView modifyFrameworkSysAction(FrameworkSysAction model)  {

		frameworkSysActionService.update(model);
		return ajaxDoneSuccess("成功");
	}
	

	
		
		
	/**
	 * 明细信息查找
	 * 
	 * @return String
	 * @author code gen
	 */
	@RequestMapping("/showinfo/{pk_Id}")
	public String findFrameworkSysAction(@PathVariable("pk_Id") int pk_Id,Model springModel)  {
		FrameworkSysAction frameworkSysAction = frameworkSysActionService.getFrameworkSysActionByPk(pk_Id);
		springModel.addAttribute("infos",frameworkSysAction);
		return "system/permission/FrameworkSysAction/FrameworkSysActionDetail";
	}
	
	/**
	 * 将对象属性信息赋值给表单对象
	 * 
	 * @param  FrameworkSysAction      POJO对象
	 * @return FrameworkSysActionForm  表单信息POJO对象
	 * @author code gen
	 */
//	private void changeToFrameworkSysActionForm(FrameworkSysAction frameworkSysAction) {
//		
//		
//		frameworkSysAction.setId(frameworkSysAction.getId());
//	    
//		frameworkSysAction.setSysMenuId(frameworkSysAction.getSysMenuId());
//	    
//		frameworkSysAction.setPermissionCode(frameworkSysAction.getPermissionCode());
//	    
//		frameworkSysAction.setActionName(frameworkSysAction.getActionName());
//	    
//		frameworkSysAction.setMethodName(frameworkSysAction.getMethodName());
//	    
//		frameworkSysAction.setSimpleClassName(frameworkSysAction.getSimpleClassName());
//	    
//		frameworkSysAction.setActionType(frameworkSysAction.getActionType());
//	    
//		frameworkSysAction.setActionDes(frameworkSysAction.getActionDes());
//	    
//	}
}
