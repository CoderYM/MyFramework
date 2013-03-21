/**
 * 
 */
package com.allinpay.generator.ibatis.generator;

import java.io.File;

/**
 * @author pony
 * 
 */
public class SqlMapConfigXmlGenerator extends AbstractConfigFileGenerator {
	public SqlMapConfigXmlGenerator() {
		setTemplateFile("SqlMapConfig.html");
		setModuleFilePath("resource/spring");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.allinpay.generator.ibatis.generator.AbstractCodeGenerator#
	 * createEmptyGeneratedFile(java.lang.String)
	 */
	@Override
	public File createEmptyGeneratedFile(String fileName) {
		String moduleName = (String) getModel().get("lastPackageName");
		String dir = getGeneratedFilePath() + File.separator
				+ getModuleFilePath() + File.separator;
		new File(dir).mkdirs();
		String filepath = dir + File.separator + moduleName
				+ "-SqlMapConfig.xml";
		File file = new File(filepath);
		return file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.allinpay.generator.ibatis.generator.AbstractCodeGenerator#
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
		return "SqlMapConfigXmlGenerator";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.allinpay.generator.ibatis.generator.AbstractConfigFileGenerator#
	 * getGeneratedFilename(java.lang.String)
	 */
	@Override
	public String getGeneratedFilename(String fileName) {
		String moduleName = (String) getModel().get("lastPackageName");
		String filepath = getGeneratedFilePath() + File.separator
				+ getModuleFilePath() + File.separator + moduleName
				+ "-SqlMapConfig.xml";
		return filepath;
	}

}
