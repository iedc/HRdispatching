<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<meta name="heading" content="登录"/>
<meta name="menu" content="Login"/>
<%@ include file="resources/common/meta.jsp" %>
<link rel="stylesheet" type="text/css" media="all" href="resources/css/style.css" />
<script language="javascript" type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$("#go").click(function(){
		var table = document.getElementById("loginForm");
		table.submit();
	});
	$(document).keydown(function(event){ 
		if(event.keyCode==13){
			$("#go").click();
		}
	});
	$("#oauth").click(function(){
		window.location.href="http://testopen.admasterapi.com/oauth/authorize?client_id=3e97d3e4ec10fe372903&response_type=token";
	});
})
</script>
</head>
<body>
<div class="sbox">
	<h1 title="系统">系统</h1>
    <div class="cont">
		<h2 title="系统登录">系统登录</h2>
        <form method="get" id="loginForm" action="login.do">
            <ul class="uls set">
                <li class="errorTip" style="display:none"></li>
	            <li>
	            	<label class="required desc"></label>
	                <input type="text" class="text medium" name="username" tabindex="1" placeholder="用户名" />
	            </li>
	            <li>
	            	<label class="required desc"></label>
	                <input type="password" id="password" class="text medium" name="password" tabindex="2" placeholder="请输入密码" />
	            </li>
	            <li>
	            	<label class="required desc"></label>
	                <input type="button" id="go" class="hand btn102x26" name="login" value="登录" tabindex="3" />
	                <input type="button" id="oauth" class="hand btn102x26" name="oauthLogin" value="第三方登录" tabindex="4""/>
	            </li>
            	<li class="alg_c dev">如遇问题，请联系超级管理员【admin】<br/>邮箱【test@test.test】</li>
            </ul>
        </form>
	</div>
</div>
</body>
</html>