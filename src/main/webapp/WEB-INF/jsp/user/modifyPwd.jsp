<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>修改密码</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/user.js"></script>
</head>
<body style="padding:0px;margin:0px">
	<div class="easyui-panel" title="" style="width:400px" data-options="fit:true,border:false">
		<div style="padding:10px 60px 20px 60px">
			<form id="userForm" method="post">
				<table cellpadding="0" cellspacing="0" style="table-layout:fixed;">
					<tr>
						<td>原密码:</td>
						<td><input class="easyui-textbox" type="password" id="oldPwd" name="oldPwd" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>新密码:</td>
						<td><input class="easyui-textbox" type="password" id="newPwd" name="newPwd" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>确认密码:</td>
						<td><input class="easyui-textbox" type="password" id="confirmPwd" name="confirmPwd" data-options="required:true"></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="User.modifyPwd();" data-options="iconCls:'icon-save'">修改</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="User.cancelModifyPwd();" data-options="iconCls:'icon-cancel'">取消</a>
			</div>
		</div>
	</div>
</body>
</html>
