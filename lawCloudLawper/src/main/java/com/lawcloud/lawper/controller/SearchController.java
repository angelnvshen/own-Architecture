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
@RequestMapping("lucene")
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

        return "/lucene/index";
    }

    @ResponseBody
    @RequestMapping(value = "do_index", method = RequestMethod.GET)
    public String do_index(HttpServletRequest request) {
        System.out.println("$.ajax()执行成功！！！");
        try {
            String attachmentDir = util.getProperty("AttachmentDir");
            String indexDir = util.getProperty("IndexDir");

            if (StringUtils.isEmpty(attachmentDir) || StringUtils.isEmpty(indexDir)) {
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
        String keyword = new String(request.getParameter("keyword").getBytes("ISO-8859-1"), "utf-8");
//        String keyword = request.getParameter("keyword");
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
            return "/lucene/search";
        } else {
            return "redirect:/lucene/index";
        }
    }

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws Exception {

        response.setContentType("text/html");
        javax.servlet.ServletOutputStream out = response.getOutputStream();
        //String filepath = request.getRealPath("/") + "uploadfile/";
        System.out.println(request.getParameter("filepath"));
        String filepath = new String(request.getParameter("filepath").getBytes("ISO8859-1"), "utf-8").toString();//request.getParameter("filepath");//
        String filename = new String(request.getParameter("filename").getBytes("ISO8859-1"), "utf-8").toString();//request.getParameter("filename");//
        System.out.println("DownloadFile filepath:" + filepath);
        System.out.println("DownloadFile filepath:" + filename);
        //System.out.println("DownloadFile filepath:" + request.getParameter("filename"));
        //System.out.println("DownloadFile filename:" + filepath.lastIndexOf("\\"));

        //int b = filepath.lastIndexOf("\\");
        //filepath.substring(b);
        //System.out.println("DownloadFile filename:" + filepath.substring(b));
        java.io.File file = new java.io.File(filepath);
        if (!file.exists()) {
            System.out.println(file.getAbsolutePath() + " 文件不存在!");
            return;
        }
        // 读取文件流
        java.io.FileInputStream fileInputStream = new java.io.FileInputStream(file);
        // 下载文件
        // 设置响应头和下载保存的文件名
        if (filename != null && filename.length() > 0) {
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1") + "");
            if (fileInputStream != null) {
                int filelen = fileInputStream.available();
                // 文件太大时内存不能一次读出,要循环
                byte a[] = new byte[filelen];
                fileInputStream.read(a);
                out.write(a);
            }
            fileInputStream.close();
            out.close();
        }
    }
}
