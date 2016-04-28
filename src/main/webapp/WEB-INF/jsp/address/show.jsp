<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>地址</title>
<%@ include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/address/address.js"></script>
</head>
<body>
	<div>
		<h1>地址联动</h1>
		省:<select id="province"></select>
		市:<select id="city"></select>
		区:<select id="country"></select>
	</div>

	<script type="text/javascript">
		$(document).ready(function(){
			Address.init("province","city","country","","","","","");
		});
	</script>
</body>
</html>