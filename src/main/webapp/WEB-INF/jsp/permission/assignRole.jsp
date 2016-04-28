<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>用户管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/table.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/user.js"></script>
</head>
<body style="padding:0px;margin:0px">
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="分配角色" style="width:500px">
		<div style="padding:10px 60px 20px 60px">
			<form id="roleForm" method="post">
				<input type="hidden" id="account" name="account" value="${account}" />
				<table cellpadding="0" cellspacing="0" style="table-layout:fixed;">
					<thead>
						<tr>
							<th><input type="checkbox" name="checkAll" id="checkAll" /><label for="checkAll">全选</label></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="role" items="${allRoleList }">
							<tr>
								<td><input type="checkbox" name="roleName" value="${role.name }" id="${role.name }" /></td>
								<td>${role.name }(${role.description })</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="User.assignRole();" data-options="iconCls:'icon-save'">分配</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="User.toList();" data-options="iconCls:'icon-cancel'">取消</a>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			<c:forEach var="role" items="${assignRoleList }">
			$("#${role.name}").attr("checked", "checked");
			</c:forEach>
		});
	</script>
</body>
</html>
