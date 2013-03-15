<%----------------------------------------------------------------------
  Copyright(c) 2011 ALLINPAY. All Right Reserver.

  File Name:  FrameworkSysActionsQuery
  Detail   :  该页用于 请求权限配置表管理-请求权限配置表查询
----------------------------------------------------------------------%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form id="pagerForm" method="post" action="sysperm">
	<input type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="${page.pageSize}" />

</form>

<form method="post" rel="pagerForm" action="sysperm"
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
			<li><a class="add" target="navTab" rel="bookNav"
				href="sysperm/addFrameworkSysActionToPage" title="添加"><span>添加</span></a></li>
			<li><a class="edit" target="navTab" rel="bookNav"
				href="sysperm/modify/{slt_objId}" title="修改"><span>修改</span></a></li>
			<li><a class="delete" target="ajaxTodo"
				href="sysperm/delete/{slt_objId}" title="你确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>


	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50"></th>
				  	<th width="130">seq</th>
				  	<th width="130">菜单ID</th>
				  	<th width="130">权限编码</th>
				  	<th width="130">访问的action名字</th>
				  	<th width="130">访问的method名字</th>
				  	<th width="130">访问的class名字</th>
				  	<th width="130">类型</th>
				  	<th width="130">操作描述</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${page.result}" varStatus="s">
			<tr target="slt_objId" rel="${item.id }">
				<td width="50">${s.index + 1}</td>
				   
				  	<td width="130">${item.id}</td>
				   
				  	<td width="130">${item.sysMenuId}</td>
				   
				  	<td width="130">${item.permissionCode}</td>
				   
				  	<td width="130">${item.actionName}</td>
				   
				  	<td width="130">${item.methodName}</td>
				   
				  	<td width="130">${item.simpleClassName}</td>
				   
				  	<td width="130">${item.actionType}</td>
				   
				  	<td width="130">${item.actionDes}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="/jsp/system/panelBar.jsp"></c:import>
</div>

