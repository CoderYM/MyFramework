<%----------------------------------------------------------------------
  Copyright(c) 2011 ALLINPAY. All Right Reserver.

  File Name:  FrameworkActionLogAdd
  Detail   :  该页用于 系统日志记录表管理-系统日志记录表新增
----------------------------------------------------------------------%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
<form method="post" action="aclog/saveFrameworkActionLogAction?navTabId=aclog&callbackType=closeCurrent" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<div class="pageFormContent" layoutH="56">
	 	<p>
			<label>ID<seq>  </label>
			<input type="text" name="id" class="required" maxlength="20"/>
		</p>
	 	<p>
			<label>操作时间</label>
				<input type="text" name="logTime" class="date" maxlength="20"/>
		</p>
	 	<p>
			<label>操作人员</label>
				<input type="text" name="logUser" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>操作类型</label>
				<input type="text" name="logOperate" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>操作内容</label>
				<input type="text" name="logContent" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>备注    </label>
				<input type="text" name="remark" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>版本号  </label>
				<input type="text" name="version" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>操作对象名</label>
				<input type="text" name="logOperateClass" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>操作对象方法</label>
				<input type="text" name="logOperateMethod" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>操作结果</label>
				<input type="text" name="logOperateResult" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>日志类型</label>
				<input type="text" name="logType" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>授权是否通过:true通过；false未通过</label>
				<input type="text" name="isAuthed" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>操作action名字</label>
				<input type="text" name="logOperateActionName" class="" maxlength="20"/>
		</p>
	 	<p>
			<label>捕捉到的参数信息</label>
				<input type="text" name="changeTableInfo" class="" maxlength="20"/>
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

