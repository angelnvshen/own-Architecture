package com.lawcloud.lawper.common.lucene.extractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Text {

	public static String getContent(InputStream is) {
		StringBuffer content = new StringBuffer("");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "GBK"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				content.append(line + "\r");
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}
}
