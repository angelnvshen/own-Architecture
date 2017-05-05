package com.lawcloud.lawper.service.impl;

import com.lawcloud.lawper.common.lucene.model.Attachment;
import com.lawcloud.lawper.common.lucene.util.LuceneUtil;
import com.lawcloud.lawper.common.util.AppCodeConstantUtil;
import com.lawcloud.lawper.service.SearcherService;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/5/5.
 */
@Service("searcherService")
public class SearcherServiceImpl implements SearcherService {

    @Autowired
    private AppCodeConstantUtil util;

    @Override
    public List<Attachment> do_search(String path, String keyword, String type, int start, int end) {
        List<Attachment> list = null;
        Attachment att = null;
        try {
            String IndexDir = util.getProperty("IndexDir");
            Directory dir = FSDirectory.open(Paths.get(IndexDir));
            DirectoryReader reader = DirectoryReader.open(dir);
            //TermEnum termEnum = reader.terms();
            IndexSearcher is = new IndexSearcher(reader);
            //is.setDefaultFieldSortScoring(true, false);
            Query query = null;
            if (type != "" && type != null) {
                String[] fields = {LuceneUtil.FILE_CONTENT, LuceneUtil.FILE_TYPE};
                String[] fields_value = {keyword, type};
                //MultiFieldQueryParser mquery = new MultiFieldQueryParser(LuceneUtil.VERSION_CURRENT, fields, LuceneUtil.ANALYZER_CURRENT);
                //QueryFilter qf = new QueryFilter(tq);
                query = MultiFieldQueryParser.parse(fields_value, fields, new BooleanClause.Occur[]{BooleanClause.Occur.MUST, BooleanClause.Occur.MUST}, LuceneUtil.ANALYZER_CURRENT_SMART);

            } else {
                QueryParser queryParser = new QueryParser(LuceneUtil.FILE_CONTENT, LuceneUtil.ANALYZER_CURRENT_SMART);
                query = queryParser.parse(keyword);
            }

            //QueryParser queryParser = new QueryParser(LuceneUtil.VERSION_CURRENT, LuceneUtil.FILE_CONTENT, LuceneUtil.ANALYZER_CURRENT);

            //Term tm = new Term(LuceneUtil.FILE_TYPE,type);
            //TermQuery tq = new TermQuery(tm);
            //Filter f = new Filter();
            TopDocs hits = is.search(query, 10000, Sort.RELEVANCE, true, false);
            SimpleHTMLFormatter shf = new SimpleHTMLFormatter("<em><strong>", "</strong></em>");
            Highlighter hl = new Highlighter(shf, new QueryScorer(query));
            //hl.setTextFragmenter(new SimpleFragmenter(Integer.MAX_VALUE));
            hl.setTextFragmenter(new SimpleFragmenter(200));

            //TopScoreDocCollector results = TopScoreDocCollector.create(11, false);
            //is.search(query, results);
            //TopDocs tds = results.topDocs(1, 10);
            //ScoreDoc[] sd = tds.scoreDocs;
            list = new ArrayList<Attachment>();
            for (int i = start; i < end && i < hits.scoreDocs.length; i++) {
                System.out.println("start: " + start + " end: " + end);
                att = new Attachment();
                ScoreDoc sdoc = hits.scoreDocs[i];
                Document doc = is.doc(sdoc.doc);
                System.out.println(doc.get(LuceneUtil.FILE_NAME));
                System.out.println(doc.get(LuceneUtil.FILE_TYPE));
                System.out.println(doc.get(LuceneUtil.FILE_PATH));
                System.out.println(doc.get(LuceneUtil.FILE_DATE));
                att.setFileid(sdoc.doc);
                att.setFilename(doc.get(LuceneUtil.FILE_NAME));
                att.setFiletype(doc.get(LuceneUtil.FILE_TYPE));

                String file_path = doc.get(LuceneUtil.FILE_PATH);
                file_path = file_path.replaceAll("\\\\", "/");
                att.setFilepath(file_path);

                att.setFiledate(doc.get(LuceneUtil.FILE_DATE));
                String str = hl.getBestFragment(LuceneUtil.ANALYZER_CURRENT_SMART, LuceneUtil.FILE_CONTENT, doc.get(LuceneUtil.FILE_CONTENT));
                att.setHitword(str);
                att.setFilescore(sdoc.score);
                list.add(att);

                // TokenStream tokenStream = LuceneUtil.ANALYZER_CURRENT.tokenStream(fieldName,new StringReader(text));

                //TermPositionVector tpv = (TermPositionVector)  IndexReader.getTermFreqVector(id, "content");
                //TokenStream tokenStream = TokenSources.getTokenStream(tpv);
                //tokenStream.startOffset();
                //tokenStream.next().endOffset();

                System.out.println(doc.get(LuceneUtil.FILE_CONTENT));
                System.out.println(doc.toString());
//                System.out.println(doc.getBoost());
                System.out.println(sdoc.toString());
                System.out.println("doc: " + sdoc.doc + "  score: " + sdoc.score + "  shardIndex: " + sdoc.shardIndex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InvalidTokenOffsetsException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getCount(String path, String keyword, String type) {
        TopDocs hits = null;
        try {
            String IndexDir = util.getProperty("IndexDir");
            Directory dir = FSDirectory.open(Paths.get(IndexDir));
            DirectoryReader reader = DirectoryReader.open(dir);
            IndexSearcher is = new IndexSearcher(reader);
//            is.setDefaultFieldSortScoring(true, false);
            Query query = null;
            if (type != "" && type != null) {
                String[] fields = {LuceneUtil.FILE_CONTENT, LuceneUtil.FILE_TYPE};
                String[] fields_value = {keyword, type};
                //MultiFieldQueryParser mquery = new MultiFieldQueryParser(LuceneUtil.VERSION_CURRENT, fields, LuceneUtil.ANALYZER_CURRENT);
                //QueryFilter qf = new QueryFilter(tq);
                query = MultiFieldQueryParser.parse(fields_value, fields, new BooleanClause.Occur[]{BooleanClause.Occur.MUST, BooleanClause.Occur.MUST}, LuceneUtil.ANALYZER_CURRENT_SMART);

            } else {
                QueryParser queryParser = new QueryParser(LuceneUtil.FILE_CONTENT, LuceneUtil.ANALYZER_CURRENT_SMART);
                query = queryParser.parse(keyword);
            }
            //QueryParser queryParser = new QueryParser(LuceneUtil.VERSION_CURRENT, LuceneUtil.FILE_CONTENT, LuceneUtil.ANALYZER_CURRENT);
            //Query query = queryParser.parse(keyword);
            hits = is.search(query, 10000, Sort.RELEVANCE, true, false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hits.scoreDocs.length;
    }
}
