<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>权限管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/ext.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/permission.js"></script>
</head>
<body>
	<div id="list"></div>
	<script type="text/javascript">
		Ext.onReady(function() {
			var url = contextPath + "/permission/listDataOfExt.do";
			var pageSize = 10;
			var fields = [ {
				name : "name",
				type : "string"
			}, {
				name : "description",
				type : "string"
			}, {
				name : "url",
				type : "string"
			} ];
			var cmModels = [ {
				header : '名称',
				dataIndex : 'name',
				width : 100,
				sortable : true
			}, {
				header : '描述',
				dataIndex : 'description',
				width : 130,
				sortable : true
			}, {
				header : 'URL地址',
				dataIndex : 'url',
				width : 400,
				sortable : true
			}, {
				header : "操作",
				renderer : renderOper
			} ];
			var type = "checkbox";
			var id = "permissionList";
			var field = "name";
			var tbar = [ {
				xtype : 'button',
				text : '新增',
				handler : function() {
					Permission.toAdd();
				}
			}, '-', {
				xtype : 'button',
				text : '删除',
				handler : function() {
					Permission.batchDelete();
				}
			} ];
			ExtGridUtil.loadPageGrid(url, pageSize, fields, cmModels, '权限信息', "list", tbar, 0, id, type, field);
		});

		function renderOper(value, cellmeta, record, rowIndex, columnIndex, store) {
			var html = "<a href=\"javascript:void(0)\" onclick=\"Permission.toUpdate('" + record.data["name"] + "')\">修改</a>";
			html += "&nbsp;&nbsp;";
			html += "<a href=\"javascript:void(0)\" onclick=\"Permission.deletePermission('" + record.data["name"] + "')\">删除</a>";
			return html;
		}
	</script>
</body>
</html>
