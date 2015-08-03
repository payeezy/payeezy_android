package com.firstdata.firstapi.client;

public class MessageLogger {

	public static void logMessage(String message)
	{
		
        System.out.println(message);
	}
	public static void logMessage(String message, String format )
	{
		String logMessage = String.format("SecureRandom nonce:{}",format);
        System.out.println(logMessage);
	}
	
	public static void debug(String message, String format )
	{
		String logMessage = String.format("SecureRandom nonce:{}",format);
        System.out.println(logMessage);
	}
	
	public static void info(String message, String format )
	{
		String logMessage = String.format("SecureRandom nonce:{}",format);
        System.out.println(logMessage);
	}
	
	public static void error(String message, String format )
	{
		String logMessage = String.format("SecureRandom nonce:{}",format);
        System.out.println(logMessage);
	}
}
