package servlet;

import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;

public class WebsocketInitializer implements ServletContextListener {
	private static Logger logger = Logger.getLogger(WebsocketInitializer.class.getName());

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		// Get a reference to the ServerContainer
		ServerContainer serverContainer = (ServerContainer) servletContext.getAttribute("javax.websocket.server.ServerContainer");
		// Add endpoint manually to server container
		try {
			serverContainer.addEndpoint(websocket.GatewayServerEndpoint.class);
			logger.info("websocket.GatewayEndpoint.class endpoint added");
		} catch (DeploymentException e) {
			logger.severe(e.getMessage());
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		
	}
	
}
