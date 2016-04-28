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
<body>
	<div style="margin: 10px 0;"></div>
	<div id="panel">
		<table id="table"></table>
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="Org.toAdd();" data-options="iconCls:'icon-add'">新增</a> <a href="javascript:void(0);" class="easyui-linkbutton"
				onclick="Org.batchDelete();" data-options="iconCls:'icon-remove'">删除</a>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#table").datagrid({
				url : contextPath + "/org/listDataOfEasyUI.do",
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
					title : "全选",
					field : "ck",
					checkbox : true
				}, {
					field : 'name',
					title : '名称',
					width : 250
				}, {
					field : 'address',
					title : '地址',
					width : 650
				}, {
					title : "操作",
					field : "oper",
					width : 120,
					formatter : function(value, row, index) {
						var html = "<a href=\"javascript:void(0);\" onclick=\"Org.toUpdate('" + row["id"] + "')\">修改</a>";
						html += "&nbsp;&nbsp;";
						html += "<a href=\"javascript:void(0);\" onclick=\"Org.deleteRecord('" + row["id"] + "')\">删除</a>";
						return html;
					}
				} ] ]
			});
		});
	</script>
</body>
</html>
