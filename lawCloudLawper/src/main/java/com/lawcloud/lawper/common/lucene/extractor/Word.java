package com.lawcloud.lawper.common.lucene.extractor;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;

public class Word {

	public static String getContent(InputStream is) {
		String content = null;
		WordExtractor ex = null;
		try {
			ex = new WordExtractor(is);
			content = ex.getText();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String getContent2007(String fileName) {
		OPCPackage opc = null;
		POIXMLTextExtractor ex = null;
		try {
			opc = POIXMLDocument.openPackage(fileName);
			ex = new XWPFWordExtractor(opc);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlException e) {
			e.printStackTrace();
		} catch (OpenXML4JException e) {
			e.printStackTrace();
		}
		return ex.getText();
	}
}
