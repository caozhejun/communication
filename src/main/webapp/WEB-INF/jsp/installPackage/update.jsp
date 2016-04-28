<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>安装包管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/installPackage.js"></script>
</head>
<body style="padding:0px;margin:0px">
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="修改安装包说明" style="width:700px">
		<div style="padding:10px 60px 20px 60px">
			<form id="updateForm" method="post">
				<input type="hidden" id="installVersion" name="installVersion" value="${installPackage.installVersion }" />
				<table cellpadding="0" cellspacing="0" style="table-layout:fixed;">
					<tr>
						<td>说明:</td>
						<td><textarea rows="30" cols="70" class="easyui-validatebox" data-options="required:true" id="detail" name="detail">${installPackage.detail }</textarea></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="InstallPackage.update();" data-options="iconCls:'icon-add'">修改</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="InstallPackage.toList();" data-options="iconCls:'icon-cancel'">取消</a>
			</div>
		</div>
	</div>
</body>
</html>
