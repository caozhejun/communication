<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="caozj" uri="http://www.caozj.com"%>
<!DOCTYPE html>
<html>
<head>
<title>广告管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<%@ include file="../common/ueditor.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/advertisement.js"></script>
</head>
<body style="padding: 0px; margin: 0px">
	<div style="margin: 10px 0;"></div>
	<input type="hidden" id="id" name="id" value="${advertisement.id}" />
	<input type="hidden" id="avoid" name="avoid" value="${searchForm.avoid}"/>
	<input type="hidden" id="userAccount" name="userAccount" value="${searchForm.userAccount}"/>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="Advertisement.backList();" data-options="iconCls:'icon-back'">返回</a>

	<br /> ${advertisement.content}
</body>
</html>
