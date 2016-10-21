package com.lrm.biz;

import java.util.UUID;

public class CommonUtil {
	
	/**
	 * get unique ID
	 * 
	 * @return: uuidStr
	 */

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String uuidStr = str.replace("-", "");
		return uuidStr;
	}

}
