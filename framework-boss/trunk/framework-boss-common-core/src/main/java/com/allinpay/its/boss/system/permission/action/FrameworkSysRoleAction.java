/*
 * 版权声明 .
 * 此文档的版权归通联支付网络服务有限公司所有
 * Powered By [Allinpay-Boss-framework]
 */

package com.allinpay.its.boss.system.permission.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.allinpay.its.boss.framework.utils.Page;
import com.allinpay.its.boss.framework.utils.SessionUtil;
import com.allinpay.its.boss.system.BaseAction;
import com.allinpay.its.boss.system.permission.model.FrameworkSysRole;
import com.allinpay.its.boss.system.permission.service.FrameworkSysRoleServiceImpl;

@Controller
@RequestMapping("/sysrole")
public class FrameworkSysRoleAction extends BaseAction {

	// private static final long serialVersionUID = 1579626063L;

	@Autowired
	private FrameworkSysRoleServiceImpl frameworkSysRoleService;

	/**
	 * 列表信息查询
	 * 
	 * @return String
	 * @author code gen
	 */
	@RequestMapping("")
	public String findFrameworkSysRoles(Model springModel,
			FrameworkSysRole model) {
		Page page = frameworkSysRoleService.findFrameworkSysRoles(model,
				model.getPageNum(), model.getNumPerPage());
		springModel.addAttribute(page);
		return "system/permission/FrameworkSysRole/FrameworkSysRolesQuery";
	}

	/**
	 * 新增页面
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping("/addFrameworkSysRoleToPage")
	public String addFrameworkSysRole(Model model,HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute(SessionUtil.USER_MAP);
		frameworkSysRoleService.addFrameworkSysRoleToPage(model,map);
		return "system/permission/FrameworkSysRole/FrameworkSysRoleAdd";
	}

	/**
	 * 新增保存
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping(value = "/saveFrameworkSysRoleAction", method = RequestMethod.POST)
	public ModelAndView saveFrameworkSysRole(FrameworkSysRole model) {
		
		frameworkSysRoleService.saveSysRole(model);
		return ajaxDoneSuccess("成功");
	}

	/**
	 * 删除
	 * 
	 * @return String
	 * @author by code generator
	 */
	@RequestMapping("/delete/{pk_Id}")
	public ModelAndView deleteFrameworkSysRole(@PathVariable("pk_Id") int pk_Id) {
		frameworkSysRoleService.deleteSysRole(pk_Id);
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
					frameworkSysRoleService.delete(Integer.parseInt(ids[i]));
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
	public String initModifyFrameworkSysRole(@PathVariable("pk_Id") int pk_Id,
			Model springModel,HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute(SessionUtil.USER_MAP);
		frameworkSysRoleService.initModifySysRole(pk_Id, springModel,map);
		return "system/permission/FrameworkSysRole/FrameworkSysRoleModify";
	}

	/**
	 * 修改
	 * 
	 * @return String
	 * @author code gen
	 */
	@RequestMapping(value = "/modifyFrameworkSysRoleAction", method = RequestMethod.POST)
	public ModelAndView modifyFrameworkSysRole(FrameworkSysRole model) {

		frameworkSysRoleService.modifySysRole(model);

		return ajaxDoneSuccess("成功");
	}

	/**
	 * 明细信息查找
	 * 
	 * @return String
	 * @author code gen
	 */
	public String findFrameworkSysRole(FrameworkSysRole model) {
		// 查询基本信息
		// FrameworkSysRole frameworkSysRole =
		// frameworkSysRoleService.getFrameworkSysRoleByPk(model);
		// changeToFrameworkSysRoleForm(frameworkSysRole);
		return "system/permission/FrameworkSysRole/FrameworkSysRoleDetail";
	}

	/**
	 * 将对象属性信息赋值给表单对象
	 * 
	 * @param FrameworkSysRole
	 *            POJO对象
	 * @return FrameworkSysRoleForm 表单信息POJO对象
	 * @author code gen
	 */
	// private void changeToFrameworkSysRoleForm(FrameworkSysRole
	// frameworkSysRole) {
	//
	//
	// frameworkSysRole.setId(frameworkSysRole.getId());
	//
	// frameworkSysRole.setSysRoleCode(frameworkSysRole.getSysRoleCode());
	//
	// frameworkSysRole.setSysRoleName(frameworkSysRole.getSysRoleName());
	//
	// frameworkSysRole.setSysRoleDescription(frameworkSysRole.getSysRoleDescription());
	//
	// frameworkSysRole.setState(frameworkSysRole.getState());
	//
	// frameworkSysRole.setRemark(frameworkSysRole.getRemark());
	//
	// frameworkSysRole.setCreateUserId(frameworkSysRole.getCreateUserId());
	//
	// frameworkSysRole.setCreateTime(frameworkSysRole.getCreateTime());
	//
	// frameworkSysRole.setModifyUserId(frameworkSysRole.getModifyUserId());
	//
	// frameworkSysRole.setModifyTime(frameworkSysRole.getModifyTime());
	//
	// frameworkSysRole.setVersion(frameworkSysRole.getVersion());
	//
	// }
}
