package org.wangwei86609.lily;

import com.itextpdf.text.Rectangle;

public class PageSetting {
	private PdfFooter footer=new PdfFooter();
	private PdfHeader header=new PdfHeader();
	private Rectangle pageSize=PdfPageSize.A4;
	public PdfFooter getFooter() {
		return footer;
	}
	public void setFooter(PdfFooter footer) {
		this.footer = footer;
	}
	public PdfHeader getHeader() {
		return header;
	}
	public void setHeader(PdfHeader header) {
		this.header = header;
	}
	public Rectangle getPageSize() {
		return pageSize;
	}
	public void setPageSize(Rectangle pageSize) {
		this.pageSize = pageSize;
	}

	
}
