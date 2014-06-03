<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter, java.io.IOException" %>

<%!
	String ERROR = "ERROR";
	String COOKIE_LOGIN = "AgileManufacturingGUILogin";
	int MODE_LOGIN = 0;
	int MODE_MAP = 1;
	int MODE_LIST = 2;
	int MODE_DETAIL = 3;
	int MODE_CONTROLS = 4;
	int MODE_GUI = 5;
	
	
	int currentMode = 0; 
	Cookie[] cookies;
	
	public String cookieRetrieval(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String output = "";
		cookies = request.getCookies();
		if(cookies == null)
		{
			output = ERROR;
		}
		else
		{
			for (Cookie cookieFound : cookies)
			{
				if (cookieFound.getName().equals(COOKIE_LOGIN))
				{
					output = cookieFound.getValue();
				}
			}
		}
		return output;
	}
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Agile Manufacturing Transport GUI</title>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<link href="css/main.css" type="text/css" rel="stylesheet">
	<link href="css/gatewayGUI.css" type="text/css" rel="stylesheet">
	<info cookie="<%= cookieRetrieval(request,response) %>">
</head>