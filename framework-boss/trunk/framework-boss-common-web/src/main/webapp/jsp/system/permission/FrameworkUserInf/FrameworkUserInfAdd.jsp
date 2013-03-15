<%----------------------------------------------------------------------
  Copyright(c) 2011 ALLINPAY. All Right Reserver.

  File Name:  FrameworkUserInfAdd
  Detail   :  该页用于 BOSS操作员基本信息表管理-BOSS操作员基本信息表新增
----------------------------------------------------------------------%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form method="post"
		action="sysuser/saveFrameworkUserInfAction?navTabId=sysuser&callbackType=closeCurrent"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="56">
			<dl>
				<dt>操作员名称：</dt>
				<dd>
					<input type="text" name="userName" maxlength="20" class="required" />
				</dd>
			</dl>

			<dl>
				<dt>密码:</dt>
				<dd>
					<input id="w_validation_pwd" type="password" name="userPassword"
						class="required alphanumeric" minlength="6" maxlength="20"
						alt="字母、数字、下划线 6-20位" />
				</dd>
			</dl>
			<dl>
				<dt>确认密码:</dt>
				<dd>
					<input type="password" name="rep_userPassword" class="required"
						equalto="#w_validation_pwd" />
				</dd>
			</dl>
			<dl>
				<dt>操作员真实姓名</dt>
				<dd>
					<input type="text" name="realName" class="" maxlength="20" />
				</dd>
			</dl>
			<dl>
				<dt>手机号码</dt>
				<dd>
					<input type="text" name="mobile" class="" maxlength="20" />
				</dd>
			</dl>
			<dl>
				<dt>座机号码</dt>
				<dd>
					<input type="text" name="contactPhone" class="" maxlength="20" />
				</dd>
			</dl>
			<dl>
				<dt>邮件地址</dt>
				<dd>
					<input type="text" name="contactEmail" class="" maxlength="20" />
				</dd>
			</dl>
			<dl>
				<dt>操作员描述</dt>
				<dd>
					<input type="text" name="userDesc" class="" maxlength="20" />
				</dd>
			</dl>
			<dl >
				<fieldset>
				<legend>系统角色:<font color=red>*</font></legend>
				   <c:forEach items="${sysRoleList}" var="role" >
			           <lable><input type="checkbox" name="roleIds" value="${role.id }"/>${role.sysRoleName }</lable>
			       </c:forEach>
			           
				</fieldset>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">关闭</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>

