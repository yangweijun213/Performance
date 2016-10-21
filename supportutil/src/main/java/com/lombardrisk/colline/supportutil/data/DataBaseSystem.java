package com.lombardrisk.colline.supportutil.data;
import com.lombardrisk.colline.supportutil.exception.SUException;
import com.lombardrisk.colline.supportutil.util.SUUtil;
import com.lombardrisk.f3.database.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Bond Chen on 2/22/2016.
 * 1)Get Database server information
 * 2)Get Key Table volume information
 */
public class DataBaseSystem {
	private String databasePropertiesFile = null;
	private DataSource dataSource = null;
	private static final String SYSTEM_INFO_SQL_4_ORACLE = "SELECT BANNER from V$VERSION";
	private static final String SYSTEM_INFO_SQL_4_MSSQL = "SELECT @@VERSION as BANNER";
	private static final String TABLE_ROW_COUNT_SQL_PREFIX = "SELECT COUNT(*) COUNT FROM ";
	private static final String COL_NAME_4_BANNER = "BANNER";
	private static final String COL_NAME_4_COUNT = "COUNT";

	/**
	 *
	 * @param databasePropertyFile The database property file, which used to store database connection information
	 *                                (oracle.properties/colline.properties/sqlserver.properties)
	 * @throws SUException Exception if encounter errors
	 */
	public DataBaseSystem(String databasePropertyFile) throws SUException{
		databasePropertiesFile = databasePropertyFile;
		getDataSource(SUUtil.loadProperties(databasePropertyFile));
	}

	private  void getDataSource(Properties properties) throws SUException{
		if(dataSource != null){
			return;
		}
		try{
			dataSource = DataSourceFactory.configure(properties);
		}catch (Exception e){
			throw new SUException("Unable to get data source", e);
		}

	}

	/**
	 * Get the database system level information
	 * @return The database system information
	 * @throws SUException Exception if encounter errors
	 */
	public String getDatabaseSystemInfo() throws SUException{
		StringBuilder stringBuilder = new StringBuilder();
		String sql = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String productName = con.getMetaData().getDatabaseProductName();
			if(productName != null){
				if(productName.contains("Oracle")){
					sql = SYSTEM_INFO_SQL_4_ORACLE;
				}else if(productName.contains("SQL Server")){
					sql = SYSTEM_INFO_SQL_4_MSSQL;
				}else {
					throw new SUException("Not support to get system info for database type: " + productName + ", please extends this utility to support", null);
				}
			}else {
				throw new SUException("Unable to determine the database type", null);
			}
			pstmt = con.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()){
				stringBuilder.append(resultSet.getString(COL_NAME_4_BANNER));
				stringBuilder.append("\n");
			}
			pstmt.close();
			resultSet.close();
		}catch (SQLException se){
			throw new SUException("Error during get database information",se);
		}finally {
			try{
				if(con != null) {
					con.close();
				}
			}catch (SQLException se){

			}
		}
		return stringBuilder.toString();
	}

	/**
	 * Get the key table volume
	 * @param tableSet The set of tables
	 * @param tableSeparator The separator used to separate tables in the configuration properties file
	 * @return The table and correspondent row map
	 * @throws SUException Exception if encounter errors
	 */
	public Map<String, Long> getKeyTableVolume(Set<String> tableSet, String tableSeparator) throws SUException{
		Connection con = null;
		Map<String, Long> tableVolumeMap = new HashMap<String, Long>();
		try {
			if(tableSet.size() == 0){
				return tableVolumeMap;
			}
			con = dataSource.getConnection();
			for(String table:tableSet){
				try {
					table = table.trim();
					//deal with the scenarios of several consecutive comma in the configuration properties
					if (table.contains(tableSeparator)) {
						continue;
					}
					String sql = TABLE_ROW_COUNT_SQL_PREFIX + table;
					PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet resultSet = pstmt.executeQuery();
					if (resultSet.next()) {
						tableVolumeMap.put(table, resultSet.getLong(COL_NAME_4_COUNT));
					} else {
						SUUtil.printMsgAndLogMsg("Unable to determine the row count for table " + table);
					}
					resultSet.close();
					pstmt.close();
				}catch(SQLException se){
					//One table's failure should not break the loop, we can log the error and analyze.
					SUUtil.printMsgAndLogMsg("Error when get row count for table " + table + ", continue to get other tables!");
					SUUtil.printErrorAndlogError(se);
				}
			}

		}catch (SQLException se){
			throw new SUException("Error during get table volume, please check the root error", se);
		}finally {
			try{
				if(con != null) {
					con.close();
				}
			}catch (SQLException se){
			}
		}
		return tableVolumeMap;
	}
}
