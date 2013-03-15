<%----------------------------------------------------------------------
  Copyright(c) 2011 ALLINPAY. All Right Reserver.

  File Name:  FrameworkUserInfDetail
  Detail   :  该页用于 BOSS操作员基本信息表管理-BOSS操作员基本信息表明细信息
----------------------------------------------------------------------%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
<form method="post" action="" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<div class="pageFormContent" layoutH="56">
	 	<p>
			<label><seq></label>
			<input type="text" name="id" value="${infos.id}" class="required" maxlength="20"/>
		</p>
	 	<p>
			<label>操作员名称</label>
			<input type="text" name="userName" value="${infos.userName}" class="required" maxlength="20"/>
		</p>
	 	<p>
			<label>操作员密码</label>
			<input type="text" name="userPassword" value="${infos.userPassword}" class="required" maxlength="20"/>
		</p>
	 	<p>
			<label>当前状态</label>
			<input type="text" name="state" value="${infos.state}" class="required" maxlength="20"/>
		</p>
	 	<p>
			<label>USER_INST_ID</label>
				<input type="text" name="userInstId" value="${infos.userInstId}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>操作员真实姓名</label>
				<input type="text" name="realName" value="${infos.realName}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>手机号码</label>
				<input type="text" name="mobile" value="${infos.mobile}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>座机号码</label>
				<input type="text" name="contactPhone" value="${infos.contactPhone}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>邮件地址</label>
				<input type="text" name="contactEmail" value="${infos.contactEmail}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>归属机构</label>
				<input type="text" name="attributiveOrg" value="${infos.attributiveOrg}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>扩展1</label>
				<input type="text" name="misc1" value="${infos.misc1}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>扩展2</label>
				<input type="text" name="misc2" value="${infos.misc2}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>操作员描述</label>
				<input type="text" name="userDesc" value="${infos.userDesc}" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>最后更新者</label>
			<input type="text" name="recUpdUsr" value="${infos.recUpdUsr}" class="required" maxlength="20"/>
		</p>
	 	<p>
			<label>创建时间</label>
			<input type="text" name="rowCrtTs" value="${infos.rowCrtTs}" class="required" maxlength="20"/>
		</p>
	 	<p>
			<label>最后更新时间</label>
			<input type="text" name="recUpdTs" value="${infos.recUpdTs}" class="required" maxlength="20"/>
		</p>
	</div>

	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>
