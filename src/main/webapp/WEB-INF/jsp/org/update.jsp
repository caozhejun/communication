<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>机构管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/org.js"></script>
</head>
<body style="padding: 0px; margin: 0px">
	<div style="margin: 20px 0;"></div>
	<div class="easyui-panel" title="修改机构" style="width: 500px">
		<div style="padding: 10px 60px 20px 60px">
			<form id="form" method="post">
				<input type="hidden" id="id" name="id" value="${org.id}"/>
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td>名称:</td>
						<td><input class="easyui-textbox" type="text" id="name" name="name" data-options="required:true" value="${org.name }"></input></td>
					</tr>
					<tr>
						<td>地址:</td> 
						<td><input class="easyui-textbox" type="text" id="address" name="address" data-options="required:true" value="${org.address }"></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="Org.update();" data-options="iconCls:'icon-save'">修改</a> <a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="Org.toList();" data-options="iconCls:'icon-cancel'">取消</a>
			</div>
		</div>

	</div>
</body>
</html>
