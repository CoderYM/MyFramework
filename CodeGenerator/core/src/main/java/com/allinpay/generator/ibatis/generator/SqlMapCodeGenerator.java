/**
 * Created at 2007-12-13 by pony
 */
package com.allinpay.generator.ibatis.generator;

import java.io.File;
import java.util.Map;


/**
 * 
 */
public class SqlMapCodeGenerator extends AbstractCodeGenerator {
	public SqlMapCodeGenerator() {
		setTemplateFile("sqlmap.html");
		setModuleFilePath("resource/spring");
	}
	
	/* (non-Javadoc)
	 * @see com.allinpay.ibatis.generaotor.sqlmap.generator.AbstractCodeGenerator#createEmptyGeneratedFile()
	 */
	@Override
	public File createEmptyGeneratedFile(final String fileName) {
		String moduleName = (String) getModel().get("lastPackageName");
		String dir = getGeneratedFilePath() + File.separator  + getModuleFilePath() + File.separator + moduleName;
		new File(dir).mkdirs();
		String filepath = dir + File.separator + fileName + ".xml";
		File file = new File(filepath);
		return file;
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
		return "SqlMapGeneraotr";
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.ICodeGenerator#register(java.util.Map)
	 */
	public void register(Map model) {
		super.register(model);
	}

}
