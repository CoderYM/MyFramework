<%----------------------------------------------------------------------
  Copyright(c) 2011 ALLINPAY. All Right Reserver.

  File Name:  FrameworkUserInfModify
  Detail   :  该页用于 BOSS操作员基本信息表管理-BOSS操作员基本信息表修改
----------------------------------------------------------------------%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
<form method="post" action="sysuser/modifyMyPassword" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<input type="hidden" name="id" value="${v_userId}"/>
	<div class="pageFormContent" layoutH="56">
	 	<dl>
				<dt>操作员名称：</dt>
				<dd>
					${v_userName }
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
	</div>

	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>
