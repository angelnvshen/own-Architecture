package com.lawcloud.lawper.common.lucene.extractor;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	public static String getContent(InputStream is){
		HSSFWorkbook wb=null;
		ExcelExtractor execl =null;
		try {
			wb = new HSSFWorkbook(is);
			execl = new ExcelExtractor(wb);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return execl.getText();
	}

	public static String getContent2007(InputStream is){
		StringBuffer content = new StringBuffer();
		
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb=null;
		try {
			xwb = new XSSFWorkbook(is);
		
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < xwb.getNumberOfSheets(); numSheet++) {
			XSSFSheet xSheet = xwb.getSheetAt(numSheet);
			if (xSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 0; rowNum <= xSheet.getLastRowNum(); rowNum++) {
				XSSFRow xRow = xSheet.getRow(rowNum);
				if (xRow == null) {
					continue;
				}
				// 循环列Cell
				for (int cellNum = 0; cellNum <= xRow.getLastCellNum(); cellNum++) {
					XSSFCell xCell = xRow.getCell(cellNum);
					if (xCell == null) {
						continue;
					}
					if (xCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
						content.append(xCell.getBooleanCellValue());
					} else if (xCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
						content.append(xCell.getNumericCellValue());
					} else {
						content.append(xCell.getStringCellValue());
					}
				}
			}
		}
		is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}

}
