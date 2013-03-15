/*
 * 版权声明 .
 * 此文档的版权归通联支付网络服务有限公司所有
 * Powered By [Allinpay-Boss-framework]
 */

package com.allinpay.its.boss.system.permission.action;


import com.allinpay.its.boss.framework.utils.Page;
import com.allinpay.its.boss.system.BaseAction;
import com.allinpay.its.boss.system.permission.model.FrameworkActionLog;
import com.allinpay.its.boss.system.permission.service.FrameworkActionLogServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/aclog")
public class FrameworkActionLogAction  extends BaseAction{
	
	//private static final long serialVersionUID = 13352179L;
	
	@Autowired
	private FrameworkActionLogServiceImpl frameworkActionLogService;
	
	
	
	/**
	 * 列表信息查询
	 * 
	 * @return String
	 * @author code gen
	 */
	@RequestMapping("")
	public String findFrameworkActionLogs(Model springModel,FrameworkActionLog model)  {
		Page page = frameworkActionLogService.findFrameworkActionLogs(model, model.getPageNum(),  model.getNumPerPage());
		springModel.addAttribute(page);
		return "system/permission/FrameworkActionLog/FrameworkActionLogsQuery";
	}
	
	
	/**
	 * 新增页面
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping("/addFrameworkActionLogToPage")
	public String addFrameworkActionLog(Model model)  {

		return "system/permission/FrameworkActionLog/FrameworkActionLogAdd";
	}
	/**
	 * 新增保存
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping(value="/saveFrameworkActionLogAction",method = RequestMethod.POST)
	public ModelAndView saveFrameworkActionLog(FrameworkActionLog model)  {
		
		frameworkActionLogService.add(model);
		return ajaxDoneSuccess("成功");
	}
	/**
	 * 删除
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping("/delete/{pk_Id}")
	public ModelAndView deleteFrameworkActionLog(@PathVariable("pk_Id") int pk_Id)  {
		frameworkActionLogService.delete(pk_Id);
		return ajaxDoneSuccess("成功");
	}
	/**
	 * 修改初始化
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping("/modify/{pk_Id}")
	public String initModifyFrameworkActionLog(@PathVariable("pk_Id") int pk_Id,Model springModel) {
		FrameworkActionLog frameworkActionLog = frameworkActionLogService.getFrameworkActionLogByPk(pk_Id);
		springModel.addAttribute("infos",frameworkActionLog);
		return "system/permission/FrameworkActionLog/FrameworkActionLogModify";
	}
	
	/**
	 * 修改
	 * 
	 * @return String
	 * @author code gen
	 */
	@RequestMapping(value="/modifyFrameworkActionLogAction",method = RequestMethod.POST)
	public ModelAndView modifyFrameworkActionLog(FrameworkActionLog model)  {

		frameworkActionLogService.update(model);
		return ajaxDoneSuccess("成功");
	}
	

	
		
		
	/**
	 * 明细信息查找
	 * 
	 * @return String
	 * @author code gen
	 */
	@RequestMapping("/showinfo/{pk_Id}")
	public String findFrameworkActionLog(@PathVariable("pk_Id") int pk_Id,Model springModel)  {
		FrameworkActionLog frameworkActionLog = frameworkActionLogService.getFrameworkActionLogByPk(pk_Id);
		springModel.addAttribute("infos",frameworkActionLog);
		return "system/permission/FrameworkActionLog/FrameworkActionLogDetail";
	}
	
	/**
	 * 将对象属性信息赋值给表单对象
	 * 
	 * @param  FrameworkActionLog      POJO对象
	 * @return FrameworkActionLogForm  表单信息POJO对象
	 * @author code gen
	 */
//	private void changeToFrameworkActionLogForm(FrameworkActionLog frameworkActionLog) {
//		
//		
//		frameworkActionLog.setId(frameworkActionLog.getId());
//	    
//		frameworkActionLog.setLogTime(frameworkActionLog.getLogTime());
//	    
//		frameworkActionLog.setLogUser(frameworkActionLog.getLogUser());
//	    
//		frameworkActionLog.setLogOperate(frameworkActionLog.getLogOperate());
//	    
//		frameworkActionLog.setLogContent(frameworkActionLog.getLogContent());
//	    
//		frameworkActionLog.setRemark(frameworkActionLog.getRemark());
//	    
//		frameworkActionLog.setVersion(frameworkActionLog.getVersion());
//	    
//		frameworkActionLog.setLogOperateClass(frameworkActionLog.getLogOperateClass());
//	    
//		frameworkActionLog.setLogOperateMethod(frameworkActionLog.getLogOperateMethod());
//	    
//		frameworkActionLog.setLogOperateResult(frameworkActionLog.getLogOperateResult());
//	    
//		frameworkActionLog.setLogType(frameworkActionLog.getLogType());
//	    
//		frameworkActionLog.setIsAuthed(frameworkActionLog.getIsAuthed());
//	    
//		frameworkActionLog.setLogOperateActionName(frameworkActionLog.getLogOperateActionName());
//	    
//		frameworkActionLog.setChangeTableInfo(frameworkActionLog.getChangeTableInfo());
//	    
//	}
}
