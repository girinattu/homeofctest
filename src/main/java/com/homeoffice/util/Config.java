package com.homeoffice.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by giridharvasudevan on 18/01/2018.
 */
public class Config {
	private static Properties props = new Properties();
	private static HashMap <String, Properties> propertyObjMap = new HashMap <>();

	public synchronized static String getConfigProperty(String property, String propertyFile) throws IOException {
		String result = "";
		FileInputStream input = new FileInputStream(propertyFile);
		try {
			Properties prop = new Properties();
			if (input != null) {

				prop.load(input);
			}

			result = prop.getProperty(property);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {

			input.close();
		}
		return result;
	}

}
