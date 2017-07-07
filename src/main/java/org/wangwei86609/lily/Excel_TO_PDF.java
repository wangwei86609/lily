package org.wangwei86609.lily;

import java.io.OutputStream;

public interface Excel_TO_PDF {

	public void convertFromLocal(String excelFilePath,String pdfFilePath,PageSetting pageSetting)throws Exception;

	public void convertFromDB(String excelFileName,OutputStream output,PageSetting pageSetting)throws Exception;

	public void convertFromLocal(String excelFilePath,OutputStream output,PageSetting pageSetting)throws Exception;
}
