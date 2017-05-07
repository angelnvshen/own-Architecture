package com.lawcloud.lawper.common.lucene.util;

import com.lawcloud.lawper.common.lucene.extractor.Text;
import com.lawcloud.lawper.common.util.analyzer.MyIkAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.file.Paths;

public class LuceneUtil {

    public static final Analyzer ANALYZER_CURRENT = new MyIkAnalyzer();
    public static final Analyzer ANALYZER_CURRENT_SMART = new MyIkAnalyzer(true);

    public static final String FILE_NAME = "filename";
    public static final String FILE_TYPE = "filetype";
    public static final String FILE_PATH = "filepath";
    public static final String FILE_DATE = "filedate";
    public static final String FILE_CONTENT = "content";

    public static void print(Analyzer analyzer, String parseStr) throws IOException {
        StringReader reader = new StringReader(parseStr);
        TokenStream tokenStream = analyzer.tokenStream("content", reader);
        tokenStream.reset();

        CharTermAttribute termAttribute = tokenStream
                .getAttribute(CharTermAttribute.class);

        System.out.println("分词技术：" + analyzer.getClass());
        while (tokenStream.incrementToken()) {
            System.out.print(termAttribute.toString() + "|");
        }
        System.out.println();
    }

    private static String str = "体质等方面全面发展";

    public static void main(String[] args) throws IOException, ParseException {

        String fileName =  "C:\\Users\\CHANEL\\Desktop\\tmp\\lucene\\AttachmentDir\\第二回　江南七怪.txt";
        InputStream inputStream = new FileInputStream(fileName);
        String str = Text.getContent(inputStream);

        LuceneUtil.print(new CJKAnalyzer(), str);
        System.out.println(" ==================== ");
        LuceneUtil.print(new MyIkAnalyzer(), str);

        /*Analyzer analyzer = null;
        analyzer = new MyIkAnalyzer();
        LuceneUtil.print(analyzer, str);*/

        Directory dir = new RAMDirectory();

        /*Document doc = new Document();
        doc.add(new StringField(LuceneUtil.FILE_CONTENT, str, StoredField.Store.YES));

        IndexWriterConfig config = new IndexWriterConfig(LuceneUtil.ANALYZER_CURRENT_SMART);
        IndexWriter writer = new IndexWriter(dir, config);
        writer.addDocument(doc);
        writer.commit();

        String keyword = "勾引";
        DirectoryReader reader = DirectoryReader.open(dir);
        IndexSearcher is = new IndexSearcher(reader);
        QueryParser queryParser = new QueryParser(LuceneUtil.FILE_CONTENT, new CJKAnalyzer());
        Query query = queryParser.parse(keyword);
        TopDocs hits = is.search(query, 10000, Sort.RELEVANCE, true, false);
        for(ScoreDoc scoreDoc : hits.scoreDocs){
            int docId = scoreDoc.doc;
            Document doc_s = is.doc(docId);;
            System.out.println("content = " + doc.get("content"));
        }*/
    }
}
