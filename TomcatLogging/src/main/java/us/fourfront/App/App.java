package us.fourfront.App;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.ws.rs.ApplicationPath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/app")
public class App extends ResourceConfig {

	private static final Logger logger = LogManager.getLogger(App.class);
	
	public App() {
		
		/*
		 * The value stored in tcport will be used in log4j2.xml to name the log file
		 */
		ThreadContext.put("tcport", "App");
		
		try {
			System.setProperty("hostName", InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			logger.error("Failed to set hostname");
		}
		
		logger.info("Starting us.fourfront.App class...");

	}

}
