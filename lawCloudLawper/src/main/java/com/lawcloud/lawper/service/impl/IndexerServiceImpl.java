package com.lawcloud.lawper.service.impl;

import com.lawcloud.lawper.common.lucene.extractor.*;
import com.lawcloud.lawper.common.lucene.util.DateUtil;
import com.lawcloud.lawper.common.lucene.util.LuceneUtil;
import com.lawcloud.lawper.common.lucene.util.PropertiesHelper;
import com.lawcloud.lawper.service.IndexerService;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Paths;

@Service("indexerService")
public class IndexerServiceImpl implements IndexerService {

    public void create_index(String attachmentDir, String indexDir) {
        try {
            IndexWriter indexWriter = null;
            Directory dir = FSDirectory.open(Paths.get(indexDir));

            indexWriter = new IndexWriter(dir, new IndexWriterConfig(LuceneUtil.ANALYZER_CURRENT).setOpenMode(OpenMode.CREATE_OR_APPEND));
            File[] files = new File(attachmentDir).listFiles();
            for (int i = 0; i < files.length; i++) {
                Document doc = new Document();
                String[] str = files[i].getName().split("\\.");
                InputStream inputStream = null;

                if (str[1].equalsIgnoreCase("doc")) {
                    inputStream = new FileInputStream(files[i]);
                    doc.add(new TextField(LuceneUtil.FILE_CONTENT, Word.getContent(inputStream), Field.Store.YES));
                    doc.add(new StringField(LuceneUtil.FILE_TYPE, str[1], Field.Store.YES));
                } else if (str[1].equalsIgnoreCase("docx")) {
                    doc.add(new TextField(LuceneUtil.FILE_CONTENT, Word.getContent2007(files[i].toString()), Field.Store.YES));
                    doc.add(new StringField(LuceneUtil.FILE_TYPE, "doc", Field.Store.YES));
                } else if (str[1].equalsIgnoreCase("xls")) {
                    inputStream = new FileInputStream(files[i]);
                    doc.add(new TextField(LuceneUtil.FILE_CONTENT, Excel.getContent(inputStream), Field.Store.YES));
                    doc.add(new StringField(LuceneUtil.FILE_TYPE, str[1], Field.Store.YES));
                } else if (str[1].equalsIgnoreCase("xlsx")) {
                    inputStream = new FileInputStream(files[i]);
                    doc.add(new TextField(LuceneUtil.FILE_CONTENT, Excel.getContent2007(inputStream), Field.Store.YES));
                    doc.add(new StringField(LuceneUtil.FILE_TYPE, "xls", Field.Store.YES));
                } else if (str[1].equalsIgnoreCase("ppt")) {
                    inputStream = new FileInputStream(files[i]);
                    doc.add(new TextField(LuceneUtil.FILE_CONTENT, PowerPoint.getContent(inputStream), Field.Store.YES));
                    doc.add(new StringField(LuceneUtil.FILE_TYPE, str[1], Field.Store.YES));
                } else if (str[1].equalsIgnoreCase("pptx")) {
                    doc.add(new TextField(LuceneUtil.FILE_CONTENT, PowerPoint.getContent2007(files[i].toString()), Field.Store.YES));
                    doc.add(new StringField(LuceneUtil.FILE_TYPE, "ppt", Field.Store.YES));
                } else if (str[1].equalsIgnoreCase("pdf")) {
                    inputStream = new FileInputStream(files[i]);
                    doc.add(new TextField(LuceneUtil.FILE_CONTENT, PDF.getContent(inputStream), Field.Store.YES));
                    doc.add(new StringField(LuceneUtil.FILE_TYPE, str[1], Field.Store.YES));
                } else {
                    inputStream = new FileInputStream(files[i]);
                    doc.add(new TextField(LuceneUtil.FILE_CONTENT, Text.getContent(inputStream), Field.Store.YES));
                    doc.add(new StringField(LuceneUtil.FILE_TYPE, str[1], Field.Store.YES));
                }

                doc.add(new TextField(LuceneUtil.FILE_NAME, str[0], Field.Store.YES));

                doc.add(new StringField(LuceneUtil.FILE_PATH, files[i].getAbsolutePath(), Field.Store.YES));

                long long_date = files[i].lastModified();
                doc.add(new StringField(LuceneUtil.FILE_DATE, DateUtil.getDate(long_date), Field.Store.YES));
                indexWriter.addDocument(doc);
                indexWriter.commit();
            }
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
