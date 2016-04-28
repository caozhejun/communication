<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>小区管理系统</title>
<%@ include file="./common/header.jsp"%>
<%@ include file="./common/easyui.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/user.js"></script>
<style type="text/css">
/*公共样式*/
* {
	padding: 0;
	margin: 0;
	border: none
}

body {
	height: 100%;
	width: 100%;
	background: #014880;
	overflow: hidden;
}
/*标题部分的样式*/
.titleWraper {
	width: 100% px;
	height: 223px;
}

.title {
	width: 980px;
	height: 223px;
	margin: 0 auto;
	background: url(<%=request.getContextPath()%>/images/title.jpg) no-repeat left center;
}
/*中间区域的样式*/
.contentWraper {
	width: 100% px;
	height: 246px;
}

.content {
	width: 980px;
	height: 246px;
	margin: 0 auto;
}

.picBg {
	width: 602px;
	height: 246px;
	float: left;
	background: url(<%=request.getContextPath()%>/images/boxPic.jpg) no-repeat
}

.inputBox {
	width: 334px;
	height: 246px;
	float: left
}

.account {
	width: 334px;
	height: 54px;
	padding-top: 69px;
}

.password {
	width: 334px;
	height: 78px;
	padding-top: 15px;
}

#tip {
	width: 334px;
	height: 30px;
}

.inputBox input {
	width: 232px;
	height: 36px;
	line-height: 36px;
	color: #777;
	font-size: 20px;
}

.inputBox span {
	color: #FFF;
	font-size: 22px;
	font-family: SimHei, Microsoft YaHei;
	font-weight: bold;
	padding-right: 15px;
}

#loginBtnBg {
	width: 44px;
	height: 44px;
	margin-top: 101px;
	margin-bottom: 101px;
	float: left;
	cursor: pointer;
	background: url(<%=request.getContextPath()%>/images/loginBtn.jpg) no-repeat;
}

.footWraper {
	width: 100%;
	height: 350px;
	background: url(<%=request.getContextPath()%>/images/cube.jpg) repeat-x bottom;
	overflow: hidden
}
</style>
<script type="text/javascript">
	function mouseOver() {
		document.getElementById("loginBtnBg").style.background = "url(<%=request.getContextPath()%>/images/loginBtnOver.jpg) no-repeat";
	}
	function mouseOut() {
		document.getElementById("loginBtnBg").style.background = "url(<%=request.getContextPath()%>/images/loginBtn.jpg) no-repeat";
	}
	function login() {
		$("#loginForm").submit();
	}

	function showRegistry() {
		$('#registryDiv').window("open");
	}
	
	function showForget(){
		
	}

	<c:if test="${not empty top}">
		top.location.href = contextPath + "/login/toLogin.do";
	</c:if>

	$(document).ready(function() {
		
	});
</script>
</head>
<body>
	<div class="titleWraper">
		<div class="title"></div>
	</div>
	<div class="contentWraper">
		<div class="content">
			<div class="picBg"></div>
			<div class="inputBox">
				<form action="<%=request.getContextPath()%>/login/login.do" method="post" id="loginForm">
					<div class="account">
						<span>账 号</span><input type="text" id="userName" name="userName" value="${userName }" />
					</div>
					<div class="password">
						<span>密 码</span><input type="password" value="${password }" id="password" name="password" />
					</div>
					<div class="password">
						<a href="#" onclick="showRegistry();"><span>注册新用户</span></a>
					</div>
					<!-- 
					<div class="password">
						<a href="#" onclick="showForget();"><span>忘记密码</span></a>
					</div>
					 -->
				</form>
				<div id="tip" style="color: red">${errMsg}</div>
			</div>
			<div id="loginBtnBg" onclick="login()" onmouseover="mouseOver()" onmouseout="mouseOut()"></div>
		</div>
	</div>
	<div class="footWraper"></div>
	<div id="registryDiv" class="easyui-window" title="注册新用户" style="width:540px;height:410px;" closed="true">  
	    <div style="padding: 10px 60px 20px 60px">
		<form id="registryForm" method="post">
			<table cellpadding="0" cellspacing="0" style="table-layout: fixed;">
				<tr>
					<td>账号:</td>
					<td><input class="easyui-textbox" type="text" id="account" name="account" data-options="required:true"></input>(请输入英文)</td>
				</tr>
				<tr>
					<td>姓名:</td>
					<td><input class="easyui-textbox" type="text" id="name" name="name" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input class="easyui-textbox" type="password" id="pwd" name="pwd" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>确认密码:</td>
					<td><input class="easyui-textbox" type="password" id="confirmPwd" name="confirmPwd" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>小区:</td>
					<td>
						<select id="orgId" name="orgId">
							<c:forEach items="${allOrgs}" var="org">
								<option value="${org.id}">${org.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>邮箱:</td>
					<td><input class="easyui-textbox" type="text" id="email" name="email" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>手机号:</td>
					<td><input class="easyui-textbox" type="text" id="telNo" name="telNo" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>身份证号:</td>
					<td><input class="easyui-textbox" type="text" id="idNo" name="idNo" data-options=""></input></td>
				</tr>
				<tr>
					<td>楼号:</td>
					<td><input class="easyui-textbox" type="text" id="floorNo" name="floorNo" data-options=""></input></td>
				</tr>
				<tr>
					<td>单元号:</td>
					<td><input class="easyui-textbox" type="text" id="unitNo" name="unitNo" data-options=""></input></td>
				</tr>
				<tr>
					<td>房号:</td>
					<td><input class="easyui-textbox" type="text" id="roomNo" name="roomNo" data-options=""></input></td>
				</tr>
				<tr>
					<td>申请理由:</td>
					<td><input class="easyui-textbox" type="text" id="reason" name="reason" data-options="multiline:true"></input></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="User.registry();" data-options="iconCls:'icon-save'">注册</a> <a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="User.cancelRegistry();" data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</div>
	</div>
</body>
</html>