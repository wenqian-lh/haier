/**
 * 
 */
package com.lh.web.core;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lh
 * @data 2021年1月2日
 * Email 2944862497@qq.com
 */
public interface ServletResponse {

	/**
	 * 重定向
	 * @param url
	 */
	public void sendRedirect(String url);
	
	public PrintWriter getWriter() throws IOException;
}
