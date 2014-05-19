package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class Service {
	private static Logger logger = Logger.getLogger(Service.class.getName());
	protected static final String propertiesPath = "./service.properties";
	protected static String serviceIP;
	protected static String servicePort;
	
	protected Service() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(propertiesPath));
			serviceIP = prop.getProperty("serviceIP");
			serviceIP = prop.getProperty("servicePort");
		} catch (IOException e) {
			logger.severe("Could not find or open configuration file " + propertiesPath);
			logger.severe(e.getMessage());
		}
	}
}
