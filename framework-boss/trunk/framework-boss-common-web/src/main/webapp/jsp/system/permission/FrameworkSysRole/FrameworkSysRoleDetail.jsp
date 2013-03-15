<%----------------------------------------------------------------------
  Copyright(c) 2011 ALLINPAY. All Right Reserver.

  File Name:  FrameworkSysRoleDetail
  Detail   :  该页用于 FrameworkSysRole管理-FrameworkSysRole明细信息
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
			<label>系统角色编码</label>
				<input type="text" name="sysRoleCode" value="${infos.sysRoleCode}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>系统角色名</label>
				<input type="text" name="sysRoleName" value="${infos.sysRoleName}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>系统角色描述</label>
				<input type="text" name="sysRoleDescription" value="${infos.sysRoleDescription}" class="" maxlength="20"/>
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
