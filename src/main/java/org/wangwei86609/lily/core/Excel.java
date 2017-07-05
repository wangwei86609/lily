package org.wangwei86609.lily.core;

import java.io.FileInputStream;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;


public class Excel {
	private Sheet[] sheets=null;
	private boolean isProtected=false;
	private Workbook wb=null;
	public Excel(){
	}
	public Sheet[] getSheets() {
		return sheets;
	}
	public boolean isProtected() {
		return isProtected;
	}

	public void readExcel(String sourceFilePath){
		InputStream is=null;
		try {
			is=new FileInputStream(sourceFilePath);
			wb=Workbook.getWorkbook(is);
			sheets=wb.getSheets();
			isProtected=wb.isProtected();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readExcelFromDB(InputStream is){
		Workbook wb=null;
		try {
			wb=Workbook.getWorkbook(is);
			sheets=wb.getSheets();
			isProtected=wb.isProtected();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
	}
	
	public void closeWorkbook(){
		if(wb!=null){
		    wb.close();
		}
	}
}
