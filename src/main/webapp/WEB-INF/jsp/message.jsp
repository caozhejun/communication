<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%
	String message = request.getAttribute("message").toString();
	String callback = request.getParameter("callback");
	String output = message;
	if (callback != null && !"".equals(callback)) {
		output = callback + "(" + message + ")";
	}
	out.print(output);
%>