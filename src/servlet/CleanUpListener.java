package servlet;

import handler.JDBCDatabaseHandler;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CleanUpListener implements ServletContextListener  {
	private static Logger logger = Logger.getLogger(JDBCDatabaseHandler.class.getName());
    
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
