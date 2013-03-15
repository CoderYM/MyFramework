<%----------------------------------------------------------------------
  Copyright(c) 2011 ALLINPAY. All Right Reserver.

  File Name:  FrameworkActionLogsQuery
  Detail   :  该页用于 系统日志记录表管理-系统日志记录表查询
----------------------------------------------------------------------%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form id="pagerForm" method="post" action="aclog">
	<input type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="${page.pageSize}" />

</form>

<form method="post" rel="pagerForm" action="aclog"
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
			<li><a class="add" target="navTab" rel="aclogNav"
				href="aclog/addFrameworkActionLogToPage" title="添加"><span>添加</span></a></li>
			<li><a class="delete" target="selectedTodo" rel="orderIndexs"
					postType="string" href="aclog/deleteAll" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>


	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50"><input type="checkbox" group="orderIndexs" class="checkboxCtrl"></th>
				  	<th width="130">ID<seq>  </th>
				  	<th width="130">操作时间</th>
				  	<th width="130">操作人员</th>
				  	<th width="130">操作类型</th>
				  	<th width="130">操作内容</th>
				  	<th width="130">备注    </th>
				  	<th width="130">版本号  </th>
				  	<th width="130">操作对象名</th>
				  	<th width="130">操作对象方法</th>
				  	<th width="130">操作结果</th>
				  	<th width="130">日志类型</th>
				  	<th width="130">授权是否通过:true通过；false未通过</th>
				  	<th width="130">操作action名字</th>
				  	<th width="130">捕捉到的参数信息</th>
				  <th width="130">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${page.result}" varStatus="s">
			<tr target="slt_objId" rel="${item.id }">
				<td width="50"><input name="orderIndexs" value="${item.id }" type="checkbox"></td>
				   
				  	<td width="130">${item.id}</td>
						<td><fmt:formatDate value="${item.logTime}" pattern="yyyy-MM-dd"/></td>
				   
				  	<td width="130">${item.logUser}</td>
				   
				  	<td width="130">${item.logOperate}</td>
				   
				  	<td width="130">${item.logContent}</td>
				   
				  	<td width="130">${item.remark}</td>
				   
				  	<td width="130">${item.version}</td>
				   
				  	<td width="130">${item.logOperateClass}</td>
				   
				  	<td width="130">${item.logOperateMethod}</td>
				   
				  	<td width="130">${item.logOperateResult}</td>
				   
				  	<td width="130">${item.logType}</td>
				   
				  	<td width="130">${item.isAuthed}</td>
				   
				  	<td width="130">${item.logOperateActionName}</td>
				   
				  	<td width="130">${item.changeTableInfo}</td>
				  	<td width="130">
						<a class="btnDel" href="aclog/delete/${item.id }" target="ajaxTodo" title="你确定要删除吗?">删除</a>
						<a class="btnEdit" href="aclog/modify/${item.id }" target="navTab" rel="aclogNav" title="编辑">编辑</a>
				   </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="/jsp/system/panelBar.jsp"></c:import>
</div>

