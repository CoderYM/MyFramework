/**
 * 
 */
package com.allinpay.generator.ibatis.generator;

import java.io.File;

/**
 * @author pony
 *
 */
public class DaoContextXmlGenerator extends AbstractConfigFileGenerator {
	public DaoContextXmlGenerator() {
		setTemplateFile("context-dao.html");
		setModuleFilePath("resource/spring");
	}
	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#createEmptyGeneratedFile(java.lang.String)
	 */
	@Override
	public File createEmptyGeneratedFile(String fileName) {
		String moduleName = (String) getModel().get("lastPackageName");
		String dir = getGeneratedFilePath() + File.separator + getModuleFilePath() + File.separator;
		new File(dir).mkdirs();
		String filepath = dir + File.separator + moduleName + "-dao.xml";
		File file = new File(filepath);
		return file;
	}
	
	protected String getGeneratedFilename(String fileName) {
		String moduleName = (String) getModel().get("lastPackageName");
		String filepath = getGeneratedFilePath() + File.separator + getModuleFilePath() + File.separator+ moduleName + "-dao.xml";
		return filepath;
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#getPackageIdentifier()
	 */
	@Override
	public String getPackageIdentifier() {
		return "";
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.ICodeGenerator#getName()
	 */
	public String getName() {
		return "DaoContextXml";
	}
}
