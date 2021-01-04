/**
 * 
 */
package com.lh.web.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.lh.tomcat.core.ConstantInfo;
import com.lh.tomcat.core.ParseWebXml;
import com.lh.tomcat.util.StringUtil;

/**
 * @author lh
 * @data 2021年1月2日
 * Email 2944862497@qq.com
 */
public class HttpServletResponse implements ServletResponse {

	private OutputStream os = null;
	private String basePath = ConstantInfo.BASE_PATH;
	private String projectName;
	
	public HttpServletResponse(OutputStream os, String projectName) {
		this.os = os;
		this.projectName = "/" + projectName;
	}
	
	@Override
	public void sendRedirect(String url) {
		if(StringUtil.checkNull(url)) {
			error404(url);
			return;
		}	
		
		if(!url.startsWith(projectName)) {
			send302(projectName + "/" + url);
			return;
		}
		
		if(url.startsWith("/") && url.indexOf("/") == url.lastIndexOf("/")) {
			send302(url + "/");
			return;
		} else {
			if (url.endsWith("/")) { // 说明后面没有指定资源项
				String defaultPath = ConstantInfo.DEFAULT_PATH; // 获取默认访问的页面
				
				File fl = new File(basePath, url.substring(1).replace("/", "\\") + defaultPath);
				if(!fl.exists() || !fl.isFile()) {
					error404(url);
					return;
				}
				
				send200(readFile(fl), url.substring(url.lastIndexOf(".") + 1).toLowerCase());
				return;
			}
			
			File fl = new File(basePath, url.substring(1).replace("/", "\\"));
			if(!fl.exists() || !fl.isFile()) {
				error404(url);
				return;
			}
			send200(readFile(fl), url.substring(url.lastIndexOf(".") + 1).toLowerCase());
		}
	}
	
	/**
	 * 在url后面补一个 /
	 * @param url
	 */
	private void send302(String url) {
		try {
			String responseHeader = "HTTP/1.1 302 Moved Temporarily\r\nContent-Type: text/html;charset=utf-8\r\nLocation: " + url + "\r\n\r\n";
			os.write(responseHeader.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			if(os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 发送
	 * @param bt 要发送的数据
	 * @param extension 文件扩展名
	 */
	private void send200(byte[] bt, String extension) {
		String ContentType = "text/html;charset=utf-8";
		String type = ParseWebXml.getContentType(extension);
		if (!StringUtil.checkNull(type)) {
			ContentType = type;
		}
		try {
			String responseHeader = "HTTP/1.1 200 OK\r\nContent-Type: " + ContentType + " \r\nContent-Length: " + bt.length + "\r\n\r\n";
			os.write(responseHeader.getBytes());
			os.write(bt);
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * 读取指定的文件
	 * @param fl
	 * @return
	 */
	private byte[] readFile(File fl) {
		byte[] bt = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fl);
			bt = new byte[fis.available()];
			fis.read(bt);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return bt;
	}
	
	/**
	 * 404
	 * @param url
	 */
	private void error404(String url) {
		String data = "<h1>HTTP Status 404 - " + url + "</h1>";
		String responseHeader = "\"HTTP/1.1  404 File Not Found\\r\\nContent-Type: text/html;charset=utf-8\\r\\nContent-Length: \" + bt.length + \"\\r\\n\\r\\n\";\r\n" ; 
		try {
			os.write(responseHeader.getBytes());
			os.write(data.getBytes());
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
