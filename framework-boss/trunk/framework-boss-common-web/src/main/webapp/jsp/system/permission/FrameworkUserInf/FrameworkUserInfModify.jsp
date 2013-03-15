<%----------------------------------------------------------------------
  Copyright(c) 2011 ALLINPAY. All Right Reserver.

  File Name:  FrameworkUserInfModify
  Detail   :  该页用于 BOSS操作员基本信息表管理-BOSS操作员基本信息表修改
----------------------------------------------------------------------%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
<form method="post" action="sysuser/modifyFrameworkUserInfAction?navTabId=sysuser&callbackType=closeCurrent" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<input type="hidden" name="id" value="${infos.id}"/>
	<div class="pageFormContent nowrap" layoutH="56">
	 	<dl>
				<dt>操作员名称：</dt>
				<dd>
					<input type="text" name="userName" maxlength="20" class="required" value="${infos.userName }"/>
				</dd>
			</dl>

			<dl>
				<dt>操作员真实姓名</dt>
				<dd>
					<input type="text" name="realName" class="" maxlength="20" value="${infos.realName }" />
				</dd>
			</dl>
			<dl>
				<dt>手机号码</dt>
				<dd>
					<input type="text" name="mobile" class="" maxlength="20" value="${infos.mobile }"/>
				</dd>
			</dl>
			<dl>
				<dt>座机号码</dt>
				<dd>
					<input type="text" name="contactPhone" class="" maxlength="20" value="${infos.contactPhone }"/>
				</dd>
			</dl>
			<dl>
				<dt>邮件地址</dt>
				<dd>
					<input type="text" name="contactEmail" class="" maxlength="20" value="${infos.contactEmail }"/>
				</dd>
			</dl>
			<dl>
				<dt>操作员描述</dt>
				<dd>
					<input type="text" name="userDesc" class="" maxlength="20" value="${infos.userDesc }"/>
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
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>
<script language="javascript">
var userRoles = "${userRoles}";
if(userRoles!=null && userRoles!=""){
  var roles = userRoles.split(",");
  for(var i=0;i<roles.length;i++){
    $("input[type=checkbox][name=roleIds][value="+roles[i]+"]").attr("checked",true);
	//    alert($("input[type=checkbox][name=roleIds][value="+roles[i]+"]"));
  }
}
</script>
