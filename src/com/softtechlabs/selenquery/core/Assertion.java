package com.softtechlabs.selenquery.core;

class Assertion {

	public static boolean test(boolean expected, boolean condition) {
		return test(expected, condition, "");
	}

	public static boolean test(boolean expected, String val1, String val2, String customMessage) {
		if (!test(expected, val1.equals(val2), customMessage)) {
			printDetails(expected,val1, val2);
			return false; 
		}
		return true;
	}

	public static boolean test(boolean expected, int val1, int val2, String customMessage) {
		if (!test(expected, val1 == val2, customMessage)) {
			printDetails(expected,val1, val2);
			return false; 
		}
		return true;
	}

	public static boolean test(boolean expected, long val1, long val2, String customMessage) {
		if (!test(expected, val1 == val2, customMessage)) {
			printDetails(expected,val1, val2);
			return false; 
		}
		return true;
	}

	public static boolean test(boolean expected, Object val1, Object val2, String customMessage) {
		if (!test(expected, val1 == val2, customMessage)) {
			printDetails(expected,val1, val2);
			return false; 
		}
		return true;
	}

	static void printDetails(boolean expected, Object val1, Object val2) {
		if (expected) {
			System.err.println("Expected Value : " + val1);
		} else {
			System.err.println("Expected Value Should Not : " + val1);
		}
		System.err.println("Actual Value   : " + val2);
		sleep(100);
		throw new RuntimeException("Assertion Failed");
	}

	public static boolean test(boolean expected, boolean condition, String customMessage) {
		if (condition == expected) {
			return true;
		} else {
			if (customMessage.equals("")) {
				System.err.print("Assertion Failed ");
			} else {
				System.err.println("Assertion Failed : " + customMessage);
			}
			sleep(100);
			return false;
		}

	}

	private static void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
