package com.qa.test.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author Jaga
 *
 */
public class LoggerHelper {

	private static boolean root = false;
	private static String propertyFilePath = "//src//main//resources//configurations//";
	private static String logConfigFile = "log4j.properties";

	@SuppressWarnings("rawtypes")
	public static Logger getLogger(Class getclass) {
		if (root) {
			return Logger.getLogger(getclass);
		}
		PropertyConfigurator.configure(System.getProperty("user.dir") + propertyFilePath + logConfigFile);
		root = true;
		return Logger.getLogger(getclass);

	}

}
