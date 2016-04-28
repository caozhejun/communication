<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="caozj" uri="http://www.caozj.com"%>
<!DOCTYPE html>
<html>
<head>
<title>公告管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/dateformat.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/notice.js"></script>
</head>
<body>
	<input type="hidden" id="type" name="type" value="${searchForm.type}" />
	<div style="margin: 10px 0;"></div>
	<div id="panel">
		<table id="table"></table>
		<div id="toolbar">
			<caozj:permission url="/notice/add.do" session="<%=session%>"><a href="javascript:void(0);" class="easyui-linkbutton" onclick="Notice.toAdd();" data-options="iconCls:'icon-add'">新增</a> </caozj:permission>
			<caozj:permission url="/notice/batchDelete.do" session="<%=session%>"><a href="javascript:void(0);" class="easyui-linkbutton" onclick="Notice.batchDelete();" data-options="iconCls:'icon-remove'">删除</a> </caozj:permission>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#table").datagrid({
				url : contextPath + "/notice/listDataOfEasyUI.do?type=" +$("#type").val(),
				pagination : true,
				height : $(document).height() - 50,
				width : $(document).width()-10,
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
					field : 'title',
					title : '标题',
					width : 450
				}, {
					field : 'time',
					title : '发布时间',
					width : 200,
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
						<caozj:permission url="/notice/update.do" session="<%=session%>">
						html += "<a href=\"javascript:void(0);\" onclick=\"Notice.toUpdate('" + row["id"] + "')\">修改</a>";
						html += "&nbsp;&nbsp;";
						</caozj:permission>
						<caozj:permission url="/notice/delete.do" session="<%=session%>"> 
						html += "<a href=\"javascript:void(0);\" onclick=\"Notice.deleteRecord('" + row["id"] + "')\">删除</a>";
						html += "&nbsp;&nbsp;";
						</caozj:permission>
						html += "<a href=\"javascript:void(0);\" onclick=\"Notice.show('" + row["id"] + "')\">查看</a>";
						return html;
					}
				} ] ]
			});
		});
	</script>
</body>
</html>
