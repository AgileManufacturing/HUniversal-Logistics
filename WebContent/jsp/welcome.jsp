<%@ page language="java" contentType="text/html"%>
<%@ page import="java.io.PrintWriter, java.io.IOException" %>
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
%>

<html>
	<jsp:include page="includes/head.jsp" />
	<body>
		<jsp:include page="includes/header.jsp" />
		<jsp:include page="includes/navigation.jsp" />
		<jsp:include page="includes/content.jsp" />
		<jsp:include page="includes/footer.jsp" />
	</body>
</html>