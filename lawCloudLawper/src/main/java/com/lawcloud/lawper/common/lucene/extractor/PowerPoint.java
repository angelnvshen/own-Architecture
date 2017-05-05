package com.lawcloud.lawper.common.lucene.extractor;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.xmlbeans.XmlException;

import java.io.IOException;
import java.io.InputStream;

public class PowerPoint {

    public static String getContent(InputStream is) {
        PowerPointExtractor extractor = null;
        String text = "";
        try {
            extractor = new PowerPointExtractor(is);
            text = extractor.getText();
            extractor.close();
        } catch (Exception e) {
            System.out.println("getTextFromPPT IO错误" + e);
            e.printStackTrace();
        }
        return text;
    }

    public static String getContent2007(String filename) {
        OPCPackage opc = null;
        XSLFPowerPointExtractor pptx = null;
        try {
            opc = POIXMLDocument.openPackage(filename);
            pptx = new XSLFPowerPointExtractor(opc);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
        }

        return pptx.getText();
    }

}
