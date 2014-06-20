<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter, java.io.IOException" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%!
	public void jspInit() {
		super.jspInit();
		log("HUniversal-Transport-GUI Welcome Page");
	}
	
	public void jspDestroy()
	{
		super.jspDestroy();
	}
	
	boolean _login = false;
%>

<html>
	<%@include file="includes/head.jsp" %>
	<body>
		<jsp:include page="includes/header.jsp" />
		<core:set var="_login" value="true" />
		<core:choose>
			<core:when test="<%= _login %>">
				<jsp:include page="includes/navigation.jsp" />
				<jsp:include page="includes/content.jsp" />
			</core:when>
			<core:otherwise>
				<jsp:include page="includes/login.jsp" />
			</core:otherwise>
		</core:choose>
		<jsp:include page="includes/footer.jsp" />
	</body>
</html>