/**
 * 
 */
package com.lh.tomcat.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.dom4j.DocumentException;

/**
 * 启动类
 * @author lh
 * @data 2021年1月2日
 * Email 2944862497@qq.com
 */
public class StartTomcat {

	public static void main(String[] args) {
		try {
			new StartTomcat().start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public void start() throws IOException, DocumentException {
		// 解析读取配置文件web.xml
		int port = Integer.parseInt(ReadConfig.getInstance().getProperty("port"));		
		
		// 启用一个ServerSocket
		ServerSocket ssk = new ServerSocket(port);
		System.out.println("服务器以启动，占用端口" + port);
		
		new ParseUrlPattern();
		
		new ParseWebXml(); // 读取解析文件后缀你对应的类型
		
		// 启动一个线程或使用线程池处理用户的请求 -> Socket
		ExecutorService serviceThread = Executors.newFixedThreadPool(20);
		
		Socket sk = null;
		while(true) {
			sk = ssk.accept();
			serviceThread.submit(new ServerService(sk));
		}
	}
}
