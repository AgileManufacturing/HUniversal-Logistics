HUniversal-Logistics
====================

To test deploy on Tomcat (tested on Tomcat 7.0.54).

Using Eclipse:
Window -> Preferences
Server -> Runtime Environments -> Add
...

Locations:
	The database location is set in WebContent/META-INF/context.xml
	
	Addresses of other services are found using the DiscoveryTask, these
	addresses can also directly be set in /config/addresses.properties.
	This can be used for testing purposes to run two services on the same host, 
	the DiscoveryTask can't be used to find the services in that case because
	it can't run two or more instances on the same host and the same port.
	These addresses are currently not added or updated by the DiscoveryTask.
	
	The port used by the DiscoveryTask is set in /config/service.properties
	this port should be the same for all services.
	
Servlets:
	DiscoveryListener is used to start the DiscoveryTask.

	CleanupListener is used to deregister JDBC drivers which fail to do this
	themselves.

For improved performance in Tomcat install the Apache Tomcat Native library:
	libtcnative-1 (http://tomcat.apache.org/download-native.cgi)
    32-bit or 64-bit depending on Java architecture.
    
	Windows: put libtcnative-1.dll in %CATALINE_HOME%/bin
	Linux: apt-get/yum install tomcat-native (or libtcnative-1)
	
	
When starting Tomcat from Eclipse the following warning appears:
	WARNING: [SetContextPropertiesRule]{Context} Setting property 'source'
	to 'org.eclipse.jst.jee.server:HUniversal-Logistics' did not find a
	matching property.
	
	This is caused by Eclipse setting a 'source' property which Tomcat
	doesn't use. It has no effect.

ThreadLocal warnings:
	When re-deploying the web service in Tomcat there are a number of warnings
	about the web application creating ThreadLocals which are not removed when
	the web application is stopped. This seems to be caused by third party
	libraries.

	This only causes a problem if the web application is (often) re-deployed
	in production. In this case the libraries in WEB-INF/lib should be moved
	to $CATALINE_HOME/lib so they will be loaded by the common class loader
	and not reloaded when the web application is re-deployed.
