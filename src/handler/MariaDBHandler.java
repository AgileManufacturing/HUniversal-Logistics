package handler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.mariadb.jdbc.MySQLDataSource;


/**
 * Handles database connections and commands for MariaDB.
 * 
 */
@WebService
public class MariaDBHandler extends JDBCHandler {
	private static Logger logger = Logger.getLogger(MariaDBHandler.class.getName());
	
	//TODO: use pooled connections
	public MariaDBHandler() {
		this.ds = new MySQLDataSource();
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream(propertiesPath));
		} catch (IOException e) {
			logger.severe("Could not find or open configuration file " + propertiesPath);
			logger.severe(e.getMessage());
		}
		
		((MySQLDataSource) ds).setServerName(prop.getProperty("dbServerName"));
		((MySQLDataSource) ds).setDatabaseName(prop.getProperty("databaseName"));
		((MySQLDataSource) ds).setUser(prop.getProperty("dbUser"));
		((MySQLDataSource) ds).setPassword(prop.getProperty("dbPassword"));
		
		try {
			Context ctx = new InitialContext();
			ctx.bind("jdbc/" + databaseName, ds);
		} catch (NamingException e) {
			logger.severe("Could not register and bind MySQLDataSource to jdbc/" + databaseName);
			logger.severe(e.getMessage());
		}
	}

}
