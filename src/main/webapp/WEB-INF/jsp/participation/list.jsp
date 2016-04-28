<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="caozj" uri="http://www.caozj.com"%>
<!DOCTYPE html>
<html>
<head>
<title>报名详情</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/dateformat.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/active.js"></script>
</head>
<body>
	<div style="margin: 10px 0;"></div>
	<div id="panel">
		<input type="hidden" id="activeId" name="activeId" value="${activeId }" />
		<table id="table"></table>
		<div id="toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="Active.show(${activeId});" data-options="iconCls:'icon-back'">返回</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="Active.exportParticipation();" data-options="iconCls:'icon-print'">导出Excel</a>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#table").datagrid({
				url : contextPath + "/participation/listDataOfEasyUI.do?activeId=${activeId}",
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
					field : 'userAccount',
					title : '账号',
					width : 100
				}, {
					field : 'name',
					title : '名称',
					width : 100
				},{
					field : 'email',
					title : '电子 邮箱',
					width : 200
				},{
					field : 'telNo',
					title : '电话号码',
					width : 200
				},{
					field : 'floorNo',
					title : '楼号',
					width : 80
				},{
					field : 'unitNo',
					title : '单元号',
					width : 80
				},{
					field : 'roomNo',
					title : '房号',
					width : 80
				},{
					field : 'idNo',
					title : '身份证号',
					width : 450
				},{
					field : 'num',
					title : '人数',
					width : 50
				}, {
					title : "备注",
					field : "remark",
					width : 720
				} ] ]
			});
		});
	</script>
</body>
</html>
