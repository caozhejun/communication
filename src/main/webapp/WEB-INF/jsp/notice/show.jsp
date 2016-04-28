<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>公告管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<%@ include file="../common/ueditor.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/notice.js"></script>
</head>
<body style="padding: 0px; margin: 0px">
	<div style="margin: 10px 0;"></div>
	<input type="hidden" id="type" name="type" value="${notice.type}"/>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="Notice.toList();" data-options="iconCls:'icon-back'">返回</a> 
	${notice.content}
</body>
</html>
