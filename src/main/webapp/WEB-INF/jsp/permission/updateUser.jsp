<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>用户管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/user.js"></script>
</head>
<body style="padding:0px;margin:0px">
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="修改用户" style="width:500px">
		<div style="padding:10px 60px 20px 60px">
			<form id="userForm" method="post">
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
					<tr>
						<td>楼号:</td>
						<td><input class="easyui-textbox" type="text" id="floorNo" name="floorNo" data-options="" value="${user.floorNo }"></input></td>
					</tr>
					<tr>
						<td>单元号:</td>
						<td><input class="easyui-textbox" type="text" id="unitNo" name="unitNo" data-options="" value="${user.unitNo }"></input></td>
					</tr>
					<tr>
						<td>房号:</td>
						<td><input class="easyui-textbox" type="text" id="roomNo" name="roomNo" data-options="" value="${user.roomNo }"></input></td>
					</tr>
					<tr>
						<td>申请理由:</td>
						<td><input class="easyui-textbox" type="text" id="reason" name="reason" data-options="multiline:true" value="${user.reason }"></input></td>
					</tr>
					<tr>
						<td>状态:</td>
						<td>
							<select id="status" name="status">
								<c:forEach items="${statusList}" var="status">
									<option value="${status.id }" <c:if test="${user.status == status.id }">selected</c:if>>${status.name }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="User.update();" data-options="iconCls:'icon-save'">修改</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="User.toList();" data-options="iconCls:'icon-cancel'">取消</a>
			</div>
		</div>

	</div>
</body>
</html>

