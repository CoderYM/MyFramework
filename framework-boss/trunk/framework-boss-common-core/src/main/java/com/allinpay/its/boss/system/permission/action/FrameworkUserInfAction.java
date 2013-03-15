/*
 * 版权声明 .
 * 此文档的版权归通联支付网络服务有限公司所有
 * Powered By [Allinpay-Boss-framework]
 */

package com.allinpay.its.boss.system.permission.action;


import javax.servlet.http.HttpServletRequest;

import com.allinpay.its.boss.framework.utils.Page;
import com.allinpay.its.boss.system.BaseAction;
import com.allinpay.its.boss.system.permission.model.FrameworkUserInf;
import com.allinpay.its.boss.system.permission.service.FrameworkUserInfServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/sysuser")
public class FrameworkUserInfAction  extends BaseAction{
	
	//private static final long serialVersionUID = -979661325L;
	
	@Autowired
	private FrameworkUserInfServiceImpl frameworkUserInfService;
	
	
	
	/**
	 * 列表信息查询
	 * 
	 * @return String
	 * @author code gen
	 */
	@RequestMapping("")
	public String findFrameworkUserInfs(Model springModel,FrameworkUserInf model)  {
		Page page = frameworkUserInfService.findFrameworkUserInfs(model, model.getPageNum(),  model.getNumPerPage());
		springModel.addAttribute(page);
		return "system/permission/FrameworkUserInf/FrameworkUserInfsQuery";
	}
	
	
	/**
	 * 新增页面
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping("/addFrameworkUserInfToPage")
	public String addFrameworkUserInf(Model model)  {
		frameworkUserInfService.toAddUserInf(model);
		return "system/permission/FrameworkUserInf/FrameworkUserInfAdd";
	}
	/**
	 * 新增保存
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping(value="/saveFrameworkUserInfAction",method = RequestMethod.POST)
	public ModelAndView saveFrameworkUserInf(FrameworkUserInf model)  {
		
		frameworkUserInfService.add(model);
		return ajaxDoneSuccess("成功");
	}
	/**
	 * 删除
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping("/delete/{pk_Id}")
	public ModelAndView deleteFrameworkUserInf(@PathVariable("pk_Id") int pk_Id)  {
		frameworkUserInfService.delete(pk_Id);
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
					frameworkUserInfService.delete(Integer.parseInt(ids[i]));
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
	public String initModifyFrameworkUserInf(@PathVariable("pk_Id") int pk_Id,Model springModel) {
		
		frameworkUserInfService.initModifyUserInf(pk_Id, springModel);
		
		return "system/permission/FrameworkUserInf/FrameworkUserInfModify";
	}
	
	/**
	 * 修改
	 * 
	 * @return String
	 * @author code gen
	 */
	@RequestMapping(value="/modifyFrameworkUserInfAction",method = RequestMethod.POST)
	public ModelAndView modifyFrameworkUserInf(FrameworkUserInf model)  {

		frameworkUserInfService.update(model);
		return ajaxDoneSuccess("成功");
	}
	

	
		
		
	/**
	 * 明细信息查找
	 * 
	 * @return String
	 * @author code gen
	 */
	@RequestMapping("/showinfo/{pk_Id}")
	public String findFrameworkUserInf(@PathVariable("pk_Id") int pk_Id,Model springModel)  {
		FrameworkUserInf frameworkUserInf = frameworkUserInfService.getFrameworkUserInfByPk(pk_Id);
		springModel.addAttribute("infos",frameworkUserInf);
		return "system/permission/FrameworkUserInf/FrameworkUserInfDetail";
	}
	
	/**
	 * 修改密码初始化
	 * @return
	 */
	@RequestMapping("/initModifyMyPassword")
	public String initModifyMyPassword(HttpServletRequest request,Model springModel){
		
		frameworkUserInfService.initModifyMyPassword(request, springModel);
		return "system/permission/FrameworkUserInf/modifyMyPassword";
	}
	
	/**
	 * 修改密码
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/modifyMyPassword",method = RequestMethod.POST)
	public ModelAndView modifyMyPassword(FrameworkUserInf model){
		frameworkUserInfService.modifyMyPassword(model);
		return ajaxDoneSuccess("成功");
		
	}
}
