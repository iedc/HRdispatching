<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/resources/common/meta.jsp" %>
<title><decorator:title/>_派遣企业人力资源规划软件</title>
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/style.css'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/jquery-ui.css'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/flexigrid.pack.css'/>" />
<script language="javascript" type="text/javascript" src="<c:url value='/resources/js/jquery.js'/>"></script>
<script language="javascript" type="text/javascript" src="<c:url value='/resources/js/jquery-ui.js'/>"></script>
<script language="javascript" type="text/javascript" src="<c:url value='/resources/js/flexigrid.pack.js'/>"></script>
<script language="javascript" type="text/javascript" src="<c:url value='/resources/js/com.js'/>"></script>
<decorator:head/>
<script>
$(function(){
	$('#_accordian').accordion({heightStyle:"content",header:"h3"});
});
</script>
</head>
<body <decorator:getProperty property="body.id" writeEntireProperty="true"/> <decorator:getProperty property="body.class" writeEntireProperty="true"/>>
<div id="header" class="header">
	<jsp:include page="/resources/common/header.jsp"/>
</div>
<div class="main">
	<div id="_accordian" class="l accordian_menu">
		<h3>人力规划</h3>
		<div>
		  <ul class="uls">
		  	<li><a href="listProject.do">查询项目信息</a></li>
		  	<li><a href="editProject.do">管理项目信息</a></li>
		  	<li><a href="editJob.do">管理工种</a></li>
		  	<li><a href="listPlanning.do">查询现有规划方案</a></li>
		  	<li><a href="listDispatch.do">执行规划</a></li>
		  </ul>
		</div>
		<h3>招聘计划</h3>
		<div>
		  <ul class="uls">
		  	<li><a href="#">员工信息管理</a></li>
		  	<li><a href="#">人员招聘计划管理</a></li>
		  	<li><a href="#">查询现有规划方案</a></li>
		  	<li>执行规划</li>
		  </ul>
		</div>
		<h3>派遣计划</h3>
		<div>
		  <ul class="uls">
		  	<li><a href="#">员工信息管理</a></li>
		  	<li><a href="#">人员招聘计划管理</a></li>
		  	<li><a href="#">查询现有规划方案</a></li>
		  	<li>执行规划</li>
		  </ul>
		</div>
		<h3>参数管理</h3>
		<div>
		  <p>
		  Cras dictum. Pellentesque habitant morbi tristique senectus et netus
		  et malesuada fames ac turpis egestas. Vestibulum ante ipsum primis in
		  faucibus orci luctus et ultrices posuere cubilia Curae; Aenean lacinia
		  mauris vel est.
		  </p>
		  <p>
		  Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus.
		  Class aptent taciti sociosqu ad litora torquent per conubia nostra, per
		  inceptos himenaeos.
		  </p>
		</div>
		<h3>其他</h3>
		<div>
		  <ul class="uls">
		  	<li><a href="home.do">返回首页</a></li>
		  </ul>
		</div>
	</div>
	<div class="l frameR">
		<decorator:body/>
	</div>
	<div class="clear"></div>
</div>

</body>
</html>