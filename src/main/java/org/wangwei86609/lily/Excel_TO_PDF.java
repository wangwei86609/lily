package org.wangwei86609.lily;

import java.io.OutputStream;


/**
 * �� excel�ļ��ı�׼�ӿ�
 * @author newapps
 *	2009-11-9
 */
public interface Excel_TO_PDF {
	/**
	 * �ѱ��ص�excel�ļ�ת��Ϊ���ص�PDF�ļ�
	 * @param exelFilePath excel�ļ����ڱ���·��
	 * @param PdfFilePath ����PDF�ļ���·��
	 * @param PageSetting����ҳ�������Ϣ����
	 */
	public void convertFromLocal(String excelFilePath,String pdfFilePath,PageSetting pageSetting)throws Exception;
	/**
	 * �����ݿ��д�ȡexcel�ļ�ת��ΪPDF�ļ������
	 * @param exelFilePath excel�ļ�����
	 * @param output PDF�ļ������
	 * @param PageSetting����ҳ�������Ϣ����
	 */
	public void convertFromDB(String excelFileName,OutputStream output,PageSetting pageSetting)throws Exception;
	/**
	 * �ѱ��ص�excel�ļ�ת��ΪPDF�ļ������
	 * @param exelFilePath excel�ļ����ڱ���·��
	 * @param output PDF�ļ������
	 * @param PageSetting����ҳ�������Ϣ����
	 */
	public void convertFromLocal(String excelFilePath,OutputStream output,PageSetting pageSetting)throws Exception;
}
