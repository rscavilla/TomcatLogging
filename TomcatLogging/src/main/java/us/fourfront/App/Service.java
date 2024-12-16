package us.fourfront.App;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;


@Path("/service")
public class Service {

	final static Logger logger = LogManager.getLogger(Service.class);
	private String pn;
	
	public Service() {
		
		String threadName = Thread.currentThread().getName();

		Pattern pattern = Pattern.compile("(\\d{4})");
		Matcher portNumber = pattern.matcher(threadName);
		
		/*
		 * Parse the thread name to get the port.
		 * This port will be sued for routing during the logging process 
		 */
		if (portNumber.find()) {
			/*
			 * Store the port number in tcport
			 * used to identify and name the log file 
			 */
			ThreadContext.put("tcport", portNumber.group(1));
			this.pn=portNumber.group(1);
		} else {
			ThreadContext.put("tcport", "App");
		}
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/ping")
	public String ping() {

		String msg="*********** Service Ping Request *********** Port:" + this.pn;
		logger.info(msg);
		
		return msg;

	}
}
