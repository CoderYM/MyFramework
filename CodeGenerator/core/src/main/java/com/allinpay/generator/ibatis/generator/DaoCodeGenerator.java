/**
 * Created at 2007-12-21.
 */
package com.allinpay.generator.ibatis.generator;

import java.io.File;
import java.util.Map;

/**
 * @author pony
 * 
 */
public class DaoCodeGenerator extends AbstractCodeGenerator {
	public DaoCodeGenerator() {
		setTemplateFile("dao.html");
		setModuleFilePath("dao/src/main/java/");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.allinpay.generator.ibatis.generator.AbstractCodeGenerator#
	 * createEmptyGeneratedFile(java.lang.String)
	 */
	@Override
	public File createEmptyGeneratedFile(String fileName) {

		String pkgDir = getPackageDir();
		// revised by Angi.Wang 2011-3-1
		// Dao的实现放到impl目录下
		pkgDir = getGeneratedFilePath() + File.separator + getModuleFilePath()
				+ File.separator + pkgDir + File.separator + "impl";
		new File(pkgDir).mkdirs();
		String filepath = pkgDir + File.separator + fileName + "Dao.java";
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
		return "dao";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.allinpay.generator.ibatis.ICodeGenerator#getName()
	 */
	public String getName() {
		return "DaoGenerator";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.allinpay.generator.ibatis.ICodeGenerator#register(java.util.Map)
	 */
	public void register(Map model) {
		super.register(model);
		model.put("daoPackage", getRealPackageName());
	}
}
