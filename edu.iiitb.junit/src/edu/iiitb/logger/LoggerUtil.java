package edu.iiitb.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * This is a utility for initializing the logger .
 * 
 * @author mohnish
 * 
 */
public class LoggerUtil {

	private static volatile LoggerUtil loggerUtil = null;

	private static FileHandler fileTxt;

	private static Formatter formatterTxt;

	private Logger logger = null;

	private LoggerUtil() {

	}

	public static LoggerUtil getInstance() {
		if (loggerUtil == null) {
			synchronized (LoggerUtil.class) {
				if (loggerUtil == null) {
					loggerUtil = new LoggerUtil();
					loggerUtil.init();
				}
			}
		}
		return loggerUtil;
	}

	public Logger getLogger() {
		return logger;
	}

	/**
	 * Setups the logger for logging data
	 */
	public void init() {

		// Get the global logger to configure it
		logger = Logger.getLogger("");

		// TODO:Change this SEVERE to FINE to see the logs in Logging.txt
		logger.setLevel(Level.SEVERE);

		try {
			fileTxt = new FileHandler("Logging.txt");

			// create txt Formatter
			formatterTxt = new SimpleFormatter();
			fileTxt.setFormatter(formatterTxt);
			logger.addHandler(fileTxt);
			logger.log(Level.FINE, "Checking");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}
}
