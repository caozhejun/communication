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
<body>
	<div style="margin:20px 0;"></div>
	<div id="panel">
		<table id="table"></table>
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="InstallPackage.toAdd();" data-options="iconCls:'icon-add'">新增</a> <a href="javascript:void(0);"
				class="easyui-linkbutton" onclick="InstallPackage.batchDelete();" data-options="iconCls:'icon-remove'">删除</a>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#panel").panel({
				height : $(document).height() - 50,
				width : $(document).width() - 20,
				title : "版本信息"
			});
			$("#table").datagrid({
				url : contextPath + "/installPackage/listData.do",
				method : "post",
				animate : true,
				toolbar : "#toolbar",
				singleSelect : false,
				idField : "installVersion",
				loadMsg : "正在加载数据，请耐心等待...",
				rownumbers : true,
				columns : [ [ {
					title : "全选",
					field : "ck",
					checkbox : true
				}, {
					field : 'installVersion',
					title : '版本号',
					width : 150
				}, {
					field : 'fileName',
					title : '文件名',
					width : 150
				}, {
					field : 'publishDate',
					title : '发布时间',
					width : 150
				}, {
					field : 'newest',
					title : '是否最新',
					width : 100,
					formatter : function(value, row, index) {
						var html = "";
						if (value == 0) {
							html = "否";
						} else {
							html = "是";
						}
						return html;
					}
				}, {
					field : 'detail',
					title : '说明',
					width : 400
				}, {
					title : "操作",
					field : "oper",
					width : 150,
					formatter : function(value, row, index) {
						var html = "<a href=\"javascript:void(0);\" onclick=\"InstallPackage.toUpdate('" + row["installVersion"] + "')\">修改</a>";
						html += "&nbsp;&nbsp;";
						html += "<a href=\"javascript:void(0);\" onclick=\"InstallPackage.deleteRecord('" + row["installVersion"] + "')\">删除</a>";
						html += "&nbsp;&nbsp;";
						html += "<a href=\"javascript:void(0);\" onclick=\"InstallPackage.updateNewest('" + row["installVersion"] + "')\">设置最新版本</a>";
						return html;
					}
				} ] ]
			});
		});
	</script>
</body>
</html>
