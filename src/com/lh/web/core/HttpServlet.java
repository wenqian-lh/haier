/**
 * 
 */
package com.lh.web.core;

import com.lh.tomcat.core.ConstantInfo;

/**
 * @author lh
 * @data 2021年1月3日
 * Email 2944862497@qq.com
 */
public class HttpServlet implements Servlet{

	@Override
	public void init() {
		
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) {
		switch(request.getMethod()) {
		case ConstantInfo.REQUEST_METHOD_GET: doGet(request, response); break;
		case ConstantInfo.REQUEST_METHOD_POST: doPost(request, response); break;
		}
	}

	@Override
	public void doGet(ServletRequest request, ServletResponse response) {
		System.out.println("我是父类--GET");
	}

	@Override
	public void doPost(ServletRequest request, ServletResponse response) {
		System.out.println("我是父类--POST");
	}

}
