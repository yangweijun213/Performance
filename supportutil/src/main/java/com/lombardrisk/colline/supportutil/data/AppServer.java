package com.lombardrisk.colline.supportutil.data;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Bond Chen on 2/22/2016.
 * AppServer level information
 */
public class AppServer {
	private static final long KILO_BYTES = 1024;
	private static final long MEGA_BYTES = KILO_BYTES * 1024;
	private static final long GIGA_BYTES = MEGA_BYTES * 1024;
	private static final String GB = "(GB)";

	private static final Set<String> systemPropertyNameSet = new HashSet<String>();
	private static Map<String,Map<String,String>> osDataMap = new LinkedHashMap<String,Map<String, String>>();

	private static final String UNKNOWN = "UNKNOWN";

	static {
		systemPropertyNameSet.add("os.name");
		systemPropertyNameSet.add("os.version");
		systemPropertyNameSet.add("os.arch");
	}

	/**
	 * Get OS information
	 * @return the nested map that stores the parameters and the value of parameters
	 */
	public static Map<String, Map<String,String>> getOSInformation(){
		{
			//get from system properties
			Map<String, String> generalOSInfoMap = new LinkedHashMap<String,String>();
			osDataMap.put("General AppServer Information", generalOSInfoMap);
			for(String property : systemPropertyNameSet){
				generalOSInfoMap.put(property, System.getProperty(property, UNKNOWN));
			}
			//os data
			com.sun.management.OperatingSystemMXBean operatingSystemMXBean =  ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean());
			generalOSInfoMap.put("total physical memory(MB)", Long.toString(operatingSystemMXBean.getTotalPhysicalMemorySize()/MEGA_BYTES));
			generalOSInfoMap.put("free physical memory(MB)", Long.toString(operatingSystemMXBean.getFreePhysicalMemorySize()/MEGA_BYTES));
			generalOSInfoMap.put("total swap space(MB)", Long.toString(operatingSystemMXBean.getTotalSwapSpaceSize()/MEGA_BYTES));
			generalOSInfoMap.put("total available processors", Integer.toString(operatingSystemMXBean.getAvailableProcessors()));


			/* Get a list of all filesystem roots on this system */
			Map<String, String> disInfoMap = new LinkedHashMap<String,String>();
			osDataMap.put("Filesystem Information", disInfoMap);
			File[] roots = File.listRoots();
    		/* For each filesystem root, print some info */
			for (File root : roots) {
				disInfoMap.put("total space of " + root.getAbsolutePath() + GB, Long.toString(root.getTotalSpace()/GIGA_BYTES));
				disInfoMap.put("free space of " + root.getAbsolutePath() + GB, Long.toString(root.getTotalSpace()/GIGA_BYTES));
				disInfoMap.put("usable space of " + root.getAbsolutePath() + GB, Long.toString(root.getUsableSpace()/GIGA_BYTES));
			}

			//get data from system properties
			Map<String, String> systemPropertiesMap = new LinkedHashMap<String,String>();
			osDataMap.put("System Properties", systemPropertiesMap);
			Set<Object> keySet = System.getProperties().keySet();
			for(Object key:keySet){
				 systemPropertiesMap.put((String)key, System.getProperty((String)key).toString());
			}

			//get data from environment values
			Map<String, String> envValues = new LinkedHashMap<String,String>();
			osDataMap.put("Environment Values", envValues);
			envValues.putAll(System.getenv());

			return osDataMap;
		}
	}

}
