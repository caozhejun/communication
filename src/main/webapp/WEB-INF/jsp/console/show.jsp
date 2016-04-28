<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Console</title>
<%@ include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/console/console.js"></script>
</head>
<body>
	<div class="wp cl mt-15 f-14">
		<h2>控制台</h2>
		<label for="userName"> 用户名:</label> <input type="text" id="userName" name="userName" class="input-text radius " /> <br />
		<label for="source">密码:</label> <input type="password" id="password" name="password" class="input-text radius " /> <br />
		<label for="script">脚本:</label>
		<textarea rows="30" cols="135" id="script" name="script"  class="java"></textarea>
		<br />
		<div class="mt-10">
			<input class="btn radius btn-success" type="button" value="执行" style="width:200px;"
				onclick="return Console.execute();">
		</div>
		<br />
		<div id="tip" style="color: red"></div>
	</div>
</body>
</html>