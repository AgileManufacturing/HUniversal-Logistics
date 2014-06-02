package servlet;

import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Starts an instance of {@link servlet.DiscoveryTask} during startup
 * and stops this instance during shutdown.</br>
 */
public class DiscoveryListener implements ServletContextListener  {
	private static Logger logger = Logger.getLogger(DiscoveryListener.class.getName());
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("Starting new discovery thread");
		DiscoveryTask.getInstance().start();
		logger.info("New discovery thread started");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("Stopping discovery thread");
		DiscoveryTask.getInstance().stop();
		logger.info("Discovery thread stopped");
	}
}
