package org.wangwei86609.lily;

import java.awt.Color;

import com.itextpdf.text.Element;
/**
 * ����ҳ��
 * @author newapps
 * 2009-11-12
 */
public class PdfHeader {
	/**����ҳü��Ĭ�ϴ�СΪ12.0f*/
	private static final float DEFAULT_SIZE=12.0f;
	/**����ҳü�Ķ��뷽ʽΪ�м����*/
	public static final int CENTER=Element.ALIGN_CENTER;
	/**����ҳü�Ķ��뷽ʽΪ�����*/
	public static final int LEFT=Element.ALIGN_LEFT;
	/**����ҳü�Ķ��뷽ʽΪ�Ҷ���*/
	public static final int RIGHT=Element.ALIGN_RIGHT;
	/**ҳü����*/
	private String text=" ";
	/**���뷽ʽ*/
	private int align=RIGHT;
	/** �Ƿ���� */
	private boolean bold=false;
	/** �����С */
	private float fontSize = DEFAULT_SIZE;
	/**������ɫ*/
	private Color color=Color.BLACK;
	/**ҳü����ɫ*/
	private Color backGroundColor=Color.white;
	
	public int getAlign() {
		return align;
	}
	public void setAlign(int align) {
		this.align = align;
	}
	public boolean isBold() {
		return bold;
	}
	public void setBold(boolean bold) {
		this.bold = bold;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
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
	public Color getBackGroundColor() {
		return backGroundColor;
	}
	public void setBackGroundColor(Color backGroundColor) {
		this.backGroundColor = backGroundColor;
	}
	
}
