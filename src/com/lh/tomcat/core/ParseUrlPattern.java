/**
 * 
 */
package com.lh.tomcat.core;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author lh
 * @data 2021年1月3日
 * Email 2944862497@qq.com
 */
public class ParseUrlPattern {
	
	private String basePath = ConstantInfo.BASE_PATH;
	private static Map<String, String> urlPattern = new HashMap<String, String>();
	
	public ParseUrlPattern() {
		parse();
	}

	/**
	 * 解析servlet配置文件，web.xml
	 */
	private void parse() {
		File[] files = new File(basePath).listFiles();
		
		if(files == null || files.length == 0) {
			return;
		}
		
		String projectName = null; // 存放项目名，即当前文件夹的名字
		File webFile = null; 
		
		for(File fl : files) {
			if(!fl.isDirectory()) {
				continue;
			}
			
			projectName = fl.getName();
			
			webFile = new File(fl, "WEB-INF/web.xml");
			if(!webFile.exists() || !webFile.isFile()) {
				continue;
			}
			parseXml(projectName, webFile);
		}
	}

	/**
	 * 
	 * @param projectName
	 * @param webFile
	 */
	private void parseXml(String projectName, File webFile) {
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(webFile);
			List<Element> servletList = doc.selectNodes("//servlet");
			if(servletList == null || servletList.isEmpty()) {
				return;
			}
			
			for(Element el : servletList) {
				urlPattern.put("/" + projectName + el.selectSingleNode("url-pattern").getText().trim(), el.selectSingleNode("servlet-class").getText().trim());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static String getClass(String url) {
		return urlPattern.getOrDefault(url, null);
	}

}
