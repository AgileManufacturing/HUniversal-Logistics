package servlet;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Deregisters JDBC drivers during shutdown to prevent Tomcat from complaining.</br>
 * 
 * JDBC drivers should be automatically deregistered during shutdown. This does
 * not happen at the moment most likely due to a bug in the driver. This Listener
 * can be removed when the JDBC drivers deregister automatically.
 */
public class CleanUpListener implements ServletContextListener  {
	private static Logger logger = Logger.getLogger(CleanUpListener.class.getName());
    
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
	}
    
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// Manually deregister JDBC driver, to prevents Tomcat 7 from complaining
		Enumeration<Driver> drivers = DriverManager.getDrivers();
	    while (drivers.hasMoreElements()) {
	        Driver driver = drivers.nextElement();
	        try {
	        	logger.info("Deregistering driver " + driver);
	            DriverManager.deregisterDriver(driver);
	        } catch (SQLException e) {
	            logger.severe("Error deregistering driver " + driver + " (" + e.getMessage() + ")");
	            logger.severe(e.getMessage());
	        }

	    }
	}
}
