package org.wangwei86609.lily.core;

import com.itextpdf.text.pdf.PdfPCell;


/**
 * ��ȡҳ����Ϣ
 * @author newapps
 *	2009-11-12
 */
public class HeaderText { 
	/**��һ������*/
	private PdfPCell headerText1;
	/**�ڶ�������*/
	private PdfPCell headerText2;
	/**����������*/
	private PdfPCell headerText3;
	/**��õ�һ������*/
	public PdfPCell getHeaderText1() {
		return headerText1;
	}
	/**��õڶ�������*/
	public PdfPCell getHeaderText2() {
		return headerText2;
	}
	/**��õ���������*/
	public void setHeaderText2(PdfPCell headerText2) {
		headerText2.setBorder(0);
		this.headerText2 = headerText2;
	}
	/**���õ�һ������*/
	public PdfPCell getHeaderText3() {
		return headerText3;
	}
	/**���õ���������*/
	public void setHeaderText3(PdfPCell headerText3) {
		headerText3.setBorder(0);
		this.headerText3 = headerText3;
	}
	/**���õ���������*/
	public void setHeaderText1(PdfPCell headerText1) {
		headerText1.setBorder(0);
		this.headerText1 = headerText1;
	}
	
	
	
}
