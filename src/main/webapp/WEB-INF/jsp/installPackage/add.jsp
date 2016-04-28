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
	<div class="easyui-panel" title="新增安装包" style="width:800px">
		<div style="padding:10px 60px 20px 60px">
			<form id="addForm" method="post" enctype="multipart/form-data">
				<table cellpadding="0" cellspacing="0" style="table-layout:fixed;">
					<tr>
						<td>版本号:</td>
						<td><input class="easyui-textbox" type="text" id="installVersion" name="installVersion" data-options="required:true" style="width:500px"></input></td>
					</tr>
					<tr>
						<td>说明:</td>
						<td><textarea rows="30" cols="70" class="easyui-validatebox" data-options="required:true" id="detail" name="detail"></textarea></td>
					</tr>
					<tr>
						<td>安装包</td>
						<td><input type="file" id="file" name="file" /></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="InstallPackage.add();" data-options="iconCls:'icon-add'">新增</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="InstallPackage.toList();" data-options="iconCls:'icon-cancel'">取消</a>
			</div>
		</div>
	</div>
</body>
</html>
