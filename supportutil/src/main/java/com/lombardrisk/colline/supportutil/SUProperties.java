package com.lombardrisk.colline.supportutil;

import com.lombardrisk.colline.supportutil.exception.SUException;
import com.lombardrisk.colline.supportutil.util.SUUtil;

import java.io.File;
import java.util.Properties;

/**
 * Created by Bond Chen on 3/14/2016.
 */
public class SUProperties {

	private static final String COLLINE_HOME = "colline.home";
	private static final String EXTRACT_INCLUDES = "extract.includes";
	private static final String EXTRACT_EXCLUDES = "extract.excludes";
	private static final String KEY_TABLES = "key.tables";
	private static final String DEFAULT_COLLINE_HOME =  "./../../";
	private static final String EMPTY_STRING =  "";

	private String collineHome = null;
	private String extractIncludes = null;
	private String extractExcludes = null;
	private String keyTables = null;

	/**
	 *
	 * @param propertyFile The property file that defines the support utility's configuration
	 * @return Properties object that contains properties
	 * @throws SUException Exception if have error during create instance.
	 */
	public static SUProperties getInstance(String propertyFile) throws SUException{
		SUProperties suProperties = new SUProperties();
		suProperties.loadParameters(propertyFile);
		return suProperties;
	}

	private String getPropertyValue(Properties properties,String propertyName, boolean allowNullOrEmpty) throws
			SUException {
		if(propertyName == null){
			throw new SUException("propertyName is null", null);
		}

		if(properties == null){
			throw new SUException("properties is null", null);
		}

		String string = properties.getProperty(propertyName);
		if(string == null || string.isEmpty()){
			if (allowNullOrEmpty){
				return EMPTY_STRING;
			}else {
				throw new SUException("property '" + propertyName + "' is not allowed to be empty",null);
			}
		}else {
			return string;
		}
	}

	private void loadParameters (String propertyFile) throws SUException{
		Properties properties = SUUtil.loadProperties(propertyFile);
		String home = getPropertyValue(properties, COLLINE_HOME, true);
		if(!(new File(home)).exists()){
			SUUtil.printMsgAndLogMsg("colline home '" + home + "' do not exist");
		}
		if(EMPTY_STRING.equals(home)){
			SUUtil.printMsgAndLogMsg("use " + DEFAULT_COLLINE_HOME + " as colline home");
			home = DEFAULT_COLLINE_HOME;
		}
		if(!(new File(home)).exists()){
			throw new SUException("colline home " + home + " do not exist", null);
		}
		setCollineHome(home);
		setExtractIncludes(getPropertyValue(properties, EXTRACT_INCLUDES, true));
		setExtractExcludes(getPropertyValue(properties, EXTRACT_EXCLUDES, true));
		setKeyTables(getPropertyValue(properties, KEY_TABLES, false));
	}

	public String getCollineHome() {
		return collineHome;
	}

	public void setCollineHome(String collineHome) {
		this.collineHome = collineHome;
	}

	public String getExtractIncludes() {
		return extractIncludes;
	}

	public void setExtractIncludes(String extractIncludes) {
		this.extractIncludes = extractIncludes;
	}

	public String getExtractExcludes() {
		return extractExcludes;
	}

	public void setExtractExcludes(String extractExcludes) {
		this.extractExcludes = extractExcludes;
	}

	public String getKeyTables() {
		return keyTables;
	}

	public void setKeyTables(String keyTables) {
		this.keyTables = keyTables;
	}

	@Override public String toString() {
		return "SUProperties{" +
				"collineHome='" + collineHome + '\'' +
				", extractIncludes='" + extractIncludes + '\'' +
				", extractExcludes='" + extractExcludes + '\'' +
				", keyTables='" + keyTables + '\'' +
				'}';
	}
}
