/*
 * @(#)EncodingFilter.java 2010-6-9
 *
 */
package com.allinpay.its.boss.framework.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Class EncodingFilter
 * 
 * @author 杨敏
 * @version $Revision:0.1,$Date: 2010-6-9$
 * 
 *          Description: 过滤struts2 Get方式提交数据乱码
 * 
 *          Function List: // 主要函数及其功能
 * 
 *          1. -------
 * 
 *          History: // 历史修改记录
 * 
 *          <author> <time> <version > <desc>
 * 
 *          1. 杨敏 2010-6-9 0.1 创建
 */
public class EncodingFilter implements Filter {

	protected String encoding = null;
	protected FilterConfig filterConfig = null;
	protected boolean ignore = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null)
			this.ignore = true;
		else if (value.equalsIgnoreCase("true"))
			this.ignore = true;
		else if (value.equalsIgnoreCase("yes"))
			this.ignore = true;
		else
			this.ignore = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (ignore || (request.getCharacterEncoding() == null)) {
			String encoding = selectEncoding(request);
			if (encoding != null)
				request.setCharacterEncoding(encoding);
		}
		// process get parameters
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Map<String, String[]> paramMap = request.getParameterMap();
		String[] queryStringArray = { "" };
		if (httpRequest.getQueryString() != null) {
			queryStringArray = httpRequest.getQueryString().split("&");
		}
		for (int i = 0; i < queryStringArray.length; i++) {
			queryStringArray[i] = queryStringArray[i].replaceAll("(.*)=(.*)",
					"$1");
		}
		Set<String> keySet = paramMap.keySet();
		for (String key : keySet) {
			// check where param from
			boolean isFromGet = false;
			for (String paramFromGet : queryStringArray) {
				if (key.equals(paramFromGet)) {
					isFromGet = true;
				}
			}
			if (!isFromGet) {
				continue;
			}
			String[] paramArray = (String[]) paramMap.get(key);
			for (int i = 0; i < paramArray.length; i++) {
				paramArray[i] = new String(
						paramArray[i].getBytes("iso-8859-1"), encoding);
			}
		}
		chain.doFilter(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	/**
	 * @param request
	 * @return
	 */
	protected String selectEncoding(ServletRequest request) {
		return (this.encoding);
	}

}
