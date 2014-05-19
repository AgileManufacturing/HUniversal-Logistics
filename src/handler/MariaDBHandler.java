package handler;

import java.util.logging.Logger;

import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.mariadb.jdbc.MySQLDataSource;


/**
 * Handles database connections and commands for MariaDB.
 */
@WebService
public class MariaDBHandler extends JDBCHandler {
	private static Logger logger = Logger.getLogger(MariaDBHandler.class.getName());
	
	//TODO: use pooled connections
	public MariaDBHandler() {
		this.ds = new MySQLDataSource();
		
		((MySQLDataSource) ds).setServerName(this.ip);
		((MySQLDataSource) ds).setUser(this.user);
		((MySQLDataSource) ds).setPassword(this.password);
		((MySQLDataSource) ds).setDatabaseName(databaseName);
		
		try {
			Context ctx = new InitialContext();
			ctx.bind("jdbc/" + databaseName, ds);
		} catch (NamingException e) {
			logger.severe("Could not register and bind MySQLDataSource to jdbc/" + databaseName);
			logger.severe(e.getMessage());
		}
	}

}
