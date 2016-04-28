<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>公告管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<%@ include file="../common/ueditor.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/notice.js"></script>
</head>
<body style="padding: 0px; margin: 0px">
	<div style="margin: 10px 0;"></div>
	<div style="padding: 10px 10px 20px 60px">
		<form id="form" method="post">
			<input type="hidden" id="type" name="type" value="${searchForm.type}" />
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="2"><a href="javascript:void(0)" class="easyui-linkbutton" onclick="Notice.add();" data-options="iconCls:'icon-add'">新增</a> <a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="Notice.toList();" data-options="iconCls:'icon-cancel'">取消</a></td>
				</tr>
				<tr>
					<td>标题:</td>
					<td><input class="easyui-textbox" type="text" id="title" name="title" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>内容:</td>
					<td><script id="content" name="content" type="text/plain" style="width: 1024px; height: 500px;"></script></td>
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
