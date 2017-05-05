package com.lawcloud.lawper.service;

import com.lawcloud.lawper.common.lucene.model.Attachment;

import java.util.List;

/**
 * Created by dell on 2017/5/5.
 */
public interface SearcherService {
    List<Attachment> do_search(String path, String keyword, String type, int start, int end);
    int getCount(String path, String keyword, String type);
}
