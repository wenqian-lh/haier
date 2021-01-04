/**
 * 
 */
package com.lh.tomcat.core;

/**
 * @author lh
 * @data 2021年1月2日
 * Email 2944862497@qq.com
 */
public interface ConstantInfo {
	public static String BASE_PATH = ReadConfig.getInstance().getProperty("path");
	public static String DEFAULT_PATH = ReadConfig.getInstance().getProperty("default");
	public static String REQUEST_METHOD_GET = "GET";
	public static String REQUEST_METHOD_POST = "POST";
}
