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
<body style="padding:0px;margin:0px">
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="修改菜单" style="width:700px">
		<div style="padding:10px 60px 20px 60px">
			<form id="menuForm" method="post">
				<input type="hidden" id="id" name="id" value="${menu.id }" />
				<table cellpadding="0" cellspacing="0" style="table-layout:fixed;">
					<tr>
						<td>名称:</td>
						<td><input class="easyui-textbox" type="text" id="text" name="text" data-options="required:true" style="width:200px" value="${menu.text }"></input></td>
					</tr>
					<tr>
						<td>父菜单:</td>
						<td><select id="parentID" name="parentID" style="width:200px;"></select></td>
					</tr>
					<tr>
						<td>URL地址:</td>
						<td><input class="easyui-textbox" type="text" id="url" name="url"  style="width:200px" value="${menu.url }"></input></td>
					</tr>
					<tr>
						<td>排序:</td>
						<td><input class="easyui-numberspinner" required="required" type="text" id="orderNo" name="orderNo" data-options="min:1,max:999,editable:true" style="width:200px"
							value="${menu.orderNo }"></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="Menu.update();" data-options="iconCls:'icon-add'">修改</a> <a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="Menu.toList();" data-options="iconCls:'icon-cancel'">取消</a>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			var defaultParentID = ${menu.parentID};
			$("#parentID").combotree({
				url : contextPath + "/menu/listChildrenMenu.do?dealMenu=false&showRoot=true",
				required : true,
				onLoadSuccess : function(node, data) {
					if (defaultParentID == 0) {
						return true;
					}
					var menuTreeSelect = $("#parentID").combotree("tree");
					var parentNode = menuTreeSelect.tree("find", defaultParentID);
					if (!parentNode) {
						var root = menuTreeSelect.tree("find", ${rootID});
						menuTreeSelect.tree("expandAll", root.target);
						parentNode = menuTreeSelect.tree("find", defaultParentID);
					}
					if (!parentNode) {
						return true;
					}
					menuTreeSelect.tree("scrollTo", parentNode.target);
					$("#parentID").combotree("setValue", defaultParentID);
					defaultParentID = 0;
				}
			});
		});
	</script>
</body>
</html>
