package org.wangwei86609.lily.core;

import java.util.regex.Pattern;

import com.itextpdf.text.pdf.BaseFont;

public final class ChineseFont {
	public static BaseFont BASE_CHINESE_FONT = null;
	
	static {
		try {
			BASE_CHINESE_FONT = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		} catch (Exception e) {
		}
	}

	public static boolean containsChinese(String s) {
		if (s == null || s.length() == 0)
			return false;
		return Pattern.compile("[\u0391-\uFFE5]+").matcher(s).find();
	}
	
	private ChineseFont() {}
}
