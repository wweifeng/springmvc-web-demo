package com.mframe.util;

public class StringUtils {
	
	/**
	 * 首字母小写
	 * 小写字母转化成大写字母
	 * char 字符的ascii码值加上 32
	 * @param str
	 * @return
	 */
	public static String lowerFirstCapse(String str){
		char[] chars = str.toCharArray();
		chars[0] += 32;
		return String.valueOf(chars);
	}
	
}
