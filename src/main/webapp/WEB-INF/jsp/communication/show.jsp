<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="caozj" uri="http://www.caozj.com"%>
<!DOCTYPE html>
<html>
<head>
<title>朋友圈</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/easyui.jsp"%>
<%@ include file="../common/ueditor.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/communication.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/comment.js"></script>
</head>
<body style="padding: 0px; margin: 0px">
	<div style="margin: 10px 0;"></div>
	<input type="hidden" id="id" name="id" value="${communication.id}" />
	<input type="hidden" id="userAccount" name="userAccount" value="${searchForm.userAccount}" />
	<input type="hidden" id="type" name="type" value="${type}" />
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="Communication.toList();" data-options="iconCls:'icon-back'">返回</a>
	<br /> ${communication.content}
	<br /> 评论数(	<span id="commentCount">${commentCount}</span> )
	<br /><br />
	<div id="commentList">
		<c:forEach items="${commentList }" var="comment" varStatus="step">
		${step.count }楼(${comment.name }):<br />
		${comment.content }<br />
		</c:forEach>
	</div>
	<br /><br />
	<textarea rows="5" cols="140" id="commentContent" name="commentContent"></textarea>
	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="Comment.add();" data-options="iconCls:'icon-add'">评论</a>
</body>
</html>
