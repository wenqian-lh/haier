/**
 * 
 */
package com.lh.tomcat.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件的类
 * @author lh
 * @data 2021年1月2日
 * Email 2944862497@qq.com
 */
public class ReadConfig extends Properties{

	private static final long serialVersionUID = -222299030950038234L;
	
	private static ReadConfig instance = new ReadConfig();
	
	private ReadConfig() {
		try(InputStream is = this.getClass().getClassLoader().getResourceAsStream("web.properties")) {
			load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ReadConfig getInstance() {
		return instance;
	}
	
}
