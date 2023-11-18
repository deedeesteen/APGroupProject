package logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class logging {

	private static final Logger logger = LogManager.getLogger(logging.class);
	
	public static void main(String[] args){

		logger.log(Level.INFO, "New student information added.");
		logger.debug("Debugged.");
		logger.error("Error!");
		logger.trace("Traced.");
		logger.fatal("Fatal!");
		logger.warn("Warning!");
	
	}

}

