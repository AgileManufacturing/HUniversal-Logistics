package handlers;

import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.mariadb.jdbc.MySQLDataSource;


/**
 * Handles database connections and commands for MariaDB.
 * 
 */
public class MariaDBHandler extends JDBCHandler {
	private static Logger logger = Logger.getLogger(MariaDBHandler.class.getName());
	
	//TODO: use pooled connections
	public MariaDBHandler(String serverName, String user, String password) {
		this.ds = new MySQLDataSource();
		
		((MySQLDataSource) ds).setServerName(serverName);
		((MySQLDataSource) ds).setDatabaseName(databaseName);
		((MySQLDataSource) ds).setUser(user);
		((MySQLDataSource) ds).setPassword(password);
		
		try {
			Context ctx = new InitialContext();
			ctx.bind("jdbc/" + databaseName, ds);
		} catch (NamingException e) {
			logger.severe("Could not register and bind MySQLDataSource to jdbc/" + databaseName);
			logger.severe(e.getMessage());
		}
	}

}
