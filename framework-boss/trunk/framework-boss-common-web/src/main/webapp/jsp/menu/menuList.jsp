<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
<!--
	$(document).ready(function() {
		$("#test").click( function () { 
			$.getJSON("menu/get/93", function(json){
				  alert("JSON Data: " + json.id);
			});
		});
	});
//-->
</script>

<form id="pagerForm" method="post" action="menu/level/${levelId}">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>

<form method="post" rel="pagerForm" action="menu/level/${levelId}" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>菜单名:</label>
				<input type="text" name="sys_menu_name" value="${frameworkSysMenu.sys_menu_name}"/>
			</li>
			<li>
				<label>菜单状态:</label>
				<select class="combox" name="state">
					<option value="">全部</option>
			      	<option value="00" <c:if test="${frameworkSysMenu.state eq '00'}">selected="selected"</c:if> >使用</option>
			      	<option value="01" <c:if test="${frameworkSysMenu.state eq '01'}">selected="selected"</c:if> >禁用</option>
				</select>
			</li>
		</ul>
		<div class="subBar">
			<ul>						
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
			</ul>
		</div>
	</div>
</div>
</form>

<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" target="navTab" rel="menuNav${levelId}" href="menu/add/${levelId}" title="添加"><span>添加菜单</span></a></li>
			<li><a class="delete" target="selectedTodo" rel="orderIndexs" postType="string" href="menu/deleteAll" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="orderIndexs" class="checkboxCtrl"></th>
				<c:if test="${levelId eq 2}">
					<th>所属一级菜单</th>
				</c:if>
				<c:if test="${levelId eq 3}">
					<th>所属一级菜单</th>
					<th>所属二级菜单</th>
				</c:if>
				<th>菜单名</th>
				<th>菜单链接</th>
				<th>菜单编码</th>
				<th>菜单描述</th>
				<th>状态</th>
				<th>备注</th>
				<th>菜单排序</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${page.result}" varStatus="s">
			<tr target="slt_objId" rel="${item.id}">
				<td><input name="orderIndexs" value="${item.id}" type="checkbox"></td>
				<c:if test="${levelId eq 2}">
					<td>${item.pmenu.sys_menu_name}</td>
				</c:if>
				<c:if test="${levelId eq 3}">
					<td>${item.ppmenu.sys_menu_name}</td>
					<td>${item.pmenu.sys_menu_name}</td>
				</c:if>
				<td>${item.sys_menu_name}</td>
				<td>${item.sys_menu_url}</td>
				<td>${item.sys_menu_code}</td>
				<td>${item.sys_menu_description}</td>
				<td>
					<c:choose>
						<c:when test="${item.state eq '01'}">禁用</c:when>
						<c:when test="${item.state eq '00'}">使用</c:when>
					</c:choose>
				</td>
				<td>${item.remark}</td>
				<td>${item.order_index}</td>
				<td>
				      <a class="btnDel"  href="menu/delete/${item.id}" target="ajaxTodo" title="你确定要删除吗?" >删除</a>
				      <a class="btnEdit" href="menu/edit/${item.id}/${levelId}" target="navTab" rel="menuNav${levelId}" title="编辑" >编辑</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="/jsp/system/panelBar.jsp"></c:import>
</div>