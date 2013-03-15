package com.allinpay.its.boss.framework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.java.amateras.xlsbeans.XLSBeans;
import net.java.amateras.xlsbeans.XLSBeansException;
import net.java.amateras.xlsbeans.xssfconverter.WorkbookFinder;

public class ExcelFileUtil {
	
	/**
	 *  读取excel文件转换成对象同时兼容2003和2007
	 * @param path
	 * @param type
	 * @param objClass
	 * @return
	 */
		@SuppressWarnings("unchecked")
		public static Object readExcelToObjList(String path, String type,
				Class objClass) {
			Object obj = null;
			try {
				if ("xls".equals(type)) {

					obj = new XLSBeans().load(new FileInputStream(path), null,
							objClass, WorkbookFinder.TYPE_HSSF);
				} else {
					obj = new XLSBeans().load(new FileInputStream(path), null,
							objClass, WorkbookFinder.TYPE_XSSF);

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (XLSBeansException e) {
				e.printStackTrace();
			}
			return obj;
		}

}
