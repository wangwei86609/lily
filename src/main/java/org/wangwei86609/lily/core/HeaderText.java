package org.wangwei86609.lily.core;

import com.itextpdf.text.pdf.PdfPCell;


public class HeaderText { 

	private PdfPCell headerText1;

	private PdfPCell headerText2;

	private PdfPCell headerText3;

	public PdfPCell getHeaderText1() {
		return headerText1;
	}

	public PdfPCell getHeaderText2() {
		return headerText2;
	}

	public void setHeaderText2(PdfPCell headerText2) {
		headerText2.setBorder(0);
		this.headerText2 = headerText2;
	}

	public PdfPCell getHeaderText3() {
		return headerText3;
	}

	public void setHeaderText3(PdfPCell headerText3) {
		headerText3.setBorder(0);
		this.headerText3 = headerText3;
	}

	public void setHeaderText1(PdfPCell headerText1) {
		headerText1.setBorder(0);
		this.headerText1 = headerText1;
	}
	
	
	
}
