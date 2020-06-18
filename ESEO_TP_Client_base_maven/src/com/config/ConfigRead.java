package com.config;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConfigRead {
	private static final String BUNDLE_NAME = "com.config.Conf";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private ConfigRead() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
