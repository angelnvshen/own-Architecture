package biz.sou.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Download extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			response.setHeader("Content-Disposition", "attachment; filename="+ new String(filename.getBytes("gb2312"),"ISO8859-1")+"");
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
