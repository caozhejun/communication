<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="caozj" uri="http://www.caozj.com"%>
<!DOCTYPE html>
<html>
<head>
<title>广告管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/dateformat.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/advertisement.js"></script>
</head>
<body>
	<input type="hidden" id="avoid" name="avoid" value="${searchForm.avoid}"/>
	<input type="hidden" id="userAccount" name="userAccount" value="${searchForm.userAccount}"/>
	<div style="margin: 10px 0;"></div>
	<div id="panel">
		<table id="table"></table>
		<div id="toolbar">
			<caozj:permission url="/advertisement/add.do" session="<%=session%>"><a href="javascript:void(0);" class="easyui-linkbutton" onclick="Advertisement.toAdd();" data-options="iconCls:'icon-add'">新增</a> </caozj:permission>
			<caozj:permission url="/advertisement/batchDelete.do" session="<%=session%>"><a href="javascript:void(0);" class="easyui-linkbutton" onclick="Advertisement.batchDelete();" data-options="iconCls:'icon-remove'">删除</a> </caozj:permission>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#table").datagrid({
				url : contextPath + "/advertisement/listDataOfEasyUI.do?userAccount=${searchForm.userAccount}&orgId=${searchForm.orgId}&avoid=${searchForm.avoid}&status=${searchForm.status}",
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
					width : 150
				}, {
					field : 'userAccount',
					title : '发布者',
					width : 60
				},
				<c:if test="${searchForm.avoid != 1}">
				{
					field : 'time',
					title : '发布时间',
					width : 140,
					formatter : function(value, row, index) {
						var time = row["time"];
						var date = new Date(time);
						return date.format("yyyy-MM-dd HH:mm:ss");
					}
				},{
					field : 'startTime',
					title : '开始时间',
					width : 140,
					formatter : function(value, row, index) {
						var time = row["startTime"];
						var date = new Date(time);
						return date.format("yyyy-MM-dd HH:mm:ss");
					}
				},{
					field : 'endTime',
					title : '结束时间',
					width : 140,
					formatter : function(value, row, index) {
						var time = row["endTime"];
						var date = new Date(time);
						return date.format("yyyy-MM-dd HH:mm:ss");
					}
				}, {
					field : 'orderNo',
					title : '排序',
					width : 30
				},{
					field : 'status',
					title : '状态',
					width : 50,
					formatter : function(value, row, index) {
						var status = row["status"];
						var html = "";
						if(status == 1){
							html = "评审中";
						}else if(status == 2){
							html = "同意";
						}else if(status == 3){
							html = "拒绝";
						}
						return html;
					}
				},
				</c:if>
				{
					title : "操作",
					field : "oper",
					width : 220,
					formatter : function(value, row, index) {
						var html = "";
						if(row["status"] == 1){
						<caozj:permission url="/advertisement/update.do" session="<%=session%>">
						html += "<a href=\"javascript:void(0);\" onclick=\"Advertisement.toUpdate('" + row["id"] + "')\">修改</a>";
						html += "&nbsp;&nbsp;";
						</caozj:permission>
						}
						<caozj:permission url="/advertisement/delete.do" session="<%=session%>"> 
						html += "<a href=\"javascript:void(0);\" onclick=\"Advertisement.deleteRecord('" + row["id"] + "')\">删除</a>";
						html += "&nbsp;&nbsp;";
						</caozj:permission>
						<caozj:permission url="/advertisement/approval.do" session="<%=session%>"> 
						html += "<a href=\"javascript:void(0);\" onclick=\"Advertisement.toApproval('" + row["id"] + "')\">审批</a>";
						html += "&nbsp;&nbsp;";
						</caozj:permission>
						html += "<a href=\"javascript:void(0);\" onclick=\"Advertisement.show('" + row["id"] + "')\">查看</a>";
						return html;
					}
				} ] ]
			});
		});
	</script>
</body>
</html>
