package org.wangwei86609.lily.core;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.wangwei86609.lily.PdfPageSize;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class WriterPdf {
	protected Document document=null;
	public Rectangle pageSize=PdfPageSize.A4;
	protected PdfWriter writer=null;
	public Rectangle getPageSize() {
		return pageSize;
	}
	public void setPageSize(Rectangle pageSize) {
		this.pageSize = pageSize;
	}

	public WriterPdf(String destFilePath){
		document=new Document(null,20,20,60,30);
		try {
			FileOutputStream fos=new FileOutputStream(destFilePath);
			writer=PdfWriter.getInstance(document,fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public WriterPdf(OutputStream output){
		try {
			document=new Document(null,20,20,60,30);
			writer=PdfWriter.getInstance(document,output);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public void CloseDocument() throws Exception{
		try {
			if(document!=null){
			   document.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
