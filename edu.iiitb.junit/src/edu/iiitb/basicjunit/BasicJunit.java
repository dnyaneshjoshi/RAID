package edu.iiitb.basicjunit;

import java.math.BigDecimal;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This is the basic Junit class which will be extended by the modules to make
 * test cases for their modules.
 * 
 * The parameters or the test data will be maintained in the respective Junit
 * files of the modules.
 * 
 * @author mohnish
 * 
 */
public abstract class BasicJunit {

	/*
	 * This is to configure globally utilised test parameters.
	 */
	protected ResourceBundle globalRb = null;

	/*
	 * This is to configure local test parameters.
	 */
	protected ResourceBundle rb = null;
	/*
	 * Database connection properties
	 */
	private static final String DRIVER = "DRIVER";

	private static final String SERVICE_ID = "SERVICE_ID";

	private static final String USERNAME = "USERNAME";

	private static final String PASSWORD = "PASSWORD";

	private static final String COMMA_SEPARATOR = ",";

	/**
	 * Default Constructor. This first loads all the resource bundle files. The
	 * data or the parameters for the API's being tested are stored in this
	 * resource bundle files.
	 */
	public BasicJunit() {
		String className = null;
		try {
			className = BasicJunit.class.getName();
			globalRb = ResourceBundle.getBundle(className);
		} catch (MissingResourceException exception) {
			System.out.println("Missing global resource bundle.....=> "
					+ className);
		}
		try {
			className = this.getClass().getName();
			rb = ResourceBundle.getBundle(className);
		} catch (MissingResourceException exception) {
			System.out.println("Missing global resource bundle.....=> "
					+ className);
		}
	}

	/*
	 * This retrieves the value corresponding to the property name in the
	 * resource bundle.
	 */
	private String getPropertyValue(final String property) {

		String value = null;
		try {
			if (rb != null)
				value = rb.getString(property);
		} catch (MissingResourceException mre) {
		}
		if (null == value) {
			value = globalRb.getString(property);
		}
		return value;
	}

	/**
	 * This returns the property value from the resource bundle
	 * 
	 * @param property
	 * @return String
	 */
	protected String getStringValue(final String property) {
		return getPropertyValue(property);
	}

	/**
	 * This returns the property value from the resource bundle
	 * 
	 * @param property
	 * @return Integer
	 */
	protected Integer getIntValue(final String property) {
		return Integer.parseInt(getPropertyValue(property));
	}

	/**
	 * This returns the property value from the resource bundle
	 * 
	 * @param property
	 * @return Float
	 */
	protected Float getFloatValue(final String property) {
		return Float.parseFloat(getPropertyValue(property));
	}

	/**
	 * This returns the property value from the resource bundle
	 * 
	 * @param property
	 * @return long
	 */
	protected long getLongValue(final String property) {

		return new Long(getPropertyValue(property)).longValue();
	}

	/**
	 * This returns the property value from the resource bundle
	 * 
	 * @param property
	 * @return double
	 */
	protected double getDoubleValue(final String property) {

		return new Double(getPropertyValue(property)).doubleValue();
	}

	/**
	 * This returns the property value from the resource bundle
	 * 
	 * @param property
	 * @return BigDecimal
	 */
	protected BigDecimal getBigDecimalValue(final String property) {

		return new BigDecimal(getPropertyValue(property));
	}

	/**
	 * This returns the property value from the resource bundle
	 * 
	 * @param property
	 * @return Boolean
	 */
	protected Boolean getBooleanValue(final String property) {

		return Boolean.getBoolean(getPropertyValue(property));
	}

	/**
	 * This returns list of integer values against a specified property
	 * 
	 * @param property
	 * @return int[]
	 */
	protected int[] getIntValues(final String property) {
		String stringValues = getPropertyValue(property);
		int[] values = null;
		int count = 0;
		if (stringValues != null && !stringValues.isEmpty()) {
			String[] stringArray = stringValues.split(COMMA_SEPARATOR);
			values = new int[stringArray.length];
			for (String value : stringArray) {
				values[count++] = Integer.parseInt(value.trim());
			}
		}
		return values;
	}

	/**
	 * This checks whether the property exists in the resource bundle
	 * 
	 * @param property
	 * @return String
	 */
	public boolean isExists(String propertyName) {
		boolean propertyExists = false;
		try {
			getPropertyValue(propertyName);

		} catch (Exception e) {
			// do nothing
		}
		return propertyExists;
	}

}
