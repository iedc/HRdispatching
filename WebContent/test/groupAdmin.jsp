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
	<form action="groupAdmin.do">
		<input name="create" type="submit" value="create" />
		<input name="update" type="submit" value="update" />
		<input name="delete" type="submit" value="delete" />
		<table id="group" name="groups">
			<tr>
				<td>choose</td>
				<td>group name</td>
			</tr>
			<c:forEach items="${group}" var="groups">
				<tr>
					<td><input name="groupName" type="radio" value="${group.groupName}"/></td>
					<td>${group.groupName}</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>