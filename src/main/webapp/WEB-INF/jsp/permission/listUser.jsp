<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>用户管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/ext.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/user.js"></script>
</head>
<body>
	<div id="list"></div>
	<script type="text/javascript">
		Ext.onReady(function() {
			var url = contextPath + "/user/listDataOfExt.do";
			var pageSize = 10;
			var fields = [ {
				name : "account",
				type : "string"
			}, {
				name : "name",
				type : "string"
			}, {
				name : "status",
				type : "int"
			}, {
				name : "email",
				type : "string"
			}, {
				name : "telNo",
				type : "string"
			}, {
				name : "floorNo",
				type : "string"
			}, {
				name : "unitNo",
				type : "string"
			}, {
				name : "roomNo",
				type : "string"
			}, {
				name : "idNo",
				type : "string"
			}, {
				name : "reason",
				type : "string"
			} ];
			var cmModels = [ {
				header : '账号',
				dataIndex : 'account',
				width : 50,
				sortable : true
			}, {
				header : '用户名',
				dataIndex : 'name',
				width : 50,
				sortable : true
			}, {
				header : '邮箱',
				dataIndex : 'email',
				width : 100,
				sortable : true
			}, {
				header : '手机号',
				dataIndex : 'telNo',
				width : 100,
				sortable : true
			}, {
				header : '楼号',
				dataIndex : 'floorNo',
				width : 50,
				sortable : true
			}, {
				header : '单元号',
				dataIndex : 'unitNo',
				width : 50,
				sortable : true
			}, {
				header : '房号',
				dataIndex : 'roomNo',
				width : 50,
				sortable : true
			}, {
				header : '身份证号',
				dataIndex : 'idNo',
				width : 70,
				sortable : true
			}, {
				header : '理由',
				dataIndex : 'reason',
				width : 100,
				sortable : true
			}, {
				header : "状态",
				width:50,
				renderer : renderStatus
			}, {
				header : "操作",
				width:300,
				renderer : renderOper
			} ];
			var type = "checkbox";
			var id = "userList";
			var field = "account";
			var tbar = [ {
				xtype : 'button',
				text : '新增',
				handler : function() {
					User.toAdd();
				}
			}, '-', {
				xtype : 'button',
				text : '删除',
				handler : function() {
					User.batchDelete();
				}
			}, '-', {
				xtype : 'button',
				text : '批量审批通过',
				handler : function() {
					User.batchAproved();
				}
			}, '-', {
				xtype : 'button',
				text : '批量审批拒绝',
				handler : function() {
					User.batchRefuse();
				}
			}, '->', {
				xtype : 'button',
				text : '查询',
				handler : function() {

				}
			} ];
			ExtGridUtil.loadPageGrid(url, pageSize, fields, cmModels, '用户信息', "list", tbar, 0, id, type, field);
		});

		function renderStatus(value, cellmeta, record, rowIndex, columnIndex, store) {
			var html = "";
			if (record.data["status"] == 1) {
				html = "评审中";
			} else if (record.data["status"] == 2) {
				html = "同意";
			} else if (record.data["status"] == 3) {
				html = "拒绝";
			}
			return html;
		}

		function renderOper(value, cellmeta, record, rowIndex, columnIndex, store) {
			var html = "<a href=\"javascript:void(0)\" onclick=\"User.toUpdate('" + record.data["account"] + "')\">修改</a>";
			html += "&nbsp;&nbsp;";
			html += "<a href=\"javascript:void(0)\" onclick=\"User.deleteAccount('" + record.data["account"] + "')\">删除</a>";
			html += "&nbsp;&nbsp;";
			html += "<a href=\"javascript:void(0)\" onclick=\"User.resetPwd('" + record.data["account"] + "')\">重置密码</a>";
			html += "&nbsp;&nbsp;";
			html += "<a href=\"javascript:void(0)\" onclick=\"User.approved('" + record.data["account"] + "')\">审批通过</a>";
			html += "&nbsp;&nbsp;";
			html += "<a href=\"javascript:void(0)\" onclick=\"User.refuse('" + record.data["account"] + "')\">审批拒绝</a>";
			html += "&nbsp;&nbsp;";
			html += "<a href=\"javascript:void(0)\" onclick=\"User.toAssignRole('" + record.data["account"] + "')\">分配角色</a>";
			return html;
		}
	</script>
</body>
</html>
