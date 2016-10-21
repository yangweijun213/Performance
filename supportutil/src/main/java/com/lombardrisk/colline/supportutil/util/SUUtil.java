package com.lombardrisk.colline.supportutil.util;

import com.lombardrisk.colline.supportutil.exception.SUException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Bond Chen on 3/10/2016.
 */
public class SUUtil {

	private static final String ZIP_FILE_PREFIX = "CollineSupportData_";
	private static final String DATABASE_DATA_FILE_PREFIX = "database_";
	private static final String APP_SERVER_DATA_FILE_PREFIX = "appserver_";
	private static final String ZIP_FILE_SUFFIX = ".zip";
	private static final String DATA_FILE_SUFFIX = ".lrssupport.data";
	private static final String DATE_TIME_FORMAT = "yyyyMMdd_HHmmss";
	private static final String LINE_SEPARATOR = "=====================";
	public static final String FORMAT = "%-50s %-20s";
	private static final String LOG_FILE_PREFIX = "su_";
	private static final String LOG_FILE_SUFFIX = ".lrssupport.log";
	public static final String COMMA_SEPARATOR = ",";
	private static String logFileName = null;

	/**
	 * Prints a String and log to log file
	 *
	 * @param msg The String to be printed
	 */
	public static void printMsgAndLogMsg(String msg) {
		try {
			System.out.println(msg);
			log2File(getLogFileName(), msg);
		} catch (SUException sue){
			//nothing as log to file encounter errors.
		}
	}



	/**
	 * Prints this throwable and its backtrace
	 *
	 * @param t The throwable to print and log
	 *
	 */
	public static void printErrorAndlogError(Throwable t) {
		try{

			if(t != null){
				t.printStackTrace();
				log2File(getLogFileName(), t.toString());
			}
			if(t instanceof SUException){
				SUUtil.printMsgAndLogMsg("Nest exception:");
				if(((SUException) t).getRootCause() != null){
					((SUException) t).getRootCause().printStackTrace();
					log2File(getLogFileName(), ((SUException) t).getRootCause().toString());
				}
			}
		}catch (SUException sue){
			//nothing as log to file encounter errors.
		}
	}

	/**
	 * Load properties from property file
	 * @param propertyFile property file
	 * @return properties object that contain the properties
	 */
	public static Properties loadProperties(String propertyFile) throws SUException{
		Properties properties = null;
		try {
			properties = new Properties();
			InputStream inputStream = new FileInputStream(propertyFile);
			properties.load(inputStream);
		}catch (FileNotFoundException fnfe){
			throw new SUException("File " + propertyFile + " do not find, please check !", fnfe);
		}catch (IOException ioe){
			throw new SUException("Error during load properties from file " + propertyFile + ", please check !", ioe);
		}
		return properties;
	}

	/**
	 * Log info to file,if file not exist, then create one, if exist, then append
	 * @param file The file name to log information
	 * @param info The information to log to file
	 * @throws SUException SUException if having error during log file
	 */
	public static void log2File(String file, String info) throws SUException{
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
			bufferedWriter.write(info);
			bufferedWriter.newLine();
			bufferedWriter.close();
		}catch(IOException ioe){
			throw new SUException("Error during write file", ioe);
		}
	}

	/**
	 * Log nest map data to file, if file not exist, then create one, if exist, then append.
	 * @param file The file name to log information
	 * @param info The information to log to file
	 * @throws SUException SUException SUException if having error during log file
	 */
	public static void log2NestMapFile(String file, Map<String, Map<String, String>> info) throws SUException{
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
			Set<String> set = info.keySet();
			for (String key : set) {
				bufferedWriter.newLine();
				bufferedWriter.write(LINE_SEPARATOR);
				bufferedWriter.newLine();
				bufferedWriter.write(key);
				bufferedWriter.newLine();
				bufferedWriter.write(LINE_SEPARATOR);
				bufferedWriter.newLine();
				Map<String,String> nestMap = info.get(key);
				Set<String> nestKeySet = nestMap.keySet();
				for(String nestKey:nestKeySet){
					bufferedWriter.write(String.format(FORMAT, nestKey, nestMap.get(nestKey)));
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.close();
		}catch (IOException ioe){
			throw new SUException("Error during write file", ioe);
		}
	}

	/**
	 *
	 * Log  map data to file, if file not exist, then create one, if exist, then append.
	 * @param file The file name to log information
	 * @param info The information to log to file
	 * @throws SUException SUException SUException if having error during log file
	 */
	public static void logMap2File(String file, Map<String,Long> info) throws SUException{
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
			Set<String> set = info.keySet();
			for (String key : set) {
				bufferedWriter.write(String.format(FORMAT, key, info.get(key)));
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
		}catch (IOException ioe){
			throw new SUException("Error during write file", ioe);
		}
	}

	/**
	 * Get Database data file name
	 * @return the database file name
	 */
	public static String getDataBaseDataFileName(){
		String timeStamp = new SimpleDateFormat(DATE_TIME_FORMAT).format(Calendar.getInstance().getTime());
		return DATABASE_DATA_FILE_PREFIX + timeStamp + DATA_FILE_SUFFIX;
	}

	/**
	 * Get the application server data file name
	 * @return the application server data file name
	 */
	public static String getAppServerDataFileName(){
		String timeStamp = new SimpleDateFormat(DATE_TIME_FORMAT).format(Calendar.getInstance().getTime());
		return APP_SERVER_DATA_FILE_PREFIX + timeStamp + DATA_FILE_SUFFIX;
	}

	/**
	 * Get the support data package name
	 * @return the support data package name
	 */
	public static String getZipFileName(){
		String timeStamp = new SimpleDateFormat(DATE_TIME_FORMAT).format(Calendar.getInstance().getTime());
		return ZIP_FILE_PREFIX + timeStamp + ZIP_FILE_SUFFIX;
	}

	/**
	 * Get additional includes to the support package,includes database and application server data and log files
	 * @return The includes string
	 */
	public static String getAdditionalIncludes4SupportData(){
		return "**/*"+DATA_FILE_SUFFIX + COMMA_SEPARATOR + "**/*" + LOG_FILE_SUFFIX;
	}

	/**
	 * Remove file if exist, do nothing if not exist
	 * @param fileName the file to be deleted here.
	 */
	public static void removeFile(String fileName){
		File file = new File(fileName);
		if(file.exists()){
			file.delete();
		}
	}

	/**
	 * Get the support utility log file name.
	 * @return the support utility log file name
	 */
	public static String getLogFileName(){
		if(logFileName == null) {
			String timeStamp = new SimpleDateFormat(DATE_TIME_FORMAT).format(Calendar.getInstance().getTime());
			logFileName = LOG_FILE_PREFIX + timeStamp + LOG_FILE_SUFFIX;
		}
		return logFileName;
	}

	/**
	 *Remove the support utility file,
	 *Remove file if exist, do nothing if not exist
	 */
	public static void removeLogFile(){
		removeFile(getLogFileName());
	}
}
