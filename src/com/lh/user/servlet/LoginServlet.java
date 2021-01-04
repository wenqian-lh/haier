/**
 * 
 */
package com.lh.user.servlet;

import com.lh.tomcat.core.ConstantInfo;
import com.lh.web.core.HttpServlet;
import com.lh.web.core.HttpServletRequest;
import com.lh.web.core.ServletRequest;
import com.lh.web.core.ServletResponse;

/**
 * @author lh
 * @data 2021年1月3日
 * Email 2944862497@qq.com
 */
public class LoginServlet extends HttpServlet{
	
	
	@Override
	public void doGet(ServletRequest request, ServletResponse response) {
		doPost(request, response);
	}
	
	@Override
	public void doPost(ServletRequest request, ServletResponse response) {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		
		System.out.println(name + "---" + pwd);
		
		response.sendRedirect("login.html");
	}
	
	

}
