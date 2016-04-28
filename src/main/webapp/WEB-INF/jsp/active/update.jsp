<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>活动管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<%@ include file="../common/ueditor.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/active.js"></script>
</head>
<body style="padding: 0px; margin: 0px">
	<div style="margin: 20px 0;"></div>
	<div style="padding: 10px 60px 20px 60px">
		<form id="form" method="post">
			<input type="hidden" id="id" name="id" value="${active.id}" /> <input type="hidden" id="orgId" name="orgId" value="${active.orgId}" /> 
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="2"><a href="javascript:void(0)" class="easyui-linkbutton" onclick="Active.update();" data-options="iconCls:'icon-save'">修改</a> <a href="javascript:void(0)"
						class="easyui-linkbutton" onclick="Active.toList();" data-options="iconCls:'icon-cancel'">取消</a></td>
				</tr>
				<tr>
					<td>标题:</td>
					<td><input class="easyui-textbox" type="text" id="title" name="title" data-options="required:true" value="${active.title}"></input></td>
				</tr>
				<tr>
					<td>活动截止时间:</td>
					<td><input class="easyui-datetimebox" type="text" id="endTime" name="endTime" data-options="required:true" value="<fmt:formatDate value="${active.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"></input></td>
				</tr>
				<tr>
					<td>内容:</td>
					<td><script id="content" name="content" type="text/plain" style="width: 1024px; height: 500px;">${active.content}</script></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			UE.getEditor('content');
		});
	</script>
</body>
</html>
