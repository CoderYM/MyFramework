/**
 * @(#) CommunicationServiceFileGenerator.java
 * module  : CodeGenerator
 * version : 版本管理系统中的文件版本
 * date    : 2012-3-16
 * name    : nilomiao
 */
package com.allinpay.generator.tp.generator;

import java.io.File;

import com.allinpay.util.StringUtil;

/**
 * <pre>
 * 如果有任何对代码的修改,请按下面的格式注明修改的内容.
 * 序号	时间			作者			修改内容
 * 1.	2012-3-16	nilomiao	created this class.
 * </pre>
 */
public class CommunicationServiceFileGenerator extends AbstractCodeGenerator {
	public CommunicationServiceFileGenerator() {
		setTemplateFile("ebankCommunicationService.html");
		setModuleFilePath("src/main/java/");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.allinpay.generator.tp.generator.AbstractCodeGenerator#
	 * createEmptyGeneratedFile(java.lang.String, java.lang.String)
	 */
	@Override
	public File createEmptyGeneratedFile(String filePath, String fileName) {
		String dir = getGeneratedFilePath() + File.separator + getModuleFilePath() + File.separator;
		new File(dir).mkdirs();
		String filepath = new StringBuffer(dir).append(File.separator)
				.append(StringUtil.capFirst(fileName)).append("CommunicationService.java")
				.toString();
		File file = new File(filepath);
		return file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.allinpay.generator.tp.generator.AbstractCodeGenerator#
	 * getPackageIdentifier()
	 */
	@Override
	public String getPackageIdentifier() {
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.allinpay.generator.ibatis.ICodeGenerator#getName()
	 */
	public String getName() {
		return "CommunicationServiceFileGenerator";
	}

}
