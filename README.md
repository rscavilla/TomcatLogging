## Tomcat Multiport Logging using Log4j2
This repo demonstrates how to have Tomcat running on multiple ports on the same server and record log events in separate log files for each port. 
To get this sample code to run:
1.	Update **CATALINA_HOME/conf/server.xml**
2.	Enter the log file directory in line 4 of the log4j2.xml file
3.	Build the project and load onto the tomcat server
4.	Call the endpoint: http://yourhost:8080/TomcatLogging/app/service/ping
5.	Check the log files
   
NOTES:

•	This configuration will create a {hostname} subdirectory in the log file directory

•	There will be one file named TomcatLogging-App.log where applicatgion events will be logged

•	There will be a combined log file for all events: TomcatLogging.log

•	There will be a log containing the port number file for each port the endpoint was called on: TomcatLogging-4545.log

Within the JAX-RS runtime, there is one main thread for the application class derived from ResourceConfig. In this code, that class is the App.class. Each port declared in the server.xml file creates a unique thread for the service class. Each thread name contains the port number: http-nio-4545-exec-3. Here, the request came in on port 4545. The port is parsed from the thread name and used for routing during the logging process and for naming the log files.

    <Connector port="4545" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443"
               maxParameterCount="1000"
               />

    <Connector port="4546" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443"
               maxParameterCount="1000"
               />

In the log4j2.xml file, use $${ctx:tcport} to get the port number. The following property sets the logging pattern to timestamp, hostname:port number, 5 spaces, log event type, class and line number, message:

```<Property name="LOG_PATTERN">%d{yyyy-MM-dd'T'HH:mm:ss} ${hostName}:$${ctx:tcport} %-5p	%c{1}:%L %m%n</Property>```

```-rw-r----- 1 o b     103 Dec 16 11:43 TomcatLogging-4545.log
-rw-r----- 1 o b     103 Dec 16 11:43 TomcatLogging-4546.log
-rw-r----- 1 o b      78 Dec 16 11:43 TomcatLogging-App.log
-rw-r----- 1 o b     284 Dec 16 11:43 TomcatLogging.log
```
