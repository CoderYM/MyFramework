/**
 * Created at 2008-01-23.
 */
package com.allinpay.generator.ibatis.generator;

import java.io.File;

/**
 * @author pony * 如果有任何对代码的修改,请按下面的格式注明修改的内容. * 序号   时间       作者        修改内容 * 1.  2008-1-23	pony created this class.
 */
public class ServiceContextXmlGenerator extends AbstractConfigFileGenerator {
	public ServiceContextXmlGenerator() {
		setTemplateFile("context-service.html");
		setModuleFilePath("resource/context");
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#createEmptyGeneratedFile(java.lang.String)
	 */
	@Override
	public File createEmptyGeneratedFile(String fileName) {
		String moduleName = (String) getModel().get("lastPackageName");
		String dir = getGeneratedFilePath() + File.separator + getModuleFilePath() + File.separator ;
		new File(dir).mkdirs();
		String filepath = dir + File.separator + moduleName + "-service.xml";
		File file = new File(filepath);
		return file;
	}
	
	protected String getGeneratedFilename(String fileName) {
		String moduleName = (String) getModel().get("lastPackageName");
		String filepath = getGeneratedFilePath() + File.separator + getModuleFilePath() + File.separator+ moduleName + "-service.xml";
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
		return "ServiceContentXmlGenerator";
	}

}
