package com.lombardrisk.colline.supportutil;

import com.lombardrisk.colline.supportutil.data.CollineHome;
import com.lombardrisk.colline.supportutil.data.DataBaseSystem;
import com.lombardrisk.colline.supportutil.data.AppServer;
import com.lombardrisk.colline.supportutil.exception.SUException;
import com.lombardrisk.colline.supportutil.util.SUUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The entry of support utility
 * Created by Bond Chen on 2/19/2016.
 */
public class Main {



	/**
	 *
	 * @param args args from command line caller
	 *             args[0] support utility configuration file
	 *             args[1] database configuration file, oracle.properties/sqlserver.properties before v14.1 or colline.properties in/after v14.1
	 */
	public static void main(String[] args){
		String dataBaseDataFile = null;
		String appDataFile = null;
		String suConfigFile = args[0];
		String databaseConfigFile = args[1];
		try{
			dataBaseDataFile = SUUtil.getDataBaseDataFileName();
			SUProperties suProperties = SUProperties.getInstance(suConfigFile);
			SUUtil.printMsgAndLogMsg("Support Utility Configuration:");
			SUUtil.printMsgAndLogMsg(suProperties.toString());
			SUUtil.printMsgAndLogMsg("database properties file:" + databaseConfigFile);
			try{
				//get support data regarding Database System
				SUUtil.printMsgAndLogMsg("Extract database system data");
				DataBaseSystem dataBase = (new DataBaseSystem(databaseConfigFile));
				SUUtil.log2File(dataBaseDataFile, "Database System Information\n");
				SUUtil.log2File(dataBaseDataFile, dataBase.getDatabaseSystemInfo());
				String[] tables = suProperties.getKeyTables().split(SUUtil.COMMA_SEPARATOR);
				Set<String> tableSet = new HashSet<String>(Arrays.asList(tables));
				SUUtil.log2File(dataBaseDataFile, String.format(SUUtil.FORMAT, "Key Table", "Data Volume\n"));
				SUUtil.logMap2File(dataBaseDataFile, dataBase.getKeyTableVolume(tableSet, SUUtil.COMMA_SEPARATOR));
			}catch (SUException sue){
				//failure to collect database data should not block the collection of other data
				SUUtil.printMsgAndLogMsg("Utility encounter error during extract database system data");
				SUUtil.printErrorAndlogError(sue);
			}

			//get support data regarding  Application Server
			SUUtil.printMsgAndLogMsg("Extract application server data");
			appDataFile = SUUtil.getAppServerDataFileName();
			SUUtil.log2NestMapFile(appDataFile, AppServer.getOSInformation());

			//get support data regarding Colline & Jboss directory, and zipped it.
			SUUtil.printMsgAndLogMsg("Extract support data");
			String zipFileName = SUUtil.getZipFileName();
			String collineHome = suProperties.getCollineHome();
			(new CollineHome(suProperties.getCollineHome())).zip(zipFileName,
							suProperties.getExtractIncludes()  + SUUtil.COMMA_SEPARATOR + SUUtil.getAdditionalIncludes4SupportData(), suProperties.getExtractExcludes());
			SUUtil.printMsgAndLogMsg("Support Data File compressed in:" + zipFileName);
		}catch (SUException sue){
			SUUtil.printMsgAndLogMsg("Utility encounter error");
			SUUtil.printErrorAndlogError(sue);
		}catch (Exception e){
			SUUtil.printMsgAndLogMsg("Utility encounter error");
			SUUtil.printErrorAndlogError(e);
		}finally {
			try {
				SUUtil.removeFile(appDataFile);
				SUUtil.removeFile(dataBaseDataFile);
				SUUtil.removeLogFile();
			}catch(Exception e){
				//nothing.
			}
		}
	}

}
