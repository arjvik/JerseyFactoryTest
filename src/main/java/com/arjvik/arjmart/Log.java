package com.arjvik.arjmart;

public class Log {
	private static StringBuilder log = new StringBuilder();
	public static void log(String str) {
		log.append(str+"\n");
	}
	public static String getLog() {
		return log.toString();
	}
}
