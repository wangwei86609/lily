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
	/**����ҳ�ŵ�Ĭ�ϴ�СΪ12.0f*/
	private static final float DEFAULT_SIZE=12.0f;
	/**����ҳ�ŵĶ��뷽ʽΪ�м����*/
	public static final int CENTER=Element.ALIGN_CENTER;
	/**����ҳ�ŵĶ��뷽ʽΪ�����*/
	public static final int LEFT=Element.ALIGN_LEFT;
	/**����ҳ�ŵĶ��뷽ʽΪ�Ҷ���*/
	public static final int RIGHT=Element.ALIGN_RIGHT;
	/** ҳ����ʽ��Page 1 of 10 */
	public static final String STYLE_PAGE_NUMBER_N_OFTOTAL = "Page #N of #T";
	/** ҳ����ʽ��- 1 - */
	public static final String STYLE_PAGE_NUMBER_N = "- #N -";
	/** ҳ����ʽ���� 1 ҳ */
	public static final String STYLE_PAGE_NUMBER_N_CH = "�� #N ҳ";
	/** ҳ����ʽ���� 1 �� */
	public static final String STYLE_PAGE_NUMBER_N_CH2 = "�� #N ��";
    /** ҳ����ʽ���� 1 ҳ���� 10 ҳ */
    public static final String STYLE_PAGE_NUMBER_N_OFTOTAL_CH = "�� #N ҳ���� #T ҳ";
	/** ������ʽ��� ��ǰҳ�� */
	public static final String SIGN_PAGE_NUMBER = "#N";
	/** ������ʽ��� ��ҳ�� */
	public static final String SIGN_TOTAL_NUMBER = "#T";
	Calendar cal=Calendar.getInstance();
	
	/**ҳ������*/
	private String text=" ";//cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
	/**ҳ���ı����뷽ʽ*/
	private int textAlign=RIGHT;
	
	private int pageNumberAlign=Element.ALIGN_CENTER;

	/** �Ƿ���� */
	private boolean bold=false;
	/** �����С */
	private float fontSize = DEFAULT_SIZE;
	/**������ɫ*/
	private Color color=Color.BLACK;
	/**ҳü����ɫ*/
	private Color backGroundColor=Color.white;
	/**�Ƿ���ʾҳ�� Ĭ����ʾҳ��*/
	private boolean showPageNumber=false;
	/**�Ƿ���ʾ�ܵ�ҳ�� Ĭ����ʾ��ҳ��*/
	private boolean showTotalNumber=false;
	/**�Ƿ�����ҳ�� Ĭ������*/
	private boolean hasFooter =false;
	/**ҳ����ʽ*/
	private String pageNumberStyle=STYLE_PAGE_NUMBER_N_CH;
	/**
	 * ����ҳ��
	 * @return
	 */
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
