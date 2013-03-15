<%----------------------------------------------------------------------
  Copyright(c) 2011 ALLINPAY. All Right Reserver.

  File Name:  FrameworkSysPermissionDetail
  Detail   :  该页用于 FrameworkSysPermission管理-FrameworkSysPermission明细信息
----------------------------------------------------------------------%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
<form method="post" action="" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<div class="pageFormContent" layoutH="56">
	 	<p>
			<label>ID</label>
			<input type="text" name="id" value="${infos.id}" class="required" maxlength="20"/>
		</p>
	 	<p>
			<label>菜单ID</label>
				<input type="text" name="sysMenuId" value="${infos.sysMenuId}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>权限编码</label>
				<input type="text" name="permissionCode" value="${infos.permissionCode}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>权限名</label>
				<input type="text" name="permissionName" value="${infos.permissionName}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>权限描述</label>
				<input type="text" name="permissionDescription" value="${infos.permissionDescription}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>状态</label>
				<input type="text" name="state" value="${infos.state}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>备注</label>
				<input type="text" name="remark" value="${infos.remark}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>创建人ID</label>
				<input type="text" name="createUserId" value="${infos.createUserId}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>创建时间</label>
				<input type="text" name="createTime" value="${infos.createTime}" class="date" maxlength="20"/>
		</p>
	 	<p>
			<label>修改人ID</label>
				<input type="text" name="modifyUserId" value="${infos.modifyUserId}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>修改时间</label>
				<input type="text" name="modifyTime" value="${infos.modifyTime}" class="date" maxlength="20"/>
		</p>
	 	<p>
			<label>版本号</label>
				<input type="text" name="version" value="${infos.version}" class="" maxlength="20"/>
		</p>
	</div>

	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">Close</button></div></div></li>
		</ul>
	</div>
</form>
</div>
