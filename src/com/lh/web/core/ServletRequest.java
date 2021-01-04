/**
 * 
 */
package com.lh.web.core;

/**
 * @author lh
 * @data 2021年1月2日
 * Email 2944862497@qq.com
 */
public interface ServletRequest {

	/**
	 * 解析请求
	 */
	public void parse();
	
	/**
	 * 获取请求参数
	 * @param key 
	 * @return
	 */
	public String getParameter(String key);
	
	/**
	 * 获取请求方式
	 * @return
	 */
	public String getMethod();
	
	/**
	 * 获取请求地址
	 * @return
	 */
	public String getUrl();

}
