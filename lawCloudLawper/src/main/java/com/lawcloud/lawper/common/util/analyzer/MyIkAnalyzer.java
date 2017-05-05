package com.lawcloud.lawper.common.util.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.util.IOUtils;

import java.io.Reader;
import java.io.StringReader;

public class MyIkAnalyzer extends Analyzer {

    private boolean useSmart;

    public MyIkAnalyzer() {
        this.useSmart = false;
    }

    public MyIkAnalyzer(boolean useSmart) {
        this.useSmart = useSmart;
    }

    @Override
    protected TokenStreamComponents createComponents(String arg0) {
        Reader reader=null;
        try{
            reader=new StringReader(arg0);
            // useSmart : true 用智能分词 ，false细粒度
            MyIKTokenizer it = new MyIKTokenizer(reader, useSmart);
            return new TokenStreamComponents(it);
        }finally {
            IOUtils.closeWhileHandlingException(reader);
        }
    }

}