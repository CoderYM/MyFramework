<%----------------------------------------------------------------------
  Copyright(c) 2011 ALLINPAY. All Right Reserver.

  File Name:  FrameworkSysActionModify
  Detail   :  该页用于 FrameworkSysAction管理-FrameworkSysAction修改
----------------------------------------------------------------------%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
<form method="post" action="sysperm/modifyFrameworkSysActionAction?navTabId=menuNav&callbackType=closeCurrent" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<input type="hidden" name="id" value="${infos.id}"/>
	<div class="pageFormContent" layoutH="56">
	 	<p>
			<label>编号</label>
			<input type="text" name="id" value="${infos.id}" class="required" maxlength="20"/>
		</p>
	 	<p>
			<label>菜单ID</label>
			<input type="text" name="sysMenuId" value="${infos.sysMenuId}" class="required" maxlength="20"/>
		</p>
	 	<p>
			<label>权限编码</label>
				<input type="text" name="permissionCode" value="${infos.permissionCode}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>访问的action名字</label>
				<input type="text" name="actionName" value="${infos.actionName}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>访问的method名字</label>
				<input type="text" name="methodName" value="${infos.methodName}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>访问的class名字</label>
				<input type="text" name="simpleClassName" value="${infos.simpleClassName}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>类型</label>
				<input type="text" name="actionType" value="${infos.actionType}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>操作描述</label>
				<input type="text" name="actionDes" value="${infos.actionDes}" class="" maxlength="20"/>
		</p>
	</div>

	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">Save</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">Close</button></div></div></li>
		</ul>
	</div>
</form>
</div>

