/**
 * 
 */
package com.lh.web.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lh.tomcat.util.StringUtil;

/**
 * @author lh
 * @data 2021年1月2日
 * Email 2944862497@qq.com
 */
public class HttpServletRequest implements ServletRequest{

	private String protocalVersion;
	private String method;
	private String url;
	private InputStream is;
	private BufferedReader reader;
	private Map<String, String> parameter = new HashMap<String, String>();
	
	public HttpServletRequest(InputStream is) {
		this.is = is;
		parse();
	}
	
	@Override
	public void parse() {	
		try {
			reader = new BufferedReader(new InputStreamReader(is));
			List<String> headers = new ArrayList<String>();
			String line = null;
			while((line = reader.readLine()) != null && !"".equals(line)) {
				headers.add(line);
			}
			
			// 解析起始行
			parseFirseLine(headers.get(0));
			
			// 解析请求参数
			parseParameter(headers.get(0));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 解析参数
	 * @param string
	 */
	private void parseParameter(String str) {
		if(StringUtil.checkNull(str)) {
			return;
		}
		String[] arrs = str.split(" ");
		if(arrs[1].contains("?")) {
			String param = arrs[1].substring(arrs[1].indexOf("?") + 1); // 获取？后面的参数
			
			if(!StringUtil.checkNull(param) && !" ".equals(param)) { // 如果？后面不为空
				String[] params = param.split("&"); // 分割参数项
				for(String p : params) {
					parameter.put(p.substring(0, p.indexOf("=")).trim(), p.substring(p.indexOf("=") + 1).trim());
				}
			}
			
		}
		
	}

	/**
	 * 解析起始行
	 * @param string
	 */
	private void parseFirseLine(String str) {
		if(StringUtil.checkNull(str)) {
			return;
		}
		
		String[] arrs = str.split(" ");
		this.method = arrs[0]; // 获取请求方式
		if(arrs[1].contains("?")) { // 说明有请求参数
			this.url = arrs[1].substring(0, arrs[1].indexOf("?"));
		} else {
			this.url = arrs[1];
		}
		this.protocalVersion = arrs[2];
	}

	@Override
	public String getParameter(String key) {
		return this.parameter.getOrDefault(key, null);
	}

	@Override
	public String getMethod() {
		return this.method;
	}

	@Override
	public String getUrl() {
		return this.url;
	}

	public String getProtocalVersion() {
		return protocalVersion;
	}
	
	

}
