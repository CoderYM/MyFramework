package com.allinpay.its.boss.framework.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.allinpay.its.boss.framework.utils.SessionUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
* @ClassName: ApplicationFilter
* @Description: TODO
* @author yangmin
* @date 2012-8-23 上午11:02:48
 */
public class ApplicationFilter implements Filter {
	
	// 日志记录器
	public final Logger log = Logger.getLogger(this.getClass());
	
	String loginUrl = "";	
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpRequest.getSession();
        
        Object sessionObject = httpSession.getAttribute(SessionUtil.USER_MAP);
      //获得请求地址
		StringBuffer requestUrl = httpRequest.getRequestURL();
	    //截取字符串 生成登录的地址
		String url = requestUrl.toString();
        if(httpSession!=null && sessionObject!=null){
        		chain.doFilter(httpRequest, httpResponse);
		}else if(httpRequest.getHeader("x-requested-with")!=null && httpRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest") && !StringUtils.containsIgnoreCase(url, "/index")){
			// 未登录
			PrintWriter out = response.getWriter();
			StringBuilder builder = new StringBuilder();
			builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
			builder.append("alert(\"页面过期，请重新登录\");");
			builder.append("window.top.location.href=\"");
			builder.append("system/login");
			builder.append("\";</script>");
			out.print(builder.toString());
			out.close();
			//chain.doFilter(httpRequest, httpResponse);
		}else if (StringUtils.containsIgnoreCase(url, "index") || StringUtils.containsIgnoreCase(url, loginUrl)) {	
			chain.doFilter(httpRequest, httpResponse);
		}else{
			if(httpSession == null || sessionObject==null) {	
				httpResponse.sendRedirect(httpRequest.getContextPath()+loginUrl);
				chain.doFilter(httpRequest, httpResponse);
			}
		}
	}

	public void init(FilterConfig config) throws ServletException {
		loginUrl = config.getInitParameter("loginUrl");
	}
	
	public void destroy() {
	}
	
}