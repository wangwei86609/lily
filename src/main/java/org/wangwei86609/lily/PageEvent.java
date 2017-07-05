package org.wangwei86609.lily;


import org.wangwei86609.lily.core.ChineseFont;
import org.wangwei86609.lily.core.HeaderText;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import jxl.Image;

/**
 * �Զ��� PDF �¼�
 * @author newapps
 * 2009-11-12
 */
public class PageEvent extends PdfPageEventHelper {
	/** An Image that goes in the header. */
    public Image headerImage;
    /** The Graphic state */
    public PdfGState gstate;
    /** page header */
	private PdfPTable table;
    /** footer �߶� */
    private float footerHeight;
    /** A template that will hold the total number of pages. */
    private PdfTemplate tpl;
    /** ҳ���������� */
    private BaseFont font;
    /** ������ҳ���Ŀո��壬�� openDocument ʱ���� */
    private String blankTextChars = "    ";
    /**ҳ��������С*/
    private float pageNumberSize;
    /** �����ʼ�� top margin ֵ */
    private float _topMargin;
    /** �����ʼ�� bottom margin ֵ */
    private float _bottomMargin;
    /** �Ƿ����� margin */
    private boolean resetMargin;
    /**ҳ����ʽ*/
    private String pageNumberStyle;
    /**ҳ��*/
    private PdfFooter footer;
    /**ҳü*/
    private PdfHeader header;
    /**ҳ����Ϣ*/
    private HeaderText headerText;
    /**����ҳ����Ϣ*/
	public void setFooter(PdfFooter footer) {
		this.footer = footer;
	}
	/**����ҳ����Ϣ*/
	public void setHeader(PdfHeader header) {
		this.header = header;
	}
	/**
	 * Ĭ�Ϲ��캯��
	 */
	public PageEvent() {}
	/**
	 * ͨ��ҳü��ҳ��������ö���
	 * @param footer ҳ��
	 * @param header ҳü
	 */
	public PageEvent(PdfFooter footer, PdfHeader header) {
		super();
		this.footer = footer;
		this.header = header;
	}
	/**
	 * ����ҳ�������С
	 * @param size
	 */
	public void setPageNumberSize(float size) {
		pageNumberSize = size;
		if (size > footerHeight) footerHeight = size;
	}		
//	/**
//	 * �����Ƿ�������һ��ҳͷ
//	 * @param skip
//	 */
//	public void setSkipFirstWrite(boolean skip) {
//		this.skipFirstWrite = skip;
//	}
//	/**
//	 * ����ҳ��
//	 * @param footerTexts
//	 */
//	public void setFooterText(List footerTexts) {
//		this.footerTexts = footerTexts;
//		if (footerTexts != null && footerTexts.size() > 0) {
//			footer.setHasFooter(true);
//			Iterator iter = footerTexts.iterator();
//			// �ҳ���������size
//			while (iter.hasNext()) {
//				PdfFooter text = (PdfFooter) iter.next();
//				if (text.getFontSize() > footerHeight)
//					footerHeight = text.getFontSize();
//			}
//			if (footerHeight < pageNumberSize)
//				footerHeight = pageNumberSize;
//		} else {
//			footer.setHasFooter(false);
//			footerHeight = 0.0f;
//		}
//	}
//	
	/**
	 * ����ҳü������
	 */
	public void setHeaderText(HeaderText headerText) {
		this.headerText = headerText;
	}
	/**
	 * ����ҳ����ʾ����ʽ
	 * @param style
	 */
	public void setPageNumberStyle(String style) {
		if (style != null & style.indexOf(PdfFooter.SIGN_PAGE_NUMBER) >= 0) {
			pageNumberStyle = style;
			footer.setShowTotalNumber((style.indexOf(PdfFooter.SIGN_TOTAL_NUMBER) > 0));
		}
	}
	/**
	 * �Ƿ����� margin
	 * @param reset
	 */
	void setResetMargin(boolean reset) {
		resetMargin = reset;
	}

	/**
	 * ���� margin
	 * @param document - com.lowagie.text.Document
	 */
	private void setMargin(Document document) {
		float leftMargin = document.leftMargin();
		float rightMargin = document.rightMargin();
		float topMargin = (table == null) ? this._topMargin : this._topMargin + table.getTotalHeight();
		float bottomMargin = this._bottomMargin + footerHeight;

		document.setMargins(leftMargin, rightMargin, topMargin, bottomMargin);
	}

	// ------------------------------------------ event implementation
	
	/**
	 * ҳ�����¼�
	 */
	@Override
    public void onEndPage(PdfWriter writer, Document document) {
        Rectangle page = document.getPageSize();
        PdfContentByte cb = writer.getDirectContent();
        if (footer.isShowPageNumber() && tpl != null) {
            cb.saveState();
            // compose the footer
            String text = this.pageNumberStyle.replaceAll(PdfFooter.SIGN_PAGE_NUMBER, String.valueOf(writer.getPageNumber()));
            int totalPagePos = -1; // ��ҳ����text�е�λ��
            if (footer.isShowTotalNumber()) {
                totalPagePos = text.indexOf(PdfFooter.SIGN_TOTAL_NUMBER);
                text = text.replaceAll(PdfFooter.SIGN_TOTAL_NUMBER, blankTextChars);
            }
            // ����ռ�Ŀ��
            float textSize = font.getWidthPoint(text, pageNumberSize);
            // Y ����
            float textBase = document.bottomMargin() - footerHeight;
            cb.beginText();
            cb.setFontAndSize(font, pageNumberSize);

            // ���� X ����
            float x = 0.0f;
            if (footer.getPageNumberAlign() == Element.ALIGN_CENTER)
                x = (page.getWidth() - textSize) / 2;
            else if (footer.getPageNumberAlign() == Element.ALIGN_LEFT)
                x = document.left();
            else
                x = document.right() - textSize - font.getWidthPoint("00", pageNumberSize);
            cb.setTextMatrix(x, textBase);
            cb.showText(text);
            cb.endText();
            if (footer.isShowTotalNumber() == true) {
                textSize = font.getWidthPoint(text.substring(0, totalPagePos), pageNumberSize);
                cb.addTemplate(tpl, x + textSize, textBase);
            }
            float len = font.getWidthPoint(text, pageNumberSize);
            cb.addTemplate(tpl, x + len, textBase);
			cb.beginText();
			cb.setFontAndSize(font, 8);
			cb.setTextMatrix(x, page.getHeight()-pageNumberSize);
			cb.showText(header.getText());
			cb.endText();
            cb.restoreState();
        }

        if (footer.isHasFooter()) {
        	// ��ʾ page footer
        	cb.saveState();
        	float x = 0.0f;
        	float textBase = document.bottomMargin() - footerHeight;
//        	for (int i=0; i < footerTexts.size(); i++) {
//        		PdfFooter text = (PdfFooter) footerTexts.get(i);
        	if(footer!=null){	
        		cb.beginText();
        		cb.setFontAndSize(font, footer.getFontSize());
        		if (footer.isBold())
        			cb.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE);
        		else
        			cb.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL);
        		if (footer.getTextAlign() == Element.ALIGN_CENTER) {
        			x = document.getPageSize().getWidth() / 2;
        		} else if (footer.getTextAlign() == Element.ALIGN_LEFT) {
        			x = document.left();
        		} else {
        			x = document.right();
        		}
        		cb.showTextAligned(footer.getTextAlign(), footer.getText(), x, textBase, 0.0f);
        		cb.endText();
        	
        	
    		cb.restoreState();
        	}
        }
        // reset skipFirstWrite
    //    skipFirstWrite = false;
        
		if (resetMargin) {
			// ���� margin
			setMargin(document);
			resetMargin = false;
		}
		// not empty document
	}
	
	/**
	 * ���ĵ��¼�
	 */
	@Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        try {
            table = new PdfPTable(3);
            table.setSpacingAfter(50.0f);
            table.setWidthPercentage(100.0f);
            //table.getDefaultCell().setBackgroundColor(Color.white);
            table.getDefaultCell().setBorder(0);
            table.getDefaultCell().setHorizontalAlignment(header.getAlign());
            if(headerText!=null){
            	if(headerText.getHeaderText1()==null){
            		table.addCell(" ");
            	}else{
            	table.addCell(headerText.getHeaderText1());
            	}
            	if(headerText.getHeaderText2()==null){
            		table.addCell(" ");
            	}else{
            	table.addCell(headerText.getHeaderText2());
            	}
            	if(headerText.getHeaderText3()==null){
            		table.addCell(" ");
            	}else{
            	table.addCell(headerText.getHeaderText3());
            	}
            }
            gstate = new PdfGState();
            gstate.setFillOpacity(0.3f);
            gstate.setStrokeOpacity(0.3f);
            // initialization of the template
            tpl = writer.getDirectContent().createTemplate(100, 100);
            tpl.setBoundingBox(new Rectangle(-20, -20, 100, 100));
        	
        	if (footer.isShowPageNumber()) {
	            // initialization of the template
	            tpl = writer.getDirectContent().createTemplate(100, 100);
	            tpl.setBoundingBox(new Rectangle(-20, -20, 100, 100));
	            // initialization of the font
	            if (ChineseFont.containsChinese(pageNumberStyle) && ChineseFont.BASE_CHINESE_FONT != null)
	            	font = ChineseFont.BASE_CHINESE_FONT;
	            else
	            	font = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, false);

                float size = font.getWidthPoint("000", this.pageNumberSize);
                float blankUnitSize = font.getWidthPoint(" ", this.pageNumberSize);
                int needSpaceChars = Math.round(size / blankUnitSize);
                blankTextChars = "";
                for (int i = 0; i < needSpaceChars; i++)
                    blankTextChars += " ";
        	}
            // �����ʼ�� top��bottom margin
        	_topMargin = document.topMargin();
        	_bottomMargin = document.bottomMargin();
        }
        catch(Exception e) {
            throw new ExceptionConverter(e);
        }
	}

	/**
	 * Start page �¼�
	 */
	@Override
    public void onStartPage(PdfWriter writer, Document document) {
        if (table!=null) {
        	Rectangle page = document.getPageSize();
        	table.setTotalWidth(page.getWidth()-document.leftMargin()-document.rightMargin());
        	table.writeSelectedRows(0, -1, document.leftMargin(),
        		page.getHeight() - document.topMargin() + table.getTotalHeight(),
        		writer.getDirectContent());
        }
	}

	/**
	 * Close document �¼�
	 */
    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
    	if (footer.isShowTotalNumber() && footer.isShowPageNumber() && tpl != null) {
	        tpl.beginText();
	        tpl.setFontAndSize(font, pageNumberSize);
            // ����λ�� (x ����)
            float x = 0.0f;
            int totalPage = writer.getPageNumber() - 1;
            if (totalPage < 10) // 1 λ��
                x += font.getWidthPoint("00", pageNumberSize) / 2;
            else if (totalPage < 100) // 2 λ��
                x += font.getWidthPoint("0", pageNumberSize) / 2;
            else if (totalPage > 1000) // 4 λ�������
                x -= font.getWidthPoint("0", pageNumberSize) / 2;
            
	        tpl.setTextMatrix(x, 0);
	        tpl.showText(""+totalPage);
	        tpl.endText();
    	}
     }
}
