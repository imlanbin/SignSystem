/**
 * Copyright 2011 jeff Microsystems,Inc. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



//import org.apache.log4j.Logger;
package cn.edu.ccnu.imd.analysis.common.util;
import org.apache.log4j.Logger;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * <li>描述:汉字转换位汉语拼音，英文字符不变</li><br>
 * <li>This is about <code>CnToSpellUtil</code></li>
 * 
 * @author jeff
 * @version 1.0
 * @date 2013-4-18 下午04:47:20
 */
public class CnToSpellUtil {
	
	private static Logger logger = Logger.getLogger(CnToSpellUtil.class);

	/**
	 * 汉字转换为汉语拼音首字母，英文字符不变<br>
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音
	 */
	public static String converterToFirstSpell(String chines) {
		String pinyinName = "";
		if(chines == null){
			chines = "";
		}
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			// 判断是否为汉字字符
			if (Character.toString(nameChar[i]).matches("[\\u4E00-\\u9FA5]+")) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0].charAt(0);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					logger.error(e.getMessage(),e);
				}
			} else {
				// pinyinName += nameChar[i];
				pinyinName += Character.toString(nameChar[i]);
			}
		}
		return pinyinName;
	}

	/**
	 * 汉字转换为汉语拼音，英文字符不变<br>
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音
	 */
	public static String converterToSpell(String chines) {
		String pinyinName = "";
		if(chines == null){
			chines = "";
		}
		char[] nameChar = chines.toCharArray();
		// 设置格式, 创建汉语拼音处理类
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		// 输出设置，大小写，音标方式
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

		for (int i = 0; i < nameChar.length; i++) {
//			 logger.info("##nameChar["+i+"]:"+nameChar[i]);
			// 判断是否为汉字字符
			if (Character.toString(nameChar[i]).matches("[\\u4E00-\\u9FA5]+")) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0];
				}catch(NullPointerException ex){
					ex.printStackTrace();
				}catch (BadHanyuPinyinOutputFormatCombination e) {
					logger.error(e.getMessage(),e);
				}
			} else {
				pinyinName += Character.toString(nameChar[i]);
			}
		}
		// logger.info("##pinyinName:"+pinyinName);
		return pinyinName;
	}

	/**
	 * 测试方法<br>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		logger.info("##中文转换成拼音的首字母，英文不变:"
				+ CnToSpellUtil.converterToSpell("欢迎来到最棒的Java中文社区"));
		logger.info("##中文转换成拼音，英文不变："
				+ CnToSpellUtil.converterToSpell("做Java的，你好&?^zhang"));
		logger.info("##test："
				+ CnToSpellUtil.converterToSpell("zhongguo/ @#!@#@!#$$%%^&*()/>~? \\?,.，。？！~"));
	}

}
