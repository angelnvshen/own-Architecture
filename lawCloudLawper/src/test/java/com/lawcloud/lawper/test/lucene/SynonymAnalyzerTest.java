package com.lawcloud.lawper.test.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SynonymAnalyzerTest {

    Directory dir;

    @Before
    public void setUp() throws Exception {
        dir = new RAMDirectory();
        IndexWriterConfig conf = new IndexWriterConfig(new SynonymsAnalyzer("classpath:synonyms.txt"));
        IndexWriter writer = new IndexWriter(dir, conf);
        Document doc = new Document();
        doc.add(new TextField("content", "我来自中国广州", Field.Store.YES));
        writer.addDocument(doc);
        writer.close();
    }

    @Test
    public void test1() throws IOException {
        Term term = new Term("content", "天朝");
        Query query = new TermQuery(term);
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(query, 10);
        assertEquals(1, docs.totalHits);
    }

    @Test
    public void test2() throws IOException {
        Term term = new Term("content", "俺");
        Query query = new TermQuery(term);
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(query, 10);
        assertEquals(1, docs.totalHits);
    }

    @Test
    public void test3() throws IOException {
        Term term = new Term("content", "五羊城");
        Query query = new TermQuery(term);
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(query, 10);
        assertEquals(1, docs.totalHits);
    }
}