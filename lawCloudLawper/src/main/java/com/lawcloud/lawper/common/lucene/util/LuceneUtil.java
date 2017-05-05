package com.lawcloud.lawper.common.lucene.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

public class LuceneUtil {

    public static final Analyzer ANALYZER_CURRENT = new IKAnalyzer();
    public static final Analyzer ANALYZER_CURRENT_SMART = new IKAnalyzer(true);

    public static final String FILE_NAME = "filename";
    public static final String FILE_TYPE = "filetype";
    public static final String FILE_PATH = "filepath";
    public static final String FILE_DATE = "filedate";
    public static final String FILE_CONTENT = "content";

    public static void print(Analyzer analyzer) throws IOException {
        StringReader reader = new StringReader(str);
        TokenStream tokenStream = analyzer.tokenStream("", reader);
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

    public static void main(String[] args) throws IOException {

        Analyzer analyzer = null;

        analyzer = new IKAnalyzer(true);
        LuceneUtil.print(analyzer);

        analyzer = new IKAnalyzer();
        LuceneUtil.print(analyzer);


		/*String file_path = "D:\\tmp\\lucene\\AttachmentDir\\中华人民共和国国籍法.txt";
        System.out.println(file_path);

		file_path = file_path.replaceAll("\\\\", "/");

		System.out.println(file_path);*/
    }
}
