<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>菜单管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/menu.js"></script>
</head>
<body class="easyui-layout" id="layout">
	<input type="hidden" id="parentID" name="parentID" />
	<div data-options="region:'west',split:true" title="菜单树" style="width: 180px;">
		<ul class="easyui-tree" id="menuTree"></ul>
	</div>
	<div data-options="region:'center',title:'菜单树--子菜单'">
		<table id="menuTable"></table>
		<div id="menuToolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="Menu.toAdd();" data-options="iconCls:'icon-add'">新增</a> <a href="javascript:void(0);" class="easyui-linkbutton"
				onclick="Menu.batchDelete();" data-options="iconCls:'icon-remove'">删除</a>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#menuTree").tree({
				url : contextPath + "/menu/listChildrenMenu.do?dealMenu=false&showRoot=true",
				method : "post",
				animate : true,
				onClick : function(node) {
					Menu.selectMenu(node);
				}
			});
			$("#menuTable").datagrid({
				url : contextPath + "/menu/listChildrenMenu.do?dealMenu=false",
				method : "post",
				animate : true,
				toolbar : "#menuToolbar",
				singleSelect : false,
				idField : "id",
				loadMsg : "正在加载数据，请耐心等待...",
				rownumbers : true,
				queryParams : {
					id : $("#parentID").val()
				},
				columns : [ [ {
					title : "全选",
					field : "ck",
					checkbox : true
				}, {
					field : 'text',
					title : '名称',
					width : 150
				}, {
					field : 'url',
					title : 'URL地址',
					width : 400
				}, {
					field : 'orderNo',
					title : '排序号',
					width : 100
				}, {
					title : "操作",
					field : "oper",
					width : 120,
					formatter : function(value, row, index) {
						var html = "<a href=\"javascript:void(0);\" onclick=\"Menu.toUpdate('" + row["id"] + "')\">修改</a>";
						html += "&nbsp;&nbsp;";
						html += "<a href=\"javascript:void(0);\" onclick=\"Menu.deleteMenu('" + row["id"] + "')\">删除</a>";
						return html;
					}
				} ] ]
			});
		});
	</script>
</body>
</html>
