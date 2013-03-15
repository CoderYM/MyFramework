<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
<form method="post" action="menu/insert/${levelId}?navTabId=menu/level/${levelId}&callbackType=closeCurrent" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<input type="hidden" name="state" value="00" />
	<div class="pageFormContent" layoutH="56">
		<c:if test="${levelId eq 2 or levelId eq 3}">
			<p>
				<label>上级菜单：</label>
				<input type="hidden" name="pid" value=""/>
				<input type="text" class="required" name="showName" value=""/>
				<a class="btnLook" href="menu/level/lookup/${levelId-1}" lookupGroup="">查找带回</a>
			</p>
		</c:if>
		<p>
			<label>菜单名：</label>
			<input type="text" name="sys_menu_name" class="required" maxlength="20"/>
		</p>
		
		<p>
			<label>菜单链接：</label>
			<input type="text" name="sys_menu_url" class="required"/>
		</p>
		
		<p>
			<label>菜单编码：</label>
			<input type="text" name="sys_menu_code" class="required"/>
		</p>
		
		<p>
			<label>菜单描述：</label>
			<input type="text" name="sys_menu_description"/>
		</p>
		
		<p>
			<label>备注：</label>
			<input type="text" name="remark"/>
		</p>
		
		<p>
			<label>菜单排序：</label>
			<input type="text" name="order_index" class="required digits"/>
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
