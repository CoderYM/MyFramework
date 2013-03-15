package com.allinpay.its.boss.system.menu.action;

import java.util.ArrayList;
import java.util.List;

import com.allinpay.its.boss.framework.utils.Page;
import com.allinpay.its.boss.system.BaseAction;
import com.allinpay.its.boss.system.menu.model.FrameworkSysMenu;
import com.allinpay.its.boss.system.menu.service.FrameworkSysMenuServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

/**
 * 页面访问Action的url规则以下面这个方法为例:
 * http://localhost/项目名称/menu/XXX方法名
 * @return
 */

@Controller
@RequestMapping(value="/menu")
public class MenuAction extends BaseAction{
	
	@Autowired
	private FrameworkSysMenuServiceImpl menuService;
	
	@RequestMapping("")
	public String listMenu(@ModelAttribute("frameworkSysMenu") FrameworkSysMenu menu, Model model){
		Page page = menuService.getFrameworkSysMenuList(menu);
		model.addAttribute(page);
		return "menu/menuList";
	}
	
	@RequestMapping("/level/{levelId}")
	public String getMenuByLevelId(@ModelAttribute("frameworkSysMenu") FrameworkSysMenu menu, Model model, 
			@PathVariable("levelId") int levelId){
		Page page = menuService.getFrameworkSysMenuListByLevelId(menu, levelId);
		model.addAttribute(page);
		model.addAttribute("levelId", levelId);
		return "menu/menuList";
	}
	
	@RequestMapping("/level/lookup/{levelId}")
	public String getMenuLookupByLevelId(@ModelAttribute("frameworkSysMenu") FrameworkSysMenu menu, Model model, 
			@PathVariable("levelId") int levelId){
		Page page = menuService.getFrameworkSysMenuListByLevelId(menu, levelId);
		model.addAttribute(page);
		model.addAttribute("levelId", levelId);
		return "menu/menuLookup";
	}
	
	@RequestMapping("/add/{levelId}")
	public String add(Model model, @PathVariable("levelId") int levelId){
		model.addAttribute("levelId", levelId);
		return "menu/menuAdd";
	}
	
	/**
	 * 为什么一开始界面传了参数model.addAttribute(menu);显示不出来？因为根据默认的规则，放的是对象的名字。所以
	 * 一开始的时候界面用menu.XXX显示不出来。还是要在设置的时候放名字
	 * @param menuId
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit/{menuId}/{levelId}")
	public String edit(@PathVariable("menuId") int menuId, Model model, @PathVariable("levelId") int levelId) {
		FrameworkSysMenu menu = menuService.getMenu(menuId);
		model.addAttribute("menu",menu);
		model.addAttribute("levelId", levelId);
		return "menu/menuEdit";
	}
	
	@RequestMapping("/get/{menuId}")
	@ResponseBody
	public String get(@PathVariable("menuId") int menuId) {
		FrameworkSysMenu menu = menuService.getMenu(menuId);
		Gson gson = new Gson();
		return gson.toJson(menu);
	}
	
	@RequestMapping(value = "/insert/{levelId}", method = RequestMethod.POST)
	public ModelAndView insert(FrameworkSysMenu menu, @PathVariable("levelId") int levelId) {
		menuService.insertMenu(menu, levelId);
		return ajaxDoneSuccess("成功");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(FrameworkSysMenu menu) {
		menuService.update(menu);
		return ajaxDoneSuccess("成功");

	}
	
	@RequestMapping("/delete/{menuId}")
	public ModelAndView delete(@PathVariable("menuId") int menuId) {
		menuService.delete(menuId);
		return ajaxDoneSuccess("成功");
	}
	
	@RequestMapping("/deleteAll")
	public ModelAndView deleteAll(@RequestParam("orderIndexs") String orderIndexs) {
		if (orderIndexs != null && orderIndexs.length()> 0 ){
			String[] ids= orderIndexs.split(",");
			for( int i=0; i<ids.length; i++){
				if (ids[i].length()>0){
					menuService.delete(Integer.parseInt(ids[i]));
				}
			}
		}
		return ajaxDoneSuccess("成功");
	}
}
