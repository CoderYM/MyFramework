/**
 * Created at 2008-01-30.
 */
package com.allinpay.generator.ibatis.generator;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.allinpay.frameworkdao.ibatis.metadata.ColumnMetaData;
import com.allinpay.frameworkdao.ibatis.metadata.TableMetaData;

/**
 * @author pony * 如果有任何对代码的修改,请按下面的格式注明修改的内容. * 序号   时间       作者        修改内容 * 1.  2008-1-30	pony created this class.
 *
 */
public class DaoCriteriaCodeGenerator extends AbstractCodeGenerator {
	public DaoCriteriaCodeGenerator() {
		setTemplateFile("criteria.html");
		setModuleFilePath("dao/src/main/java");
	}
	
	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#createEmptyGeneratedFile(java.lang.String)
	 */
	@Override
	public File createEmptyGeneratedFile(String fileName) {
		String pkgDir = getPackageDir();
		pkgDir = getGeneratedFilePath() + File.separator + getModuleFilePath() + File.separator  + pkgDir;
		new File(pkgDir).mkdirs();
		String filepath = pkgDir + File.separator + "Criteria" + fileName + ".java";
		File file = new File(filepath);
		return file;
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#getPackageIdentifier()
	 */
	@Override
	public String getPackageIdentifier() {
		return "criteria";
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.ICodeGenerator#getName()
	 */
	public String getName() {
		return "DaoCriteriaCodeGenerator";
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#generate(java.util.Map)
	 */
	@Override
	public void generate(Map model) {
		TableMetaData meta = (TableMetaData) model.get("tmd");
		List columns = meta.getColumns();
		Iterator it = columns.iterator();
		while (it.hasNext()) {
			ColumnMetaData column = (ColumnMetaData)it.next();
			if (column.getLabel().endsWith("<query>")) {
				super.generate(model);
			}
		}
	}

}
