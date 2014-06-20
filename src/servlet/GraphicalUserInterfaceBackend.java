package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import communication.Host;

/**
 * Servlet implementation class GraphicalUserInterfaceBackend
 */
@WebServlet("/GUIBackend")
public class GraphicalUserInterfaceBackend extends HttpServlet {	
	private List<Host> _gateways;
	private Map<String,String> _userSessions; //SessionID,UserID
	private Map<String,String> _userCookie; //CookieID,UserID
	
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GraphicalUserInterfaceBackend() {
	    super();
	    //Find the Gateway
	    broadcastGateways();
	    //Connect to the gateway
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//If we get a GUI request
		
		////If we get details of a specific ID
		
		//If we get a Session request
		
		
//		PrintWriter out = response.getWriter();
//		
//		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
//					"Transitional//EN\">\n" +
//					"<HTML><HEAD><TITLE></TITLE></HEAD><BODY>" +
//					"</BODY></HTML>" );
//		out.close();
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//If the post contains gateway data store this in the gateway arraylist
		
		//If the gateway updates the griddata, update the griddata
		
		//If a GUI client wants to login
		
	}
	
	protected void broadcastGateways() {
		//Broadcast
		
		//Create a list of all responses
	}
}