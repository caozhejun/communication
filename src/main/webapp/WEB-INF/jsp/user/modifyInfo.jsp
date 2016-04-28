<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>修改个人信息</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/user.js"></script>
</head>
<body style="padding:0px;margin:0px">
	<div class="easyui-panel" title="" style="width:400px" data-options="fit:true,border:false">
		<div style="padding:10px 60px 20px 60px">
			<form id="userForm" method="post">
				<input type="hidden" id="status" name="status" value="${user.status}"/>
				<input type="hidden" id="reason" name="reason" value="${user.reason}"/>
				<input type="hidden" id="floorNo" name="floorNo" value="${user.floorNo}"/>
				<input type="hidden" id="unitNo" name="unitNo" value="${user.unitNo}"/>
				<input type="hidden" id="roomNo" name="roomNo" value="${user.roomNo}"/>
				<input type="hidden" id="orgId" name="orgId" value="${user.orgId}"/>
				<table cellpadding="0" cellspacing="0" style="table-layout:fixed;">
					<tr>
						<td>账号:</td>
						<td><input class="easyui-textbox" type="text" id="account" name="account" data-options="required:true,readonly:true" value="${user.account }"></input></td>
					</tr>
					<tr>
						<td>用户名:</td>
						<td><input class="easyui-textbox" type="text" id="name" name="name" data-options="required:true" value="${user.name }"></input></td>
					</tr>
					<tr>
						<td>邮箱:</td>
						<td><input class="easyui-textbox" type="text" id="email" name="email" data-options="required:true" value="${user.email }"></input></td>
					</tr>
					<tr>
						<td>手机号:</td>
						<td><input class="easyui-textbox" type="text" id="telNo" name="telNo" data-options="required:true" value="${user.telNo }"></input></td>
					</tr>
					<tr>
						<td>身份证号:</td>
						<td><input class="easyui-textbox" type="text" id="idNo" name="idNo" data-options="" value="${user.idNo }"></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="User.modifyInfo();" data-options="iconCls:'icon-save'">修改</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="User.cancelModifyInfo();" data-options="iconCls:'icon-cancel'">取消</a>
			</div>
		</div>

	</div>
</body>
</html>
