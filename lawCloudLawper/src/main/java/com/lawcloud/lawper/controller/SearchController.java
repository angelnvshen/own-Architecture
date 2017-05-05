package com.lawcloud.lawper.controller;

import com.lawcloud.lawper.common.lucene.model.Attachment;
import com.lawcloud.lawper.common.lucene.util.PagingInfo;
import com.lawcloud.lawper.common.util.AppCodeConstantUtil;
import com.lawcloud.lawper.service.IndexerService;
import com.lawcloud.lawper.service.SearcherService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/5/5.
 */
@RequestMapping("search")
@Controller
public class SearchController {
    @Autowired
    private IndexerService indexerService;

    @Autowired
    private SearcherService searcherService;

    @Autowired
    private AppCodeConstantUtil util;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {

        return "/sou/index";
    }

    @ResponseBody
    @RequestMapping(value = "do_index", method = RequestMethod.GET)
    public String do_index(HttpServletRequest request) {
        System.out.println("$.ajax()执行成功！！！");
        try {
            String attachmentDir = util.getProperty("AttachmentDir");
            String indexDir = util.getProperty("IndexDir");

            if(StringUtils.isEmpty(attachmentDir) || StringUtils.isEmpty(indexDir)){
                throw new Exception("attachmentDir or indexDir 为空");
            }
            indexerService.create_index(attachmentDir, indexDir);
        } catch (Exception e) {
            return "create index error : " + e;
        }
        return "create index success";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(HttpServletRequest request, Map<String, Object> model) throws UnsupportedEncodingException {

        //int totle = iaDAO.getCountAgent(1);
        //PagingInfo pl = new PagingInfo(totle, 15, page);
        // List<Agentdomain> agentdomain = iaDAO.getListAgent(pl.getMin_t(),15,1);
//        String keyword = new String(request.getParameter("keyword").getBytes("ISO-8859-1"), "utf-8");
    	String keyword = request.getParameter("keyword");
        String page = request.getParameter("page");
        String type = request.getParameter("type");
        System.out.println(":|" + keyword.trim() + "|:");
        if (!keyword.trim().equals("") && !keyword.trim().equals(null)) {
            String path = request.getRealPath("/") + "WEB-INF/sou.properties";
            System.out.println(path);
            //indexerService.create_index(path);
            int totle = searcherService.getCount(path, keyword, type);
            PagingInfo pl = new PagingInfo(totle, 10, Integer.parseInt(page));
            List<Attachment> list = searcherService.do_search(path, keyword, type, pl.getMin_t(), pl.getMax_t());
            System.out.println("pl.getMin_t(): " + pl.getMin_t() + " pl.getMax_t(): " + pl.getMax_t());
            model.put("attachment", list);
            model.put("keyword", keyword);
            model.put("type", type);
            model.put("page", pl);
            return "/sou/search";
        } else {
            return "redirect:/index.page";
        }

    }
}
