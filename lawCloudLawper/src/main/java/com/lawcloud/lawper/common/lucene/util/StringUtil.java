package com.lawcloud.lawper.common.lucene.util;

import org.apache.commons.lang.StringUtils;

public class StringUtil {
	public static String getKeyDescription(String key) {
		int showLength = 6;
		return StringUtils.isNotEmpty(key) && key.length() > showLength?key.substring(0, showLength) + "******" + key.substring(key.length() - showLength):null;
	}

	public static String getEllipsisStr(String key){
		int showLength = 30;
		return StringUtils.isNotEmpty(key) ? (key.length() >= 22 ? key.substring(0, showLength) + "..." : key) : "";
	}
}
