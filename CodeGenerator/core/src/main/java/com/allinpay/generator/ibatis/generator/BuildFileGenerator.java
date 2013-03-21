/**
 * Created at 2008-06-03.
 */
package com.allinpay.generator.ibatis.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author pony
 *
 * 如果有任何对代码的修改,请按下面的格式注明修改的内容.
 * 序号   时间         作者    修改内容
 * 1.    2008-6-3      pony   created this class.
 *
 */
public class BuildFileGenerator extends AbstractCodeGenerator {
	public BuildFileGenerator() {
		setTemplateFile("build.html");
	}
	
	/**
	 * 已创建过build.xml文件的模块不再重新创建.
	 */
	private List generatedModules = new ArrayList();
	
	@Override
	public File createEmptyGeneratedFile(String fileName) {
		String dir = getGeneratedFilePath();
		new File(dir).mkdirs();
		String filepath = dir + File.separator + "build.xml";
		File file = new File(filepath);
		return file;
	}

	@Override
	public String getPackageIdentifier() {
		return "";
	}

	public String getName() {
		return "BuildFileGenerator";
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#generate(java.util.Map)
	 */
	@Override
	public void generate(Map model) {
		String moduleName = (String) model.get("moduleName");
		if (!generatedModules.contains(moduleName)) {
			super.generate(model);
			generatedModules.add(moduleName);
		}
	}

}
