<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>File Upload</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	${message }
	<form action="<%=request.getContextPath()%>/esbuser/fileUpload.do"  method="post" enctype="multipart/form-data">
		<input type="file" id="file" name="file" /> <input type="submit" name="submit" id="submit" value="上传文件" />
	</form>
</body>
</html>