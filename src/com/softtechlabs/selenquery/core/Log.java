package com.softtechlabs.selenquery.core;

import java.util.List;

public class Log {

	private static List<String> logLevelList;

	static {
		logLevelList = Logger.getLogLevelList();
		if(logLevelList.size() <= 0){
			System.err.println("LOGGER WARNING: Log level is not set");
			
		}
	}

	public static void debug(String message) {
		if (logLevelList.contains("debug") || logLevelList.contains("all")) {
			System.out.println("DEBUG : " + message);
		}
	}

	public static void info(String message) {
		if (logLevelList.contains("info") || logLevelList.contains("all")) {
			System.out.println("INFO : " + message);
		}
	}

	public static void warning(String message) {
		if (logLevelList.contains("warning") || logLevelList.contains("all")) {
			System.out.println("WARNING : " + message);
		}
	}

	public static void error(String message) {
		if (logLevelList.contains("error") || logLevelList.contains("all")) {
			System.err.println("ERROR : " + message);
		}
	}
}
