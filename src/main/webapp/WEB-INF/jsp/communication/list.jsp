<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="caozj" uri="http://www.caozj.com"%>
<!DOCTYPE html>
<html>
<head>
<title>朋友圈</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/dateformat.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/communication.js"></script>
</head>
<body>
	<input type="hidden" id="userAccount" name="userAccount" value="${searchForm.userAccount}" />
	<div style="margin: 10px 0;"></div>
	<div id="panel">
		<table id="table"></table>
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="Communication.toAdd();" data-options="iconCls:'icon-add'">新增</a> <a href="javascript:void(0);" class="easyui-linkbutton"
				onclick="Communication.listMine();" data-options="iconCls:'icon-man'">我的</a>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#table").datagrid({
				url : contextPath + "/communication/listDataOfEasyUI.do?userAccount=${searchForm.userAccount}",
				pagination : true,
				height : $(document).height() - 50,
				width : $(document).width() - 10,
				method : "post",
				toolbar : "#toolbar",
				animate : true,
				singleSelect : false,
				idField : "id",
				loadMsg : "正在加载数据，请耐心等待...",
				rownumbers : true,
				columns : [ [ {
					field : 'title',
					title : '标题',
					width : 450
				}, {
					field : 'userName',
					title : '发布人',
					width : 100
				}, {
					field : 'time',
					title : '发布时间',
					width : 140,
					formatter : function(value, row, index) {
						var time = row["time"];
						var date = new Date(time);
						return date.format("yyyy-MM-dd HH:mm:ss");
					}
				}, {
					title : "操作",
					field : "oper",
					width : 120,
					formatter : function(value, row, index) {
						var html = "";
						var loginUser = "${sessUser.account}";
						if (loginUser == row["userAccount"]) {
							html += "<a href=\"javascript:void(0);\" onclick=\"Communication.toUpdate('" + row["id"] + "')\">修改</a>";
							html += "&nbsp;&nbsp;";
							html += "<a href=\"javascript:void(0);\" onclick=\"Communication.deleteRecord('" + row["id"] + "')\">删除</a>";
							html += "&nbsp;&nbsp;";
						}
						html += "<a href=\"javascript:void(0);\" onclick=\"Communication.show('" + row["id"] + "')\">查看</a>";
						return html;
					}
				} ] ]
			});
		});
	</script>
</body>
</html>
