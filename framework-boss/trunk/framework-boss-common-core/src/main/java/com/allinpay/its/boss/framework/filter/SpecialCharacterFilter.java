package com.allinpay.its.boss.framework.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.allinpay.its.boss.framework.utils.ParameterRequestWrapper;


/**
 * Class SpecialCharacterFilter
 * 
 * @author 杨敏
 * @version $Revision:0.1,$Date: 2011-10-21$
 * 
 *          Description: 特殊字符过滤器
 * 
 *          Function List: // 主要函数及其功能
 * 
 *          1. -------
 * 
 *          History: // 历史修改记录
 * 
 *          <author> <time> <version > <desc>
 * 
 *          1. 杨敏 2011-10-21 0.1 创建
 */
public class SpecialCharacterFilter implements Filter {
	public static List<String> whiteList;
	public static boolean isOpen;

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		HttpServletRequest request = (HttpServletRequest) req;

		if ((!isOpen) || (dontFilter(request))) {
			chain.doFilter(req, res);
			return;
		}

		HashMap<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
		HashMap<String, String[]> mm = new HashMap<String, String[]>();
		Enumeration<String> enu = request.getParameterNames();
		if ((m.size() > 0) && (processParameters(m, enu, mm))) {
			ParameterRequestWrapper wrapRequest = new ParameterRequestWrapper(
					request, mm);
			chain.doFilter(wrapRequest, res);
		} else {
			chain.doFilter(req, res);
		}
	}

	private boolean dontFilter(HttpServletRequest request) {
		String requestURL = request.getRequestURI();
		for (String name : whiteList) {
			if (requestURL.indexOf(name) >= 0) {
				return true;
			}
		}
		return false;
	}

	public boolean processParameters(HashMap<String, String[]> m,
			Enumeration<?> enu, HashMap<String, String[]> mm) {
		if ((m != null) && (enu != null)) {
			while (enu.hasMoreElements()) {
				String key = (String) enu.nextElement();
				String[] values = (String[]) (String[]) m.get(key);
				for (int i = 0; i < values.length; i++) {
					if (values[i] != null) {
						values[i] = replaceSpecialChar(values[i]);
					}
				}
				mm.put(key, values);
			}
		}
		return true;
	}

	public static String replaceSpecialChar(String value) {
		value = value.replace("&", "＆");
		value = value.replace("<", "＜");
		value = value.replace(">", "＞");
		value = value.replace("\\", "＼");
		value = value.replace("\"", "“");
		value = value.replace("'", "‘");
		return value;
	}

	public static void main(String[] args) {
		System.out.println(replaceSpecialChar("\"'\\<>&"));
	}

	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
		String swicth = "on";
		isOpen = true;
		if ((swicth != null) && (swicth.equals("off"))) {
			isOpen = false;
		}
		String[] s = { "", "" };// 白盒url,specialCharacterFilter.whiteList
		whiteList = new LinkedList<String>();
		for (int i = 0; i < s.length; i++) {
			String value = s[i].trim();
			if ((value == null) || (value.equals(""))) {
				continue;
			}
			whiteList.add(s[i].trim());
		}
	}
}