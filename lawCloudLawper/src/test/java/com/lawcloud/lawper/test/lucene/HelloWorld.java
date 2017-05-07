package com.lawcloud.lawper.test.lucene;

import com.lawcloud.lawper.common.util.analyzer.MyIkAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by CHANEL on 2017/5/7.
 */
public class HelloWorld {

    String filePath = "K:\\WorkSpace_Idea\\own-Architecture\\lawCloudLawper\\src\\test\\resources\\luceneDataSource\\第一回　风雪惊变.txt";

//    String indexPath = "K:\\WorkSpace_Idea\\own-Architecture\\lawCloudLawper\\src\\test\\resources\\luceneIndex";
    String indexPath = "C:\\Users\\CHANEL\\Desktop\\tmp\\lucene\\IndexDir";

    Analyzer analyzer = new MyIkAnalyzer();

    @Test
    public void createIndex() throws IOException {

        //1:将文本转为document
        Document doc = File2DocumentUtil.fileToDocument(filePath);

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        Directory directory = FSDirectory.open(Paths.get(indexPath));
        IndexWriter writer = new IndexWriter(directory, config);

        //2:添加文档
        writer.addDocument(doc);
        writer.commit();
        writer.close();
        directory.close();
    }

    @Test
    public void search() throws IOException, ParseException {
        String queryString = "光鲜唬人";

        //1 : 把要搜索的文本解析为query
        String[] fields = {"name", "content"};
        QueryParser queryParser = new MultiFieldQueryParser(fields, analyzer);
        Query query = queryParser.parse(queryString);

        System.out.println(query);
        //2：进行查询
        Directory directory = FSDirectory.open(Paths.get(indexPath));
        DirectoryReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader); //new IndexSearcher();
        TopDocs topDocs = searcher.search(query, 1000);

        //3:打印结果
        System.out.println("总共有【" + topDocs.totalHits + "】条匹配结果");
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            int docId = scoreDoc.doc;//文档内部编号
            Document doc = searcher.doc(docId);//根据编号取出文档
            File2DocumentUtil.printDocumentInfo(doc);
        }
        reader.close();
    }
}
