<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>业务管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/buss.js"></script>
</head>
<body style="padding:0px;margin:0px">
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="修改业务" style="width:900px">
		<div style="padding:10px 60px 20px 60px">
			<form id="bussForm" method="post">
				<table cellpadding="0" cellspacing="0" style="table-layout:fixed;">
					<tr>
						<td>名称:</td>
						<td><input class="easyui-textbox" type="text" id="name" name="name" data-options="required:true,readonly:true" value="${buss.name }"></input></td>
					</tr>
					<tr>
						<td>描述:</td>
						<td><input class="easyui-textbox" type="text" id="description" name="description" data-options="required:true" value="${buss.description }"></input></td>
					</tr>
					<tr>
						<td>脚本:</td>
						<td><textarea rows="30" cols="70" class="easyui-validatebox" data-options="required:true" id="script" name="script">${buss.script}</textarea> <font color="red"
							size="3px"><br />(内置参数params,为Map&lt;String, String[]&gt; params = request.getParameterMap();)</font></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="Buss.update();" data-options="iconCls:'icon-save'">修改</a> <a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="Buss.toList();" data-options="iconCls:'icon-cancel'">取消</a>
			</div>
		</div>

	</div>
</body>
</html>
