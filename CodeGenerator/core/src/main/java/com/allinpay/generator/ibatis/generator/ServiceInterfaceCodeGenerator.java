/**
 * Created at 2008-01-23.
 */
package com.allinpay.generator.ibatis.generator;

import java.io.File;
import java.util.Map;

/**
 * @author pony * 如果有任何对代码的修改,请按下面的格式注明修改的内容. * 序号   时间       作者        修改内容 * 1.  2008-1-23	pony created this class.
 */
public class ServiceInterfaceCodeGenerator extends AbstractCodeGenerator {
	public ServiceInterfaceCodeGenerator() {
		setTemplateFile("service-interface.html");
		setModuleFilePath("service/src/main/java/");
	}
	
	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#createEmptyGeneratedFile(java.lang.String)
	 */
	@Override
	public File createEmptyGeneratedFile(String fileName) {
		String pkgDir = getPackageDir();
		pkgDir = getGeneratedFilePath() + File.separator  + getModuleFilePath() + File.separator + pkgDir;
		new File(pkgDir).mkdirs();
		String filepath = pkgDir + File.separator + "I" + fileName + "Service.java";
		File file = new File(filepath);
		return file;
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#getPackageIdentifier()
	 */
	@Override
	public String getPackageIdentifier() {
		return "service";
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.ICodeGenerator#getName()
	 */
	public String getName() {
		return "ServiceInterfaceGenerator";
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#register(java.util.Map)
	 */
	@Override
	public void register(Map model) {
		super.register(model);
		model.put("serviceInterfacePackage", getRealPackageName());
	}

}
