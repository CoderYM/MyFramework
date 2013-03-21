/**
 * Created at 2007-12-21.
 */
package com.allinpay.generator.ibatis.generator;

import java.io.File;
import java.util.Map;

/**
 * @author pony * 如果有任何对代码的修改,请按下面的格式注明修改的内容. * 序号   时间       作者        修改内容 * 1.  2008-1-25	pony created this class.
 */
public class ModelCodeGenerator extends AbstractCodeGenerator {
	public ModelCodeGenerator() {
		setTemplateFile("model.html");
		setModuleFilePath("model/src/main/java/");
	}
	
	/* (non-Javadoc)
	 * @see com.allinpay.ibatis.generaotor.sqlmap.generator.AbstractCodeGenerator#createEmptyGeneratedFile(java.lang.String)
	 */
	@Override
	public File createEmptyGeneratedFile(String fileName) {
		String pkgDir = getPackageDir();
		pkgDir = getGeneratedFilePath() + File.separator + getModuleFilePath() + File.separator  + pkgDir;
		new File(pkgDir).mkdirs();
		String filepath = pkgDir + File.separator + fileName + ".java";
		File file = new File(filepath);
		return file;
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#getPackageIdentifier()
	 */
	@Override
	public String getPackageIdentifier() {
		return "model";
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.ICodeGenerator#getName()
	 */
	public String getName() {
		return "ModelGenerator";
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.ICodeGenerator#register(java.util.Map)
	 */
	public void register(Map model) {
		super.register(model);
		model.put("modelPackage", getRealPackageName());
		model.put("serialVersionUID", String.valueOf(System.currentTimeMillis()).hashCode() + "L");
	}
}
