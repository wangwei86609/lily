package org.wangwei86609.lily;

import java.awt.Color;
import java.util.Calendar;

import com.itextpdf.text.Element;
/**
 * ����ҳ��
 * @author newapps
 * 2009-11-12
 */
public class PdfFooter {
	private static final float DEFAULT_SIZE=12.0f;
	public static final int CENTER=Element.ALIGN_CENTER;
	public static final int LEFT=Element.ALIGN_LEFT;
	public static final int RIGHT=Element.ALIGN_RIGHT;
	public static final String STYLE_PAGE_NUMBER_N_OFTOTAL = "Page #N of #T";
	public static final String STYLE_PAGE_NUMBER_N = "- #N -";
	public static final String STYLE_PAGE_NUMBER_N_CH = "�� #N ҳ";
	public static final String STYLE_PAGE_NUMBER_N_CH2 = "�� #N ��";
    public static final String STYLE_PAGE_NUMBER_N_OFTOTAL_CH = "�� #N ҳ���� #T ҳ";
	public static final String SIGN_PAGE_NUMBER = "#N";
	public static final String SIGN_TOTAL_NUMBER = "#T";
	Calendar cal=Calendar.getInstance();
	
	private String text=" ";//cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
	private int textAlign=RIGHT;
	
	private int pageNumberAlign=Element.ALIGN_CENTER;

	private boolean bold=false;
	private float fontSize = DEFAULT_SIZE;
	private Color color=Color.BLACK;
	private Color backGroundColor=Color.white;
	private boolean showPageNumber=false;
	private boolean showTotalNumber=false;
	private boolean hasFooter =false;
	private String pageNumberStyle=STYLE_PAGE_NUMBER_N_CH;

	public String getPageNumberStyle() {
		return pageNumberStyle;
	}

	public void setPageNumberStyle(String pageNumberStyle) {
		this.pageNumberStyle = pageNumberStyle;
	}

	public boolean isHasFooter() {
		return hasFooter;
	}

	public void setHasFooter(boolean hasFooter) {
		this.hasFooter = hasFooter;
	}

	public boolean isShowTotalNumber() {
		return showTotalNumber;
	}

	public void setShowTotalNumber(boolean showTotalNumber) {
		this.showTotalNumber = showTotalNumber;
	}

	public boolean isShowPageNumber() {
		return showPageNumber;
	}

	public void setShowPageNumber(boolean showPageNumber) {
		this.showPageNumber = showPageNumber;
	}

	public Color getBackGroundColor() {
		return backGroundColor;
	}

	public void setBackGroundColor(Color backGroundColor) {
		this.backGroundColor = backGroundColor;
	}

	public PdfFooter(){}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}


	public int getPageNumberAlign() {
		return pageNumberAlign;
	}

	public void setPageNumberAlign(int pageNumberAlign) {
		this.pageNumberAlign = pageNumberAlign;
	}

	public int getTextAlign() {
		return textAlign;
	}

	public void setTextAlign(int textAlign) {
		this.textAlign = textAlign;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public float getFontSize() {
		return fontSize;
	}

	public void setFontSize(float fontSize) {
		if(fontSize>4.0f&&fontSize<40.0f){
			this.fontSize = fontSize;
		}
		
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
	
}
