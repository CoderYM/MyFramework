<%----------------------------------------------------------------------
  Copyright(c) 2011 ALLINPAY. All Right Reserver.

  File Name:  FrameworkSysPermissionsQuery
  Detail   :  该页用于 FrameworkSysPermission管理-FrameworkSysPermission查询
----------------------------------------------------------------------%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

		<form id="pagerForm" method="post" action="permission">
			<input type="hidden" name="pageNum" value="1" /> <input
				type="hidden" name="numPerPage" value="${page.pageSize}" /> <input
				id="sysMenuId_id" type="hidden" name="sysMenuId" value="">
		</form>

		<form method="post" rel="pagerForm" action="permission"
			onsubmit="return navTabSearch(this)">
			<div class="pageHeader">
				<div class="searchBar">
					<ul class="searchContent">
						<li><label>关键词：</label> <input type="text" name="keywords"
							value="" /></li>

					</ul>
					<div class="subBar">
						<span style="margin-left: 5px; line-height: 25px; float: left">总数:
							<strong>${page.totalRecord}</strong>
						</span>
						<ul>
							<li><div class="buttonActive">
									<div class="buttonContent">
										<button type="submit">搜索</button>
									</div>
								</div></li>
						</ul>
					</div>
				</div>
			</div>
		</form>
		<div class="pageContent">

			<div class="panelBar">
				<ul class="toolBar">
					<li><a class="add" target="dialog" rel="bookNav" mask="true"
						href="permission/addFrameworkSysPermissionToPage?sysMenuId=${syspermModel.sysMenuId}" title="添加"><span>添加</span></a></li>
					<li><a class="delete" target="selectedTodo" rel="orderIndexs"
						postType="string" href="permission/deleteAll" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
					<li class="line">line</li>
				</ul>
			</div>


			<table class="table" width="100%" layoutH="138">
				<thead>
					<tr>
						<th width="50"><input type="checkbox" group="orderIndexs" class="checkboxCtrl"></th>
						<th width="130">ID</th>
						<th width="130">菜单ID</th>
						<th width="130">权限编码</th>
						<th width="130">权限名</th>
						<th width="130">权限描述</th>
						<th width="130">创建人ID</th>
						<th width="130">创建时间</th>
						<th width="130">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${page.result}" varStatus="s">
						<tr target="slt_objId" rel="${item.id }">
							<td width="50"><input name="orderIndexs" value="${item.id}" type="checkbox"></td>

							<td width="130">${item.id}</td>

							<td width="130">${item.sysMenuId}</td>

							<td width="130">${item.permissionCode}</td>

							<td width="130">${item.permissionName}</td>

							<td width="130">${item.permissionDescription}</td>


							<td width="130">${item.createUserId}</td>
							<td><fmt:formatDate value="${item.createTime}"
									pattern="yyyy-MM-dd" /></td>
							<td width="130">
								<a class="btnDel" href="permission/delete/${item.id }" target="ajaxTodo" title="你确定要删除吗?">删除</a>
								<a class="btnEdit" href="permission/modify/${item.id }" target="navTab" rel="bookNav" title="编辑">编辑</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<c:import url="/jsp/system/panelBar.jsp"></c:import>
		</div>
