<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<form id="pagerForm" method="post" action="menu/level/lookup/${levelId}">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="menu/level/lookup/${levelId}" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>菜单名:</label>
				<input class="textInput" name="sys_menu_name" value="" type="text">
			</li>	  
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th>菜单名</th>
				<th>菜单链接</th>
				<th>菜单编码</th>
				<th>菜单描述</th>
				<th>状态</th>
				<th>备注</th>
				<th>菜单排序</th>
				<th>查找带回</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${page.result}" varStatus="s">
				<tr target="slt_objId" rel="${item.id}">
					<td>${item.sys_menu_name}</td>
					<td>${item.sys_menu_url}</td>
					<td>${item.sys_menu_code}</td>
					<td>${item.sys_menu_description}</td>
					<td>${item.state}</td>
					<td>${item.remark}</td>
					<td>${item.order_index}</td>
					<td>
						<a href="javascript:$.bringBack({pid:'${item.id}', showName:'${item.sys_menu_name}'})" title="查找带回">选择</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:import url="/jsp/system/lookupPanelBar.jsp"></c:import>
</div>