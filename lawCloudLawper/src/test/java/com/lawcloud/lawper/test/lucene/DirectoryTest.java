package com.lawcloud.lawper.test.lucene;

import com.lawcloud.lawper.common.util.analyzer.MyIkAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import javax.print.Doc;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by CHANEL on 2017/5/7.
 */
public class DirectoryTest {
    String filePath = "K:\\WorkSpace_Idea\\own-Architecture\\lawCloudLawper\\src\\test\\resources\\luceneDataSource\\第一回　风雪惊变.txt";

    String indexPath = "K:\\WorkSpace_Idea\\own-Architecture\\lawCloudLawper\\src\\test\\resources\\luceneIndex";

    Analyzer analyzer = new MyIkAnalyzer();

    @Test
    public void test1() throws IOException {
        Document document = File2DocumentUtil.fileToDocument(filePath);

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

//        Directory directory = FSDirectory.open(Paths.get(indexPath));

        Directory directory = new RAMDirectory();
        IndexWriter writer = new IndexWriter(directory, config);

        writer.addDocument(document);
        writer.close();

    }
    @Test
    public void test2() throws IOException {
        FSDirectory fsDirectory = FSDirectory.open(Paths.get(indexPath));


        //1:启动时读取
        Directory ramDirectory = new RAMDirectory(fsDirectory, new IOContext());

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        IndexWriter ramWriter = new IndexWriter(ramDirectory, config);
        // 添加 document
        Document document = File2DocumentUtil.fileToDocument(filePath);
        ramWriter.addDocument(document);
        ramWriter.close();

        //2:退出时保存
        IndexWriterConfig config2 = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter fsWriter = new IndexWriter(fsDirectory, config2);
        fsWriter.addIndexes(ramDirectory);
        fsWriter.close();

    }
}
