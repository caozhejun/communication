<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="caozj" uri="http://www.caozj.com"%>
<!DOCTYPE html>
<html>
<head>
<title>活动管理</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<%@ include file="../common/ueditor.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/active.js"></script>
</head>
<body style="padding: 0px; margin: 0px">
	<div style="margin: 10px 0;"></div>
	<input type="hidden" id="id" name="id" value="${active.id}" />
	<input type="hidden" id="participationId" name="participationId" value="${participation.id}" />
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="Active.toList();" data-options="iconCls:'icon-back'">返回</a> 
	<c:if test="${!depart }">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="Active.showDepart();" data-options="iconCls:'icon-save'">报名</a> 
	</c:if>
	<c:if test="${depart }">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="Active.cancel();" data-options="iconCls:'icon-cancel'">取消报名</a> 
	</c:if>
	
	<caozj:permission url="/participation/list.do" session="<%=session%>">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="Active.showParticipation();" data-options="iconCls:'icon-ok'">查看报名情况</a> 
	</caozj:permission>
	
	<div id="departDiv" class="easyui-window" title="报名" style="width:540px;height:170px;" closed="true">  
	    <div style="padding: 10px 60px 20px 60px">
		<form id="departForm" method="post">
			<table cellpadding="0" cellspacing="0" style="table-layout: fixed;">
				<tr>
					<td>报名人数:</td>
					<td><input class="easyui-textbox" type="text" id="num" name="num" data-options="required:true" value="1"></input>(请输入数字)</td>
				</tr>
				<tr>
					<td>备注:</td>
					<td><input class="easyui-textbox" type="text" id="remark" name="remark" data-options="required:false"></input></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="Active.depart();" data-options="iconCls:'icon-save'">确认报名</a> <a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="Active.hideDepart();" data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</div>
	</div>
	
	<br/>
	${active.content}
</body>
</html>
