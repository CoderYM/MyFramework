<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/common/taglibs.jsp"%>

<div class="panelBar">
	<div class="pages">
		<span>显示</span>
		<select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
			<c:forEach begin="10" end="40" step="10" varStatus="s">
				<option value="${s.index}" ${page.pageSize eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
			</c:forEach>
		</select>
		<span>条，共: ${page.totalRecord}</span>
	</div>
	
	<div class="pagination" targetType="navTab" totalCount="${page.totalRecord}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.pageIndex}"></div>
</div>