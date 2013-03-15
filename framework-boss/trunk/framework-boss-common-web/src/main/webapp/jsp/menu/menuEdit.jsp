<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
<form method="post" action="menu/update?navTabId=menu/level/${levelId}&callbackType=closeCurrent" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<input type="hidden" name="id" value="${menu.id}" />
	<div class="pageFormContent" layoutH="56">
		<c:if test="${levelId eq 2 or levelId eq 3}">
			<p>
				<label>上级菜单：</label>
				<input type="hidden" name="pid" value="${menu.pid}"/>
				<input type="text" class="required" name="showName" value="${menu.pmenu.sys_menu_name}"/>
				<a class="btnLook" href="menu/level/lookup/${levelId-1}" lookupGroup="">查找带回</a>
			</p>
		</c:if>
		<p>
			<label>菜单名：</label>
			<input type="text" name="sys_menu_name" value="${menu.sys_menu_name}" class="required" maxlength="20"/>
		</p>
		
		<p>
			<label>菜单链接：</label>
			<input type="text" name="sys_menu_url" class="required" value="${menu.sys_menu_url}"/>
		</p>
		
		<p>
			<label>菜单编码：</label>
			<input type="text" name="sys_menu_code" class="required" value="${menu.sys_menu_code}"/>
		</p>
		
		<p>
			<label>菜单描述：</label>
			<input type="text" name="sys_menu_description" value="${menu.sys_menu_description}"/>
		</p>
		
		<p>
			<label>备注：</label>
			<input type="text" name="remark" value="${menu.remark}"/>
		</p>
		
		<p>
			<label>菜单排序：</label>
			<input type="text" name="order_index" class="required digits" value="${menu.order_index}"/>
		</p>
		
		<p>
			<label>状态：</label>
			<select class="combox" name="state">
			      <option value="00" <c:if test="${menu.state eq '00'}">selected="selected"</c:if> >使用</option>
			      <option value="01" <c:if test="${menu.state eq '01'}">selected="selected"</c:if> >禁用</option>
			</select>
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
