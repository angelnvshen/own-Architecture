package com.lawcloud.lawper.test.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;

/**
 * Created by CHANEL on 2017/5/7.
 */
public class File2DocumentUtil {

    public static Document fileToDocument(String fileName){
        InputStream inputStream = null;
        try {

            Resource res1 = new FileSystemResource(fileName);
            EncodedResource encRes = new EncodedResource(res1,"UTF-8");
            String str2 = FileCopyUtils.copyToString(encRes.getReader());

            Document document = new Document();
            document.add(new TextField("name", res1.getFilename(), Field.Store.YES));
            document.add(new TextField("content", str2, Field.Store.YES));
            document.add(new StringField("size", String.valueOf(res1.contentLength()), Field.Store.YES));
            document.add(new StringField("path", res1.getURL().toString(), Field.Store.YES));

            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void printDocumentInfo(Document document){

        System.out.println("------------------------------");
        System.out.println("name    =" + document.get("name"));
        System.out.println("content =" + document.get("content"));
        System.out.println("size    =" + document.get("size"));
        System.out.println("path    =" + document.get("path"));
    }
}
