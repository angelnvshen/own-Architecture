package com.lawcloud.lawper.common.lucene.extractor;

import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDF {

	public static String getContent(InputStream is){
		StringBuffer content = new StringBuffer("");
		PDFParser p;
		try {
			p = new PDFParser(new RandomAccessBuffer(is));
			p.parse();
			PDFTextStripper ts = new PDFTextStripper();
			PDDocument pdd = p.getPDDocument();
			content.append(ts.getText(pdd));
			is.close();
			pdd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString().trim();
	}

}
