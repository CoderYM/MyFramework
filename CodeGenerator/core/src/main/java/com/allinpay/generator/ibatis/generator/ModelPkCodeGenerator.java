/**
 * Created at 2008-01-25.
 */
package com.allinpay.generator.ibatis.generator;

import java.io.File;
import java.util.Map;

import com.allinpay.frameworkdao.ibatis.metadata.TableMetaData;

/**
 * @author pony * 如果有任何对代码的修改,请按下面的格式注明修改的内容. * 序号   时间       作者        修改内容 * 1.  2008-1-25	pony created this class.
 */
public class ModelPkCodeGenerator extends AbstractCodeGenerator {
	public ModelPkCodeGenerator() {
		setTemplateFile("pk.html");
		setModuleFilePath("model/src/main/java/");
	}
	
	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#createEmptyGeneratedFile(java.lang.String)
	 */
	@Override
	public File createEmptyGeneratedFile(String fileName) {
		String pkgDir = getPackageDir();
		pkgDir = getGeneratedFilePath() + File.separator + getModuleFilePath() + File.separator  + pkgDir;
		new File(pkgDir).mkdirs();
		String filepath = pkgDir + File.separator + "Pk" + fileName + ".java";
		File file = new File(filepath);
		return file;
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#getPackageIdentifier()
	 */
	@Override
	public String getPackageIdentifier() {
		return "pk";
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.ICodeGenerator#getName()
	 */
	public String getName() {
		return "PkCodeGenerator";
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.ICodeGenerator#register(java.util.Map)
	 */
	public void register(Map model) {
		super.register(model);
		model.put("pkPackage", getRealPackageName());
		model.put("serialVersionUID", String.valueOf(System.currentTimeMillis()).hashCode() + "L");
	}
	
	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#generate(java.util.Map)
	 */
	@Override
	public void generate(Map model) {
		TableMetaData meta = (TableMetaData) model.get("tmd");
		if (meta.getPkColumns().size() < 2) {
			return;
		}
		super.generate(model);
	}
}
