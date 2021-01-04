package com.lh.tomcat.util;
/**
 * 字符串工具类
 * @author admin
 * @date 2020年10月25日
 *
 */
public class StringUtil {
	/**
	 * 空判断
	 * @param strs
	 * @return
	 */
	public static boolean checkNull(String ... strs ) {
		//  对象空			空数据
		if (strs == null || strs.length <= 0) {
			return true;
		}
		
		for (String str : strs) {
			if (str == null || "".equals(str)) {
				return true;
			}
		}
		return false;
	}
}
