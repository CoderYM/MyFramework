package com.allinpay.its.boss.system;

import java.util.Collection;

import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

public abstract class BaseAction {

	protected ModelAndView ajaxDone(int statusCode, String message) {
		ModelAndView mav = new ModelAndView("ajaxDone");
		mav.addObject("statusCode", statusCode);
		mav.addObject("message", message);
		return mav;
	}

	protected ModelAndView ajaxDoneSuccess(String message) {
		return ajaxDone(200, message);
	}

	protected ModelAndView ajaxDoneError(String message) {
		return ajaxDone(300, message);
	}
	
	protected String toJSONStrByObject(Collection<?> obj){
		Gson gson = new Gson();
		return "{\"result\":\"success\",\"length\":\""
				+ ((null == obj) ? 0 : obj.size())
				+ "\",\"lists\":" + gson.toJson(obj)
				+ "}";
	}
	
}
