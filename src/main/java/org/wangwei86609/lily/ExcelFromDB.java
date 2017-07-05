package org.wangwei86609.lily;

import java.io.InputStream;
/**
 * �����ݿ��ȡexcel�ļ�������Ľӿ�
 * @author newapps
 * 2009-11-12
 */
public interface ExcelFromDB {
	/**
	 * ����excel���ļ�����id
	 * �õ�һ�����ļ��������
	 * @param fileName
	 * @return InputStream�����
	 */
	public InputStream getInputStream(String fileName) throws Exception;
}
