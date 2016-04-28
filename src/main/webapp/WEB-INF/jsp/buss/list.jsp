<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>业务管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/ext.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/buss.js"></script>
</head>
<body>
	<div id="list"></div>
	<script type="text/javascript">
		Ext.onReady(function() {
			var url = contextPath + "/buss/listDataOfExt.do";
			var pageSize = 10;
			var fields = [ {
				name : "name",
				type : "string"
			}, {
				name : "description",
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
				header : "操作",
				renderer : renderOper
			} ];
			var type = "checkbox";
			var id = "bussList";
			var field = "name";
			var tbar = [ {
				xtype : 'button',
				text : '新增',
				handler : function() {
					Buss.toAdd();
				}
			}, '-', {
				xtype : 'button',
				text : '删除',
				handler : function() {
					Buss.batchDelete();
				}
			} ];
			ExtGridUtil.loadPageGrid(url, pageSize, fields, cmModels, '业务信息', "list", tbar, 0, id, type, field);
		});

		function renderOper(value, cellmeta, record, rowIndex, columnIndex, store) {
			var html = "<a href=\"javascript:void(0)\" onclick=\"Buss.toUpdate('" + record.data["name"] + "')\">修改</a>";
			html += "&nbsp;&nbsp;";
			html += "<a href=\"javascript:void(0)\" onclick=\"Buss.deleteBuss('" + record.data["name"] + "')\">删除</a>";
			html += "&nbsp;&nbsp;";
			html += "<a href=\"javascript:void(0)\" onclick=\"Buss.showBuss('" + record.data["name"] + "')\">查看脚本</a>";
			html += "&nbsp;&nbsp;";
			html += "<a href=\"javascript:void(0)\" onclick=\"Buss.testBuss('" + record.data["name"] + "')\">测试脚本</a>";
			return html;
		}
	</script>
</body>
</html>
