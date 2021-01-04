/**
 * 
 */
package com.lh.web.core;

/**
 * @author lh
 * @data 2021年1月2日
 * Email 2944862497@qq.com
 */
public interface Servlet {
	
	public void init();
	
	public void service(ServletRequest request, ServletResponse response);

	public void doGet(ServletRequest request, ServletResponse response);
	
	public void doPost(ServletRequest request, ServletResponse response);
}
