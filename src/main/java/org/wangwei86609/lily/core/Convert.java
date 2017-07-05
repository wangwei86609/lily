/*package org.wangwei86609.lily.core;

import java.awt.Color;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wangwei86609.lily.PageEvent;

import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;

import jxl.Cell;
import jxl.CellType;
import jxl.Image;
import jxl.Range;
import jxl.Sheet;
import jxl.format.Alignment;
import jxl.format.BoldStyle;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.Font;
import jxl.format.RGB;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;


public class Convert extends WriterPdf{
	private Table table=null;
	private boolean noEmptyBorder = false;
	private Sheet[] sheets=null;
	private Excel excel=null;
	private HeaderText header;
	private SheetImage sheetImage;
	public HeaderText getHeader() {
		return header;
	}

	public Convert(String filePath,OutputStream output) {
		super(output);
		readExcel(filePath);
	}

	public Convert(String filePath,String destFilePath){
		super(destFilePath);
		readExcel(filePath);
	}

	public Convert(InputStream input,OutputStream output){
		super(output);
		readExcel(input);
	}

	private void readExcel(Object obj){
		excel=new Excel();
		if(obj instanceof String){
			String filePath=(String)obj;
		    excel.readExcel(filePath);
		}else if(obj instanceof InputStream ){
			InputStream input=(InputStream)obj;
		    excel.readExcelFromDB(input);
		}
		sheets=excel.getSheets();
		int length=sheets.length-1;
		ExcelSheet image_Sheet=new ExcelSheet(sheets[length]);
		List<Image> imageList=image_Sheet.getImages();
		sheetImage=new SheetImage();
		sheetImage.setImage(imageList);
		try {
			header=readHeader(sheets[length]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*//**
	 * ת���ĺ��ķ���
	 * @param pageEvent ҳ���¼�
	 * @throws Exception
	 *//*
	public void convert(PageEvent pageEvent)throws Exception{
		noEmptyBorder = true;
		writer.setPageEvent(pageEvent);
		document.setPageSize(pageSize);
		document.open();
		System.out.println("->document�Ѿ���");
		if(sheets==null||sheets.length<0){
			return;
		}
		for(int i=0;i<sheets.length-1;i++){
			int colswidth[]=colsWidth(sheets[i]);
			int rows=sheets[i].getRows();
			int cols=sheets[i].getColumns();
			Map<Integer,String> merge=new HashMap<Integer,String>();
			List<Integer> subs=new ArrayList<Integer>();
			Range[] range=sheets[i].getMergedCells();
			for(int k=0;k<range.length;k++){
				int row=range[k].getTopLeft().getRow();
				int col=range[k].getTopLeft().getColumn();
				int index=row*cols+col+1;
				int r=range[k].getBottomRight().getRow()-range[k].getTopLeft().getRow()+1;
				int c=range[k].getBottomRight().getColumn()-range[k].getTopLeft().getColumn()+1;
				for(int m=row;m<=range[k].getBottomRight().getRow();m++){
					for(int n=col;n<=range[k].getBottomRight().getColumn();n++){
						if(m!=row||n!=col){
							int xy=m*cols+n+1;
							subs.add(xy);
						}
					}
				}
				
				String key=r+","+c;
				merge.put(index,key);
			}
			if(cols>0){
				List<Cell> cells=new ArrayList<Cell>();
				table=new Table(cols);
				table.setWidths(colswidth);
				table.setPadding(2.0f);
				table.setSpacing(0.0f);
				table.setWidth(100.0f);
				table.setBorder(0);
				table.setOffset(30.0f);
				cells=getSheetCell(sheets[i],rows,cols);
				for(int p=0;p<cells.size();p++){
				if(!findIndex(p,subs)){
					boolean mergerow=false;
					if(merge.containsKey(p+1)){
						mergerow=true;
						com.lowagie.text.Cell pcell=null;
						Phrase phrase = null;
					    Cell jxlcell=cells.get(p);
					    CellFormat format = jxlcell.getCellFormat();
					    Font font = null;
			            if(format != null && format.getFont() != null) {
						font = convertFont(format.getFont());
					    }else{
						font = new Font(Font.COURIER, 10.0f, Font.NORMAL, Color.BLACK);
					    }
						String content=jxlcell.getContents();
						String key=merge.get(p+1);
						String []s=key.split(",");
						int r=Integer.parseInt(s[0]);
						int c=Integer.parseInt(s[1]);
						phrase=new Phrase(content,font);
					    pcell=new com.itextpdf.text.Cell();
						pcell.addElement(phrase);
						if(r>1){
						pcell.setRowspan(r);
						}
						if(c>1){
							pcell.setColspan(c);
						}
						transferFormat(pcell, jxlcell,p,cols,cells);
						pcell.setBorderWidthRight(2.0f);
						table.addCell(pcell);
					}else{
						com.lowagie.text.Cell pcell=null;
						Phrase phrase = null;
					    Cell jxlcell=cells.get(p);
					    CellFormat format = jxlcell.getCellFormat();
					    Font font = null;
			            if(format != null && format.getFont() != null) {
						font = convertFont(format.getFont());
					    }else{
						font = new Font(Font.COURIER, 10.0f, Font.NORMAL, Color.BLACK);
					    }
			            String content=jxlcell.getContents();
						phrase=new Phrase(content,font);
					    pcell=new com.lowagie.text.Cell();
						pcell.addElement(phrase);
						transferFormat(pcell, jxlcell,p,cols,cells);
						table.addCell(pcell);
//			            } 
					}
				}
			}
				document.add(table);
				cells.clear();
			}
		
		}
		CloseDocument();
		excel.closeWorkbook();
	}

	private int[] colsWidth(Sheet sheet){
		int width[]=new int[sheet.getColumns()];
		for(int i=0;i<width.length;i++){
			width[i]=sheet.getColumnView(i).getSize();
		}
		return width;
	}

	private List<Cell> getSheetCell(Sheet sheet,int rows,int cols){
		List<Cell> cells=new ArrayList<Cell>();
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				cells.add(sheet.getCell(j,i));
			}
		}
		return cells;
	}

	private boolean findIndex(int i,List<Integer> subs){
		for(Integer n:subs){
			if(n==i+1){
				return true;
			}
		}
		return false;
	}

	private com.lowagie.text.Font convertFont(jxl.format.Font f) {
		if (f == null || f.getName() == null)
			return FontFactory.getFont(FontFactory.COURIER, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

		int fontStyle = convertFontStyle(f);
		com.lowagie.text.Font font = null;
		Color fontColor = convertColour(f.getColour(), Color.BLACK);
		if (ChineseFont.BASE_CHINESE_FONT != null && ChineseFont.containsChinese(f.getName())) {
			font = new Font(ChineseFont.BASE_CHINESE_FONT, f.getPointSize(), fontStyle, fontColor);
		} else {
			String s = f.getName().toLowerCase();
			int fontFamily;
			if (s.indexOf("courier") >= 0) //"courier new".equals(s) || "courier".equals(s))
				fontFamily = Font.COURIER;
			else if (s.indexOf("times") >= 0)
				fontFamily = Font.TIMES_ROMAN;
			else
				fontFamily = Font.HELVETICA;
		
			font = new Font(fontFamily, f.getPointSize(), fontStyle, fontColor);

		}

		return font;
	}

	private Color convertColour(Colour c, Color defaultColor) {
		if (defaultColor == null)
			defaultColor = Color.WHITE;
		
		if (c == null)
			return defaultColor;

        if (c == Colour.AUTOMATIC) 
        	return Color.BLACK;
           // return new Color(Colour.AUTOMATIC.getDefaultRGB().getRed(),Colour.AUTOMATIC.getDefaultRGB().getGreen(),Colour.AUTOMATIC.getDefaultRGB().getBlue());
        else if (c == Colour.DEFAULT_BACKGROUND)
        	return Color.white;
          //  return new Color(Colour.DEFAULT_BACKGROUND.getDefaultRGB().getRed(),Colour.DEFAULT_BACKGROUND.getDefaultRGB().getGreen(),Colour.DEFAULT_BACKGROUND.getDefaultRGB().getBlue());
        
		RGB rgb = c.getDefaultRGB();
		return new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
	}

	private int convertFontStyle(jxl.format.Font font) {

		int result = com.lowagie.text.Font.NORMAL;
		if (font.isItalic())
			result |= com.lowagie.text.Font.ITALIC;
		
		if (font.isStruckout())
			result |= com.lowagie.text.Font.STRIKETHRU;
		
		if (font.getBoldWeight() == BoldStyle.BOLD.getValue())
			result |= com.lowagie.text.Font.BOLD;
		
		if (font.getUnderlineStyle() != null) {
			// �»���
			UnderlineStyle style = font.getUnderlineStyle();
			if (style.getValue() != UnderlineStyle.NO_UNDERLINE.getValue())
				result |= com.lowagie.text.Font.UNDERLINE;
		}
		return result;
	}

	private void transferFormat2(PdfPCell pcell,Cell cell){
        jxl.format.CellFormat format = cell.getCellFormat();
        if (format != null) {

            pcell.setHorizontalAlignment(convertAlignment(format.getAlignment(), cell.getType()));

            pcell.setVerticalAlignment(convertVerticalAlignment(format.getVerticalAlignment()));
           // pcell.setBorderWidthBottom(1.0f);
    		//pcell.setBorderWidthRight(1.0f);

            BorderLineStyle lineStyle = null;

                lineStyle = format.getBorderLine(jxl.format.Border.BOTTOM);
                if (lineStyle.getValue() == BorderLineStyle.NONE.getValue()){
                    pcell.setBorderColorBottom(Color.WHITE);
                    pcell.setBorderWidthBottom(0.0f);
                }
                else{
                    pcell.setBorderColorBottom(convertColour(format.getBorderColour(jxl.format.Border.BOTTOM), Color.BLACK));
                    pcell.setBorderWidthBottom(convertBorderStyle(lineStyle));
                }
//            lineStyle = format.getBorderLine(jxl.format.Border.TOP);
//           
//            if (lineStyle.getValue() == BorderLineStyle.NONE.getValue()){
//                pcell.setBorderColorTop(Color.WHITE);
//                pcell.setBorderWidthTop(0.0f);
//            }
//            else{
//                pcell.setBorderColorTop(convertColour(format.getBorderColour(jxl.format.Border.TOP), Color.BLACK));
//                pcell.setBorderWidthTop(convertBorderStyle(lineStyle));//
//            }
//            lineStyle = format.getBorderLine(jxl.format.Border.LEFT);
//            //convertBorderStyle(lineStyle)
//            if (lineStyle.getValue() == BorderLineStyle.NONE.getValue()){
//                pcell.setBorderColorLeft(Color.WHITE);
//                pcell.setBorderWidthLeft(0.0f);
//            }
//            else{
//                pcell.setBorderColorLeft(convertColour(format.getBorderColour(jxl.format.Border.LEFT), Color.BLACK));
//                pcell.setBorderWidthLeft(convertBorderStyle(lineStyle));
//            }
            lineStyle = format.getBorderLine(jxl.format.Border.RIGHT);
           
            if (lineStyle.getValue() == BorderLineStyle.NONE.getValue()){
                pcell.setBorderColorRight(Color.WHITE);
                pcell.setBorderWidthRight(0.0f);
            }
            else{
                pcell.setBorderColorRight(convertColour(format.getBorderColour(jxl.format.Border.RIGHT), Color.BLACK));
                pcell.setBorderWidthRight(convertBorderStyle(lineStyle));
            }
            if (format.getBackgroundColour().getValue() != Colour.DEFAULT_BACKGROUND.getValue()) {
                pcell.setBackgroundColor(convertColour(format.getBackgroundColour(), Color.WHITE));
              }
        }else{
        	pcell.setBorder(0);
        }

	}

	private void transferFormat(com.lowagie.text.Cell pcell, Cell cell,int index,int cols,List<Cell> cells) {
        jxl.format.CellFormat format = cell.getCellFormat();
        if (format != null) {
            pcell.setHorizontalAlignment(convertAlignment(format.getAlignment(), cell.getType()));
            pcell.setVerticalAlignment(convertVerticalAlignment(format.getVerticalAlignment()));
            boolean left=false,top=false,right=false,bottom=false;
            if(index==0){
            	top=true;
            	left=true;
            	right=true;
            	bottom=true;
            }else if(index<cols){
//            	if(cellLeftBorderLineStyle(cells.get(0))&&cellRightBorderLineStyle(cells.get(1))){
//            		top=true;
//	            	left=true;
//	            	right=true;
//	            	bottom=true;
//            	}else{
            	top=true;
            	left=false;
            	right=true;
            	bottom=true;
//            	}
            }else if(index%cols==0){
            	top=false;
            	left=true;
            	right=true;
            	bottom=true;
            }else if(cellBottomBorderLineStyle(cells.get(index-cols))&&cellLeftBorderLineStyle(cells.get(index-1))){
              	top=true;
            	left=true;
            	right=true;
            	bottom=true;
            }else if(cellBottomBorderLineStyle(cells.get(index-cols))){
             	top=true;
            	left=false;
            	right=true;
            	bottom=true;
            }else if(cellLeftBorderLineStyle(cells.get(index-1))){
             	top=false;
            	left=true;
            	right=true;
            	bottom=true;
            }else{
               	top=false;
            	left=false;
            	right=true;
            	bottom=true;
            }
            BorderLineStyle lineStyle = null;
                lineStyle = format.getBorderLine(jxl.format.Border.BOTTOM);
                if (lineStyle.getValue() == BorderLineStyle.NONE.getValue()){
                    pcell.setBorderColorBottom(Color.WHITE);
                    pcell.setBorderWidthBottom(0.0f);
                }
                else if(bottom){
                    pcell.setBorderColorBottom(convertColour(format.getBorderColour(jxl.format.Border.BOTTOM), Color.BLACK));
                    pcell.setBorderWidthBottom(convertBorderStyle(lineStyle));
                }
            lineStyle = format.getBorderLine(jxl.format.Border.TOP);
           
            if (lineStyle.getValue() == BorderLineStyle.NONE.getValue()){
                pcell.setBorderColorTop(Color.WHITE);
                pcell.setBorderWidthTop(0.0f);
            }
            else if(top){
                pcell.setBorderColorTop(convertColour(format.getBorderColour(jxl.format.Border.TOP), Color.BLACK));
                pcell.setBorderWidthTop(convertBorderStyle(lineStyle));//
            }
            lineStyle = format.getBorderLine(jxl.format.Border.LEFT);
            //convertBorderStyle(lineStyle)
            if (lineStyle.getValue() == BorderLineStyle.NONE.getValue()){
                pcell.setBorderColorLeft(Color.WHITE);
                pcell.setBorderWidthLeft(0.0f);
            }
            else if(left){
                pcell.setBorderColorLeft(convertColour(format.getBorderColour(jxl.format.Border.LEFT), Color.BLACK));
                pcell.setBorderWidthLeft(convertBorderStyle(lineStyle));
            }
            lineStyle = format.getBorderLine(jxl.format.Border.RIGHT);
           
            if (lineStyle.getValue() == BorderLineStyle.NONE.getValue()){
                pcell.setBorderColorRight(Color.WHITE);
                pcell.setBorderWidthRight(0.0f);
            }
            else if(right){
                pcell.setBorderColorRight(convertColour(format.getBorderColour(jxl.format.Border.RIGHT), Color.BLACK));
                pcell.setBorderWidthRight(convertBorderStyle(lineStyle));
            }
          if (format.getBackgroundColour().getValue() != Colour.DEFAULT_BACKGROUND.getValue()) {
              pcell.setBackgroundColor(convertColour(format.getBackgroundColour(), Color.WHITE));
            }
        }else{
        	pcell.setBorder(0);
        }
       }
    
	private float convertBorderStyle(BorderLineStyle style) {
		if (style == null) return 0.0f;
		
		float w = 0.0f;
		if(BorderLineStyle.HAIR.getValue()==style.getValue()){
			w=0.0f;
		}else if(BorderLineStyle.NONE.getValue() == style.getValue()) {
			if (noEmptyBorder){
				w = 0.0f;
			}
		}else if(BorderLineStyle.THIN.getValue() == style.getValue()){
			w = 1.0f;
		}else if (BorderLineStyle.THICK.getValue() == style.getValue()) {
			w = 1.5f;
        }else if (BorderLineStyle.MEDIUM.getValue() == style.getValue()) {
			w = 1.0f;
        }else if(BorderLineStyle.DOUBLE.getValue()==style.getValue()){
        	w = 1.5f;
        }else {
			w = 0.0f;
		}
		return w;
	}

	private int convertAlignment(Alignment align, CellType cellType) {
		if (align == null)
			return Element.ALIGN_UNDEFINED;
		
		if (Alignment.CENTRE.getValue() == align.getValue())
			return  Element.ALIGN_CENTER;
		
		if (Alignment.LEFT.getValue() == align.getValue())
			return Element.ALIGN_LEFT;
		
		if (Alignment.RIGHT.getValue() == align.getValue())
			return Element.ALIGN_RIGHT;
		
		if (Alignment.JUSTIFY.getValue() == align.getValue())
			return Element.ALIGN_JUSTIFIED;
		
		if (Alignment.GENERAL.getValue() == align.getValue()) {
			if (cellType == CellType.NUMBER || cellType == CellType.NUMBER_FORMULA)
				return Element.ALIGN_RIGHT;
			if (cellType == CellType.DATE || cellType == CellType.DATE_FORMULA)
				return Element.ALIGN_RIGHT;
		}
		return Element.ALIGN_UNDEFINED;
	}

	private int convertVerticalAlignment(VerticalAlignment align) {
		if (align == null)
			return Element.ALIGN_UNDEFINED;
		
		if (VerticalAlignment.BOTTOM.getValue() == align.getValue())
			return Element.ALIGN_BOTTOM;
		
		if (VerticalAlignment.CENTRE.getValue() == align.getValue())
			return Element.ALIGN_MIDDLE;
		
		if (VerticalAlignment.TOP.getValue() == align.getValue())
			return Element.ALIGN_TOP;
		
		if (VerticalAlignment.JUSTIFY.getValue() == align.getValue())
			return Element.ALIGN_JUSTIFIED;

		return Element.ALIGN_UNDEFINED;
	}

	private HeaderText readHeader(Sheet sheet){
		int addNum=0,imageNums=0;
		if(sheetImage.getImage()!=null){
			imageNums=sheetImage.getImage().size();
			System.out.println("���һ��sheet ����ͼƬ��������"+imageNums);
		}
		HeaderText text=new HeaderText();
		PdfPCell pdfCell = null;
		Phrase content = null;
		if(sheet!=null&&sheet.getColumns()>0){
			Cell cell1=sheet.getCell(0,1);
		  if(cell1!=null&& cell1.getContents().equalsIgnoreCase("t")){
			  Cell cell = sheet.getCell(0,0);
				jxl.format.CellFormat format = cell.getCellFormat();
				Font font = null;
				if (format != null && format.getFont() != null) {
					font = convertFont(format.getFont());
				} else {
					font = new Font(Font.COURIER, 10.0f, Font.NORMAL, Color.BLACK);
					//font = ChineseFont.createChineseFont(10,Font.NORMAL,Color.BLACK);
				}
				if(cell.getContents()==null){
					  text.setHeaderText1(new PdfPCell(new Phrase(" ")));
				}else{
				content = new Phrase(cell.getContents(), font);
				pdfCell = new com.lowagie.text.pdf.PdfPCell(content);
				transferFormat2(pdfCell, cell);
			    text.setHeaderText1(pdfCell);
				}
		  }else if(cell1!=null&&cell1.getContents().equalsIgnoreCase("i")){
			  if(sheetImage.getImage()!=null&&sheetImage.getImage().size()>0){
			  pdfCell=new PdfPCell(sheetImage.getImage().get(0));
			  pdfCell.setBorder(0);
			  text.setHeaderText1(pdfCell);
			  addNum++;
			  }else{
				  text.setHeaderText1(new PdfPCell(new Phrase(" ")));
			  }
		  }
		  Cell cell2=sheet.getCell(1,1);
		  if(cell2!=null&& cell2.getContents().equalsIgnoreCase("t")){
				  Cell cell = sheet.getCell(1,0);//ȡ��ÿһ�еĵ�Ԫ��
					jxl.format.CellFormat format = cell.getCellFormat();//ȡ�õ�Ԫ��ĸ�ʽ
					Font font = null;
					if (format != null && format.getFont() != null) {
						font = convertFont(format.getFont());// ����convertFont()�ķ���ת������
					} else {
						font = new Font(Font.COURIER, 10.0f, Font.NORMAL, Color.BLACK);
						//font = ChineseFont.createChineseFont(10,Font.NORMAL,Color.BLACK);
					}if(cell.getContents()==null){
						text.setHeaderText2(new PdfPCell(new Phrase(" ")));
					}else{
					content = new Phrase(cell.getContents(), font);
					pdfCell = new com.lowagie.text.pdf.PdfPCell(content);
					transferFormat2(pdfCell, cell);
			    	text.setHeaderText2(pdfCell);
					}
			  }else if(cell2!=null&&cell2.getContents().equalsIgnoreCase("i")){
				  if(sheetImage.getImage()!=null&&sheetImage.getImage().size()>0){
				  if(addNum==0){
					  Image image=sheetImage.getImage().get(0);
					  pdfCell=new PdfPCell(image);
					  pdfCell.setBorder(0);
					  text.setHeaderText2(pdfCell);
				  }else if(addNum==1&&addNum<imageNums){
					  pdfCell=new PdfPCell(sheetImage.getImage().get(1));
					  pdfCell.setBorder(0);
					  text.setHeaderText2(pdfCell);
				  }
				  addNum++;
				  }else{
					  text.setHeaderText2(new PdfPCell(new Phrase(" "))); 
				  }
			  }
		  Cell cell3=sheet.getCell(2,1);
		  if(cell3!=null&& cell3.getContents().equalsIgnoreCase("t")){
				//text.setHeaderText3(sheet.getCell(2,0).getContents());
			  Cell cell = sheet.getCell(2,0);//ȡ��ÿһ�еĵ�Ԫ��
				jxl.format.CellFormat format = cell.getCellFormat();//ȡ�õ�Ԫ��ĸ�ʽ
				Font font = null;
				if (format != null && format.getFont() != null) {
					font = convertFont(format.getFont());// ����convertFont()�ķ���ת������
				} else {
					font = new Font(Font.COURIER, 10.0f, Font.NORMAL, Color.BLACK);
					//font = ChineseFont.createChineseFont(10,Font.NORMAL,Color.BLACK);
				}if(cell.getContents()==null){
					text.setHeaderText3(new PdfPCell(new Phrase(" ")));
				}else{
				content = new Phrase(cell.getContents(), font);
				pdfCell = new com.lowagie.text.pdf.PdfPCell(content);
				transferFormat2(pdfCell, cell);
			    text.setHeaderText3(pdfCell);
				}
			  }else if(cell3!=null&&cell3.getContents().equalsIgnoreCase("i")){
				  if(sheetImage.getImage()!=null&&sheetImage.getImage().size()>0){
				  if(addNum==0){
					  pdfCell=new PdfPCell(sheetImage.getImage().get(0));
					  pdfCell.setBorder(0);
					  text.setHeaderText3(pdfCell);
					  //text.setHeaderText3(sheetImage.getImage().get(0));
				  }else if(addNum==1&&addNum<imageNums){
					  pdfCell=new PdfPCell(sheetImage.getImage().get(1));
					  pdfCell.setBorder(0);
					  text.setHeaderText3(pdfCell);
					  //text.setHeaderText3(sheetImage.getImage().get(0+1));
				  }else if(addNum==2&&addNum<imageNums){
					  pdfCell=new PdfPCell(sheetImage.getImage().get(2));
					  pdfCell.setBorder(0);
					  text.setHeaderText3(pdfCell);
				 // text.setHeaderText3(sheetImage.getImage().get(0+2));
				  }
				  }else{
					  text.setHeaderText3(new PdfPCell(new Phrase(" ")));
				  }
			  }
		}
	    return text;
	    }

	private boolean cellBottomBorderLineStyle(Cell cell){
		boolean b=false;
		jxl.format.CellFormat format = cell.getCellFormat();
		if(format!=null){
			BorderLineStyle lineStyle = null;
            lineStyle = format.getBorderLine(jxl.format.Border.BOTTOM);
            if (lineStyle.getValue() == BorderLineStyle.NONE.getValue()){
              b=true;
            }
		}
		return b;
	}
	
	private boolean cellLeftBorderLineStyle(Cell cell){
		boolean b=false;
		jxl.format.CellFormat format = cell.getCellFormat();
		if(format!=null){
			BorderLineStyle lineStyle = null;
            lineStyle = format.getBorderLine(jxl.format.Border.RIGHT);
            if (lineStyle.getValue() == BorderLineStyle.NONE.getValue()){
              b=true;
            }
		}
		return b;
	}
//	private boolean cellRightBorderLineStyle(Cell cell){
//		boolean b=false;
//		jxl.format.CellFormat format = cell.getCellFormat();
//		if(format!=null){
//			BorderLineStyle lineStyle = null;
//            lineStyle = format.getBorderLine(jxl.format.Border.LEFT);
//            if (lineStyle.getValue()!= BorderLineStyle.NONE.getValue()){
//              b=true;
//            }
//		}
//		return b;
//	}
}

*/