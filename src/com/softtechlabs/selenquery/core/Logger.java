package com.softtechlabs.selenquery.core;


import java.util.ArrayList;
import java.util.List;

public class Logger {
	private static String level = "";
	private static List<String> logLevelList =  new ArrayList<String>();

	public static  String getLogLevel() {
		return level;
	}
	
	static List<String> getLogLevelList(){
		return logLevelList;
	}
	
	public static void setLogLevel() {
		setLogLevel("all");
	}

	public static void setLogLevel(String logLevel) {
		level = logLevel;
		String [] levels =  logLevel.split(",");
		for (String string : levels) {
			logLevelList.add(string.trim().toLowerCase());
		}
	}

	

}
