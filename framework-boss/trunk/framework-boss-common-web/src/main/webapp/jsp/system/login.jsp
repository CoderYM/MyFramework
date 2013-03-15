<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/css_meta.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>通联支付</title>

	<script type="text/javascript">
		function toggleBox(boxId){
			var $box = $("#"+boxId);
			if ($box.is(":visible")) $box.slideUp();
			else $box.slideDown();
		}
	</script>
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<img src="<c:url value="/css/themes/default/images/logo.png"/>" />
			</h1>

			<div class="login_headerContent">
				<div class="navList">
					<ul>
					</ul>
				</div>
				<h2 class="login_title">请登录</h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form method="post" action="<c:url value="/index"/>">
					<c:if test="${!empty errMessage}"><p style="color: red">${errMessage}</p></c:if>
					<p>
						<label>用户名：</label>
						<input type="text" name="userName" size="20" class="login_input" />
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="userPassword" size="20" class="login_input" />
					</p>
					
					<!--<p>
						<label>验证码：</label>
						<input class="code" type="text" size="5" />
						<span><img src="themes/default/images/header_bg.png" alt="" width="75" height="24" /></span>
					</p>-->
					
					<div class="login_bar">
						<input class="sub" type="submit" value=""/>
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="<c:url value="/css/themes/default/images/login_banner.jpg"/>" /></div>
			<div class="login_main">
				<!-- <ul class="helpList">
					<li><a href="javascript:toggleBox('forgotPwd')">Forgot Password?</a></li>
					<li id="forgotPwd" style="background: none; display: none">
					<form method="post" action="myaccount/member.do?method=forgetPassword" onsubmit="return validateCallback(this);">
						<p>
							<label>Username：</label>
							<input type="text" name="userName" class="textInput required" size="20"/>
							<span class="info">Please enter your username to retrieve your password.</span>
						</p>
						<p><input type="submit" value="Submit"/></p>
					</form>
					</li>
				</ul> -->

			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2013 Allinpay Inc. All Rights Reserved.
		</div>
	</div>
</body>
</html>
