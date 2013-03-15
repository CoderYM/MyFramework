<%----------------------------------------------------------------------
  Copyright(c) 2011 ALLINPAY. All Right Reserver.

  File Name:  FrameworkSysPermissionAdd
  Detail   :  该页用于 FrameworkSysPermission管理-FrameworkSysPermission新增
----------------------------------------------------------------------%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
<form method="post" action="permission/saveFrameworkSysPermissionAction" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
<input type="hidden" name="sysMenuId" value="${sysMenuId}">
	<div class="pageFormContent" layoutH="56">
	 	<p>
			<label>权限编码</label>
				<input type="text" name="permissionCode" class="required" maxlength="20"/>
		</p>
	 	<p>
			<label>权限名</label>
				<input type="text" name="permissionName" class="required" maxlength="20"/>
		</p>
	 	<p>
			<label>权限描述</label>
				<textarea style="width:50%;height:100px"  name="permissionDescription" maxlength="200">
				</textarea>
		</p>
	 	
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>

