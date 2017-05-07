package com.lawcloud.lawper.common.lucene.extractor;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;

import java.io.*;

public class Text {

	public static String getContent(InputStream is) {
		StringBuffer content = new StringBuffer("");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				content.append(line + "\r");
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(content.toString());
		return content.toString();
	}

	public static void main(String[] args) throws IOException {
		String fileName = "C:\\Users\\CHANEL\\Desktop\\tmp\\lucene\\AttachmentDir\\第二回　江南七怪.txt";
		InputStream inputStream = new FileInputStream(fileName);
		String str = getContent(inputStream);
		System.out.println(str);

		System.out.println(" ============= ");
		Resource res1 = new FileSystemResource(fileName);
		EncodedResource encRes = new EncodedResource(res1,"UTF-8");

		System.out.println("res1:"+res1.getFilename());
		InputStream ins1 = res1.getInputStream();

		String str2 = FileCopyUtils.copyToString(encRes.getReader());
		System.out.println(str2);

		System.out.println(" ============= ");
		String str3 = getContent(ins1);
		System.out.println(str3);
	}
}
