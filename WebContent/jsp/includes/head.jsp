<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter, java.io.IOException, javax.servlet.jsp.PageContext" %>

<%!
	String ERROR = "ERROR";
	String COOKIE_LOGIN = "AgileManufacturingGUILogin";
	String COOKIE_DOMAIN = "agilemanufacturing.com";
	String COOKIE_SESSION = "AgileManufacturingGUISession";
	String SERVER_ADDRESS = "http://127.0.0.1:8080/21_EersteJsp/eerste.jsp";
	int MODE_LOGIN = 0;
	int MODE_MAP = 1;
	int MODE_LIST = 2;
	int MODE_DETAIL = 3;
	int MODE_CONTROLS = 4;
	int MODE_GUI = 5;
	
	String _session;
	
	
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
				if (cookieFound.getDomain() != null && cookieFound.getDomain().equals(COOKIE_DOMAIN))
				{
					if (cookieFound.getName().equals(COOKIE_LOGIN))
					{
						_login = login(cookieFound);
					}
					else if (cookieFound.getName().equals(COOKIE_SESSION)) {
						_session = session(cookieFound);
					}
				}
			}
		}
		return output;
	}
	
	private boolean login(Cookie cookie) {
		//Connect to server and see if this login works
		
		return false;
	}
	
	private String session(Cookie cookie) {
		if (!cookie.getValue().isEmpty())
			return cookie.getValue();
		return "";
	}
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Agile Manufacturing Transport GUI</title>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="js/gui.js"></script>
	<link href="css/main.css" type="text/css" rel="stylesheet">
	<link href="css/gatewayGUI.css" type="text/css" rel="stylesheet">
	<link href="css/font-awesome.min.css" type="text/css" rel="stylesheet">
	<info cookie="<%= cookieRetrieval(request,response) %>">
</head>