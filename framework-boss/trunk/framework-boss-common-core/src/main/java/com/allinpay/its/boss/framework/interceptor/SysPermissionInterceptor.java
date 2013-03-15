package com.allinpay.its.boss.framework.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allinpay.its.boss.framework.utils.JsonUtil;
import com.allinpay.its.boss.framework.utils.SessionUtil;
import com.allinpay.its.boss.framework.utils.WebConstant;
import com.allinpay.its.boss.system.permission.model.ActionCheck;
import com.allinpay.its.boss.system.permission.model.FrameworkActionLog;
import com.allinpay.its.boss.system.permission.service.FrameworkActionLogServiceImpl;
import com.allinpay.its.boss.system.permission.service.FrameworkSysPermissionServiceImpl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;

@Repository
public class SysPermissionInterceptor extends HandlerInterceptorAdapter {
	
	@Resource
	private FrameworkSysPermissionServiceImpl sysPermissionServiceImpl;

	@Resource
	private FrameworkActionLogServiceImpl actionLogServiceImpl;
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String url = request.getRequestURI();
//		String[] noFilterUrl = new String[]{".jsp",".css",".jpg",".gif",".png",".js",".xml"};
//		for(String nourl: noFilterUrl){
//			if(StringUtils.containsIgnoreCase(url, nourl)){
//				return true;
//			}
//		}
		//取类名
		String className = handler.getClass().getSimpleName();
		
		MethodNameResolver methodNameResolver = null;
		methodNameResolver = new InternalPathMethodNameResolver();
		//方法名
		String methodName = methodNameResolver.getHandlerMethodName(request);
		//方法对象
		//Method method = BeanUtils.findDeclaredMethodWithMinimalParameters(handler.getClass(), methodName);
		
		//请求Action名
		String contextName = request.getContextPath()+"/";
		String reqName = StringUtils.replace(url, contextName, "");
		
		Map<String, Object> map = ((Map<String, Object>) request.getSession().getAttribute(SessionUtil.USER_MAP));
		String userId = null;
		String userName = null;
		if(StringUtils.equals(reqName, "")){//跳过主页登录
			return true;
		}
		if(StringUtils.equals(reqName, "index") && StringUtils.equals(methodName, "index")){//跳过登陆action
			return true;
		}
		if(map != null){
			userId = String.valueOf((Long)map.get(SessionUtil.USER_ID));
			userName = (String)map.get(SessionUtil.USER_NAME);
			if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(userName)){
				toLoginAlert(response,"noSession");
				return false;
			}
		}else{
			toLoginAlert(response,"noSession");
			return false;
		}

		/**
		 * 权限拦截处理
		 */
		boolean isAuthed = false;
		FrameworkActionLog operateLog = new FrameworkActionLog();
		ActionCheck isAccessAction = sysPermissionServiceImpl.checkActionAccess(reqName, methodName, className);
		ActionCheck isCheckAction = sysPermissionServiceImpl.checkActionAccessByUserId(userId, reqName, methodName, className);
		if(isAccessAction.isAccess()){//当配置表中不存在的情况直接通过
			isAuthed = true;
			operateLog.setLogContent("无配置的操作");
		}else{
			if(isCheckAction.isAccess() || WebConstant.SUPPER_MANAGER_USER.contains("")){
				operateLog.setLogContent(isCheckAction.getActionDesc());
				isAuthed = true;
			}
		}
		
		operateLog.setRemark(request.getRemoteAddr());//记录IP
		operateLog.setIsAuthed(String.valueOf(isAuthed));
		operateLog.setLogOperateActionName(reqName);
		operateLog.setChangeTableInfo(JsonUtil.toJsonByObj(request.getParameterMap()));//记录参数集合JSON格式
		if(isAuthed){//通过验证
			operateLog.setLogOperateResult(WebConstant.OPERATE_SUCCESS);//操作成功
			actionLogServiceImpl.add(operateLog);
		}else{
			operateLog.setLogOperateResult(WebConstant.OPERATE_FAIL);//操作失败
			actionLogServiceImpl.add(operateLog);
			toLoginAlert(response,"noPermit");
		}
		
		
		return super.preHandle(request, response, handler);
	}
	public void toLoginAlert(HttpServletResponse response,String flag){
		PrintWriter out;
		try {
			out = response.getWriter();
			StringBuilder builder = new StringBuilder();
			builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
			if(StringUtils.equals(flag, "noPermit")){
				
				builder.append("alert(\"无权限访问,非法的请求!\");");
			}else{
				builder.append("alert(\"Session过期,请重新登录!\");");
			}
			builder.append("window.top.location.href=\"");
			builder.append("jsp/system/login.jsp");
			builder.append("\";</script>");
			out.print(builder.toString());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
