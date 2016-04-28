<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Test Page</title>
<%@ include file="../common/header.jsp"%>
</head>
<body>
	<div class="wp cl mt-15 f-14">
		<table class="table table-border table-bordered table-striped table-hover radius table-bg text-c">
			<thead>
				<th>id</th>
				<th>名称</th>
				<th>操作</th>
			</thead>
			<c:forEach items="${list}" var="user">
				<tr>
					<td>${user.id }</td>
					<td>${user.name }</td>
					<td><a href="#" onclick="deleteUser('${user.id }')">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		alert("list loaded");
	});

	function deleteUser(id) {
		window.self.location.href = contextPath + "/esbuser/delete.do?id=" + id;
	}
</script>
</html>