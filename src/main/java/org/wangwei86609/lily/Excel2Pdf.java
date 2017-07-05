package org.wangwei86609.lily;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.wangwei86609.lily.core.HeaderText;

public class Excel2Pdf implements Excel_TO_PDF{


	public void convertFromLocal(String excelFilePath, String pdfFilePath,PageSetting pageSetting) throws Exception{
		
//		Convert con=new Convert(excelFilePath,pdfFilePath);
//		if(pageSetting!=null){
//		con.setPageSize(pageSetting.getPageSize());
//		HeaderText text=con.getHeader();
//		PageEvent event=new PageEvent();
//		setUp(event,pageSetting,text);
//		con.convert(event);
//		}else{
//			throw new Exception("ҳ�����δ����");
//		}
		
	}

	public void convertFromDB(String excelFileName, OutputStream output, PageSetting pageSetting)throws Exception {
//		FileIntoDB db=new FileIntoDB();
//		db.fileIntoDB("D:\\CRM1.xls","crm11");
//		InputStream input=db.getInputStream(excelFileName);
//		Convert con=new Convert(input,output);
//		if(pageSetting!=null){
//		con.setPageSize(pageSetting.getPageSize());
//		HeaderText text=con.getHeader();
//		PageEvent event=new PageEvent();
//		event.setHeaderText(text);
//		event.setFooter(pageSetting.getFooter());
//		event.setHeader(pageSetting.getHeader());
//		event.setPageNumberSize(pageSetting.getFooter().getFontSize());
//		event.setPageNumberStyle(pageSetting.getFooter().getPageNumberStyle());
//		con.transform(event);
//		}else{
//			throw new Exception("ҳ�����δ����");
//		}
	}

	public void convertFromLocal(String excelFilePath, OutputStream output, PageSetting pageSetting)throws Exception {
//		Convert con=new Convert(excelFilePath,output);
//		if(pageSetting!=null){
//		con.setPageSize(pageSetting.getPageSize());
//		HeaderText text=con.getHeader();
//		PageEvent event=new PageEvent();
//		setUp(event,pageSetting,text);
//		con.convert(event);
//		}else{
//			throw new Exception("ҳ�����δ����");
//		}
	}
	/**
	 * ����PdfEvent�¼�
	 * @param event 
	 * @param pageSetting
	 * @param text
	 */
	private void setUp(PageEvent event,PageSetting pageSetting,HeaderText text){
		event.setHeaderText(text);
		event.setFooter(pageSetting.getFooter());
		event.setHeader(pageSetting.getHeader());
		event.setPageNumberSize(pageSetting.getFooter().getFontSize());
		event.setPageNumberStyle(pageSetting.getFooter().getPageNumberStyle());
	}
	public static void main(String[] args) {
		PageSetting set=new PageSetting();
		set.setPageSize(PdfPageSize.A4.rotate());
		Excel2Pdf pdf=new Excel2Pdf();
		try {
			pdf.convertFromLocal("D:\\CRM4.xls",new FileOutputStream("d:\\wo1.pdf"),set);
			//pdf.convertFromDB("crm11",new FileOutputStream("d:\\wo.pdf"),set);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
