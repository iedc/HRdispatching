<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>派遣结果列表</title>
</head>
<body>
	<div class="box">
		<div class="content">
			<div id="addr1" class="loc icon">
				<samp class="t12"></samp>
				当前位置：派遣规划管理&nbsp;&raquo;&nbsp;
				<span class="gray" title="规划结果">规划结果</span>
			</div>
		</div>
		
		<dl class="solve-list ofc">
			<dt>派遣规划ID</dt>
			<dd>${planID }</dd>
		
			<dt>派遣规划名称</dt>
			<dd>${planName }</dd>
			
			<dt>涉及到的项目ID</dt>
			<dd>${projectIDs }</dd>
			
			<dt>可能的最低费用</dt>
			<dd>${objective }</dd>
			
			<c:forEach items="${staffRequired }" var="required">
				<dt>工种<b class="red">i</b>需要的人数</dt>
				<dd>${required }</dd>
			</c:forEach>
			<c:forEach items="${inventorys }" var="ivy">
				<dt>工种<b class="red">i</b>的现有人员：</dt>
				<dd>${ivy }</dd>
			</c:forEach>
			
			<c:forEach items="${needRecruit }" var="yi">	
				<dt>工种<b class="red">i</b>是否需要招募？</dt>
				<dd>${yi }</dd>
			</c:forEach>
			<c:forEach items="${staffRequired }" var="xoi">			
				<dt>工种<b class="red">i</b>需要招募的人数：</dt>
				<dd>${xoi }</dd>
			</c:forEach>
		</dl>
		<form id="_callSolve" action="" method="post">
			<input type="hidden" name="solvePlanID" id="solvePlanID" />
		</form>
	</div>
</body>
</html>