<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="caozj" uri="http://www.caozj.com"%>
<!DOCTYPE html >
<html>
<head>
<title>测试页面</title>
</head>
<body style="padding: 0px; margin: 0px">
	<caozj:tip content="我是你大哥的大哥" length="3" target="_self" />
	<br/>
	<caozj:tip length="3" href="#1">我是你大哥的大哥</caozj:tip>
	<c:if test="true">true</c:if><c:if test="false">false</c:if>
	<c:if test="true">
	<caozj:tip length="5" href="#1">我是你大哥的大哥
	<c<c:if test="true">true</c:if><c:if test="false">false</c:if>
	</caozj:tip>
	</c:if>
	<br />
	<caozj:permission url="/a/b.do" session="<%=session%>">有</caozj:permission>
	<caozj:permission url="/a/b.do" session="<%=session%>">${message}</caozj:permission>
	<caozj:permission url="/a/b.do" session="<%=session%>" content="${message}"/>
	${message}
</body>
</html>
