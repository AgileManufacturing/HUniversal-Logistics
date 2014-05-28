HUniversal-Logistics
====================

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


There are a number of warnings about the web application creating ThreadLocals
which are not removed when the web application is stopped. This seems to be 
caused by third party libraries and Tomcat is able to clean this up.
