package com.lrm.biz;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.lrm.data.DBHelper;

public class BizHelper {
	
	private static String transactionName;
	private static long userActionTime;
	private static long domReadyTime;
	private static long startTime;
	private static long endTime;
	
	static DBHelper dbHelper = new DBHelper();
	
	public static void startTransaction(String name, long time) {
		transactionName = name;
		userActionTime = time;
		
		dbHelper.saveDimTransaction(transactionName);
	}
	
	public static void endTransaction (long time) {
		domReadyTime = time;
		dbHelper.saveFctTestResult(userActionTime, domReadyTime);
	}
	
	public static void startTime () {
		startTime = System.currentTimeMillis();
		endTime = 0;
		dbHelper.saveDimTestRun(startTime, endTime);
	}
	
	public static void endTime () {
		endTime = System.currentTimeMillis();
		dbHelper.updateDimTestRun(endTime);
		
	}
	
}
