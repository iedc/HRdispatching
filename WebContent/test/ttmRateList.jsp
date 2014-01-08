<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>结果展示</thead>
		<tbody>
			<tr>
				<td>数据1</td>
				<td>数据2</td>
			</tr>
			
			<c:forEach items="${ttmRates}" var="ttmRate">
				<tr>
					<td>${ttmRate.currencyPair}</td>
					<td>${ttmRate.value}</td>
				</tr>
			</c:forEach>
			 
		</tbody>
	</table>
	<a href="groupAdmin.do">查看测试数据</a>
</body>
</html>