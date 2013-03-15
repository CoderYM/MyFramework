<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/css_meta.jsp"%>
<%@ include file="/common/js_meta.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<title>通联支付</title>

	<script type="text/javascript">
		$(function(){
			
		});
		//清理浏览器内存,只对IE起效,FF不需要
		if ($.browser.msie) {
			window.setInterval("CollectGarbage();", 10000);
		}
	</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="">Logo</a>
				<ul class="nav">
					<li><a href="sysuser/initModifyMyPassword" target="dialog" mask="true">修改密码</a></li>
					<li><a href="logOut"  title="你确定要退出系统吗?">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">blue</div></li>
					<li theme="green"><div>green</div></li>
					<li theme="purple"><div>purple</div></li>
					<li theme="silver"><div>silver</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>
		</div>
		
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>菜单</h2><div>collapse</div></div>
				<div class="accordion" fillSpace="sideBar">
					<c:forEach var="item" items="${menuLevel1}">
						<div class="accordionHeader">
							<h2><span>Folder</span>${item.sys_menu_name}</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<c:forEach var="item2" items="${menuLevel2}">
									<c:if test="${item.id eq item2.pid}">
										<li><a>${item2.sys_menu_name}</a>
											<ul>
												<c:forEach var="item3" items="${menuLevel3}">
													<c:if test="${item2.id eq item3.pid}">
														<li><a href="${item3.sys_menu_url}" target="navTab" rel="${item3.sys_menu_url}">${item3.sys_menu_name}</a></li>
													</c:if>
												</c:forEach>
											</ul>
										</li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:void(0)"><span><span class="home_icon">主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:void(0)">主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div>
						<div class="accountInfo">
							<div class="right">
								<p></p>
							</div>
							<p><span></span></p>
							<p></p>
						</div>
						
						<div class="pageFormContent" layoutH="80">
							<p>
							</p>
						</div>

					</div>
				</div>
			</div>
		</div>

	</div>
	
	<div id="footer">通联支付BOSS管理平台</div>

</body>
</html>